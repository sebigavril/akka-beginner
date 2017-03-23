import sbt._
import sbt.Keys._

import Dependencies._

object AkkabeginnerBuild extends Build {

  lazy val akkabeginner = Project(
    id = "akka-beginner",
    base = file("."),
    settings = Project.defaultSettings ++ Seq(
      name := "akka-beginner",
      organization := "org.sebi",
      version := "1.0.0",
      scalaVersion := "2.11.8",
      libraryDependencies ++= Seq(
        akkaActor,
        akkaTestkit,
        scalaTest
      )
    )
  )
}
