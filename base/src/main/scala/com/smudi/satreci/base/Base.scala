package com.smudi.satreci.base

import akka.actor.{ActorSystem, Props}
import akka.pattern._
import akka.util.Timeout
import com.smudi.satreci.actors.Greeter
import com.smudi.satreci.messages.{Bye, Greet}

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._
import scala.io.StdIn

object Base extends App {
  /* 엑터시스템 정의 */
  val system: ActorSystem = ActorSystem("akka-example")

  /* Need ExecutionContext as implicit for Future */
  implicit val dispatcher: ExecutionContext = system.dispatcher

  /* need to set timeout, in this example, we assume it as 10 seconds */
  implicit val timeout: Timeout = Timeout(10 seconds)

  /* Actor reference */
  val greeter = system.actorOf(Props(new Greeter("local")))

  /* Ask */
  val greet = greeter ? Greet("gyeongmin")
  val bye = greeter ? Bye("gyeongmin")

  /* Wait result and print to console */
  greet.map(println)
  bye.map(println)

  /* Wait for termination */
  StdIn.readLine()

  /* Terminate */
  system.terminate()
}
