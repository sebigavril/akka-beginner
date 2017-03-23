package org.sebi

import akka.actor.{ActorSystem, Props}
import akka.pattern.ask
import akka.testkit.{ImplicitSender, TestKit}
import org.scalatest._
import IncrementerProtocol._
import akka.util.Timeout
import scala.concurrent.duration._

class IncrementerSpec
  extends TestKit(ActorSystem("IncrementerSpec"))
    with ImplicitSender
    with AsyncWordSpecLike
    with Matchers {
  
  "An actor" should {
    "change its state" in {
      val incrementerActor = system.actorOf(Props(new Incrementer(0)), "incrementer")
      
      incrementerActor ! Increment
      incrementerActor ! Increment
      incrementerActor ! Increment
      incrementerActor ! Increment
      incrementerActor ! Increment

      implicit val timeout = Timeout(5 seconds)
      val res = incrementerActor ? HowMuch
      res.map(_ shouldBe 5)
    }

    "change its state without mutating data" in {
      val incrementerActor = system.actorOf(Props(new MutationLessIncrementer(0)), "mutation-less-incrementer")

      incrementerActor ! Increment
      incrementerActor ! Increment
      incrementerActor ! Increment
      incrementerActor ! Increment
      incrementerActor ! Increment

      implicit val timeout = Timeout(5 seconds)
      val res = incrementerActor ? HowMuch
      res.map(_ shouldBe 5)
    }
  }
}
