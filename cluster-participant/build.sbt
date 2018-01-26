import Dependencies._

name := "cluster-participant"

libraryDependencies ++= Seq(
  akkaActor, akkaCluster, akkaClusterTools
)