package com.smudi.satreci.persistent

import akka.actor.ActorLogging
import akka.persistence.PersistentActor
import com.smudi.satreci.messages.{Bye, Greet, Message}

class GreetPersistent(id: String) extends PersistentActor with ActorLogging {
  override def persistenceId: String = s"Greeter[$id]"

  def updateCommand: Message => Unit = {
    case Greet(name) => println(s"Greet $name!")
    case Bye(name) => println(s"Bye $name!")
  }

  override def receiveCommand: Receive = {
    case Greet(name) => persist(Greet(name))(updateCommand)
    case Bye(name) => persist(Bye(name))(updateCommand)
    case _ => log.warning("Unhandled command")
  }

  override def receiveRecover: Receive = {
    case msg: Message => updateCommand(msg)
    case _ => log.info("Unhandled command")
  }
}
