package org.sebi

import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestKit}
import com.typesafe.config._
import org.scalatest._
import FaultToleranceSpec.logLessConfig

class FaultToleranceSpec
  extends TestKit(ActorSystem("FaultToleranceSpec", logLessConfig))
    with ImplicitSender
    with WordSpecLike {
  
  "An actor" should {
    "display the configured fault tolerant behaviour" in {
      system.actorOf(Props[Supervisor], "supervisor")
     
      Thread.sleep(5000)
    }
  }
}

object FaultToleranceSpec {
  val logLessConfig = ConfigFactory.load
    .withValue("akka.loglevel", ConfigValueFactory.fromAnyRef("OFF"))
    .withValue("akka.stdout-loglevel", ConfigValueFactory.fromAnyRef("OFF"))
}
