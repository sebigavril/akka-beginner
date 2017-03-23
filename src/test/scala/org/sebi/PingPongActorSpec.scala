package org.sebi

import scala.util.Random

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.testkit.{TestKit, TestProbe}
import org.scalatest._
import org.sebi.PingPong._

class PingPongActorSpec
  extends TestKit(ActorSystem("PingPongActorSpec"))   
    with WordSpecLike {

  "Twp actors" should {
    "send messages between themselves" in {
      val referee = TestProbe("referee")
      lazy val pingActor: ActorRef = system.actorOf(Props(new Ping(referee.ref, pongActor)), "ping")
      lazy val pongActor: ActorRef = system.actorOf(Props(new Ping(referee.ref, pingActor)), "pong")

      if(Random.nextBoolean()) pingActor ! Start else pongActor ! Start

      referee.expectMsg(Ball(10))
    }
  }
}
