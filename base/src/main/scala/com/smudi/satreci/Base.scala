package com.smudi.satreci

import akka.actor.{ActorSystem, Props}
import akka.pattern._
import akka.util.Timeout

import scala.concurrent.duration._
import com.smudi.satreci.actors.Greeter
import com.smudi.satreci.messages.{Bye, Greet}

import scala.concurrent.ExecutionContext
import scala.io.StdIn

object Base extends App {
  /* Akka ActorSystem, in this example, the system name is akka-example */
  val system: ActorSystem = ActorSystem("akka-example")

  /* Need ExecutionContext as implicit for Future */
  implicit val dispatcher: ExecutionContext = system.dispatcher

  /* need to set timeout, in this example, we assume it as 10 seconds */
  implicit val timeout: Timeout = Timeout(10 seconds)

  /* Actor reference */
  val greeter = system.actorOf(Props(new Greeter("local")))

  val greet = greeter ? Greet("gyeongmin")
  val bye = greeter ? Bye("gyeongmin")

  greet.map(println)
  bye.map(println)

  StdIn.readLine()

  system.terminate()
}
