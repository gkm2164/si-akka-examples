package com.smudi.satreci.persistent

import akka.actor.{ActorSystem, Props}
import com.smudi.satreci.messages.{Bye, Greet}

import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn

object Main extends App {
  implicit val system: ActorSystem = ActorSystem("akka-example")
  implicit val dispatcher: ExecutionContextExecutor = system.dispatcher

  val greetPersist = system.actorOf(Props(new GreetPersistent("persist")))

  greetPersist ! Greet("gyeongmin")
  greetPersist ! Bye("gyeongmin")

  StdIn.readLine()

  system.terminate()
}
