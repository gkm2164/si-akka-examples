import Dependencies._

name := "persistence-test"

libraryDependencies ++= Seq(
  akkaActor, akkaPersistence, akkaPersistenceRedis
)