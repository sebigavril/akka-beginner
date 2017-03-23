import sbt._

object Dependencies {
  
  private val akkaVersion = "2.4.17"
  
  val akkaActor   = "com.typesafe.akka" %% "akka-actor"   % akkaVersion

  val akkaTestkit = "com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test"
  val scalaTest   = "org.scalatest"     %% "scalatest"    % "3.0.1"     % "test"
}
