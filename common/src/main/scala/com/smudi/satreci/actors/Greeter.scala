package com.smudi.satreci.actors

import akka.actor.{Actor, ActorLogging}
import com.smudi.satreci.messages.{Bye, Greet}

class Greeter(id: String) extends Actor with ActorLogging {
  log.info(s"Greeter[$id] actor initialized")
  override def receive: Receive = {
    case Greet(name) => sender ! s"$id said; Hello, $name"
    case Bye(name) => sender ! s"$id said; Bye, $name"
  }
}
