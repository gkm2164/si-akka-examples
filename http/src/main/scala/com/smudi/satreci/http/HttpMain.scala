package com.smudi.satreci.http

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer

import scala.concurrent.ExecutionContext
import scala.io.StdIn

object HttpMain extends App {
  /* Define actor system */
  implicit val system: ActorSystem = ActorSystem("akka-example")

  /* Need to implicit conversion of Route to Flow type */
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  /* for unbind http protocol */
  implicit val dispatcher: ExecutionContext = system.dispatcher

  val httpRequestRouter = new HttpRequestRouter(system)

  val host = "localhost"
  val port = 5000

  val bindingFuture = Http().bindAndHandle(httpRequestRouter.route, host, port)

  println(s"Actor system akka-example binded to http://$host:$port...")

  StdIn.readLine()

  println(s"Actor system shutting down...")
  bindingFuture
    .flatMap(_.unbind())
    .onComplete(_ => system.terminate())
}
