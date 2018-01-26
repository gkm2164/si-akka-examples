package com.smudi.satreci.cluster

import akka.actor.{ActorSystem, Props}
import akka.cluster.client.ClusterClientReceptionist
import com.smudi.satreci.actors.Greeter

import scala.concurrent.ExecutionContext
import scala.io.StdIn

object ClusterParticipant extends App {
  implicit val system: ActorSystem = ActorSystem("akka-example")
  implicit val dispatcher: ExecutionContext = system.dispatcher

  val greeter = system.actorOf(Props(new Greeter("clustered")), "greeter")

  ClusterClientReceptionist(system).registerService(greeter)

  StdIn.readLine()

  system.terminate()
}
