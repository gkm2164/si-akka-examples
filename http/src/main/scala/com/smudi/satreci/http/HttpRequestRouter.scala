package com.smudi.satreci.http

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.pattern._
import akka.util.Timeout
import com.smudi.satreci.actors.Greeter
import com.smudi.satreci.messages.{Bye, Greet}

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._
import scala.util._

class HttpRequestRouter(system: ActorSystem) {

  implicit val dispatcher: ExecutionContext = system.dispatcher
  val greeter: ActorRef = system.actorOf(Props(new Greeter("http")))

  implicit val timeout: Timeout = Timeout(10 seconds)

  def successMessage: Try[Any] => Route = {
    case Success(message: String) => complete(message)
    case Success(any) => complete(s"Unknown command: $any")
    case Failure(e) => complete(e)
  }

  val route: Route =
    path("greet" / "hello") {
      get {
        parameter("name".as[String] ? "Gyeongmin") { name =>
          onComplete(greeter ? Greet(name))(successMessage)
        }
      }
    } ~ path("greet" / "bye") {
      get {
        parameter("name".as[String] ? "Gyeongmin") { name =>
          onComplete(greeter ? Bye(name))(successMessage)
        }
      }
    }
}