lazy val commonSettings = Seq(
    organization := "com.satreci.smudi",
    scalaVersion := "2.12.4",
    version := "0.1"
)

lazy val `si-akka-examples` = (project in file("./"))
  .settings(commonSettings: _*)
  .aggregate(`base`, `http`, `persistence`, `cluster`, `common`)

lazy val `base` = (project in file("./base"))
  .dependsOn(`common`)
  .settings(commonSettings: _*)

lazy val `http` = (project in file("./http"))
  .dependsOn(`common`)
  .settings(commonSettings: _*)

lazy val `persistence` = (project in file("./persistence"))
  .dependsOn(`common`)
  .settings(commonSettings: _*)

lazy val `cluster` = (project in file("./cluster"))
  .dependsOn(`common`)
  .settings(commonSettings: _*)

lazy val `common` = (project in file("./common"))
  .settings(commonSettings: _*)