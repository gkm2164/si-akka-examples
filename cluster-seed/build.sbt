import Dependencies._

name := "cluster-seed"

libraryDependencies ++= Seq(
  akkaActor, akkaCluster, akkaClusterTools
)