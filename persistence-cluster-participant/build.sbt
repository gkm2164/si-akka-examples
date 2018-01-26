import Dependencies._

name := "persistence-cluster-participant"

libraryDependencies ++= Seq(
  akkaActor, akkaCluster, akkaClusterTools, akkaPersistence, akkaPersistenceRedis
)