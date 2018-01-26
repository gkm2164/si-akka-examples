package com.smudi.satreci.persistent

import akka.actor.{ActorSystem, Props}
import akka.cluster.client.ClusterClientReceptionist

import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn

object Main extends App {
  implicit val system: ActorSystem = ActorSystem("akka-example")
  implicit val dispatcher: ExecutionContextExecutor = system.dispatcher

  val greetPersist = system.actorOf(Props(new GreetPersistent("persist")), "greeter-persistence")

  ClusterClientReceptionist(system).registerService(greetPersist)

  StdIn.readLine()

  system.terminate()
}
