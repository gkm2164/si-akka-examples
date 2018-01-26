import sbt._
import Versions._

object Dependencies {
  val akkaActor = "com.typesafe.akka" %% "akka-actor" % akkaVersion
  val akkaCluster = "com.typesafe.akka" %% "akka-cluster" % akkaVersion
  val akkaClusterTools = "com.typesafe.akka" %% "akka-cluster-tools" % akkaVersion
  val akkaPersistence = "com.typesafe.akka" %% "akka-persistence" % akkaVersion
  val akkaPersistenceRedis = "com.hootsuite" %% "akka-persistence-redis" % akkaPersistenceRedisVersion
  val akkaHttp = "com.typesafe.akka" %% "akka-http" % akkaHttpVersion
}