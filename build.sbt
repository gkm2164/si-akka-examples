lazy val commonSettings = Seq(
    organization := "com.satreci.smudi",
    scalaVersion := "2.12.4",
    version := "0.1"
)

lazy val `si-akka-examples` = (project in file("./"))
  .settings(commonSettings: _*)
  .aggregate(base, http, persistence, common, `persistence-test`, `cluster-seed`, `cluster-participant`)

lazy val base = (project in file("./base"))
  .dependsOn(common)
  .settings(commonSettings: _*)

lazy val http = (project in file("./http"))
  .dependsOn(common)
  .settings(commonSettings: _*)

lazy val persistence = (project in file("./persistence"))
  .dependsOn(common)
  .settings(commonSettings: _*)

lazy val common = (project in file("./common"))
  .settings(commonSettings: _*)

lazy val `persistence-test` = (project in file("./persistence-test"))
  .dependsOn(common, persistence)
  .settings(commonSettings: _*)

lazy val `cluster-common` = (project in file("./cluster-common"))
  .dependsOn(common)
  .settings(commonSettings: _*)

lazy val `cluster-seed` = (project in file("./cluster-seed"))
  .dependsOn(common, `cluster-common`)
  .settings(commonSettings: _*)

lazy val `cluster-participant` = (project in file("./cluster-participant"))
  .dependsOn(common, `cluster-common`)
  .settings(commonSettings: _*)

lazy val `persistence-cluster-participant` = (project in file("./persistence-cluster-participant"))
  .dependsOn(common, persistence, `cluster-common`)
  .settings(commonSettings: _*)