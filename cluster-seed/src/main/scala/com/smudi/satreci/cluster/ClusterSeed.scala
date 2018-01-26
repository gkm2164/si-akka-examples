package com.smudi.satreci.cluster

import akka.actor.{ActorPath, ActorSystem, Address}
import akka.cluster.Cluster
import akka.cluster.client.{ClusterClient, ClusterClientSettings}
import akka.pattern._
import akka.util.Timeout
import com.smudi.satreci.messages.Greet

import scala.concurrent.ExecutionContext
import scala.io.StdIn
import scala.concurrent.duration._

object ClusterSeed extends App {
  implicit val system: ActorSystem = ActorSystem("akka-example")
  implicit val dispatcher: ExecutionContext = system.dispatcher
  implicit val timeout: Timeout = Timeout(10 seconds)

  val cluster = Cluster(system)
  val seedAddr = Address("akka.tcp", "akka-example", "localhost", 2551)

  val initialContacts = Set(
    ActorPath.fromString(s"${seedAddr.toString}/system/receptionist")
  )

  val c = system.actorOf(
    ClusterClient.props(
      ClusterClientSettings(system).withInitialContacts(initialContacts)
    ), "client")

  StdIn.readLine()

  c ? ClusterClient.Send("/user/greeter", Greet("Gyeongmin"), localAffinity = false) map println
  c ! ClusterClient.Send("/user/greeter-persistent", Greet("Gyeongmin"), localAffinity = false)

  StdIn.readLine()

  system.terminate()
}
