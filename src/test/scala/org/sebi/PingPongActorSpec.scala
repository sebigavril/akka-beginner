package org.sebi

import scala.util.Random

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.testkit.{EventFilter, ImplicitSender, TestKit}
import org.scalatest._


class PingPongActorSpec
  extends TestKit(ActorSystem("PingPongActorSpec"))   
    with WordSpecLike 
    with ImplicitSender {

//  "Twp actors" should {
//    "send messages between themselves" in {
//      lazy val referee: ActorRef   = system.actorOf(Props(new Referee(List(pingActor, pongActor))), "referee")
//      lazy val pingActor: ActorRef = system.actorOf(Props(new PingPong(referee, pongActor)), "ping")
//      lazy val pongActor: ActorRef = system.actorOf(Props(new PingPong(referee, pingActor)), "pong")
//
//      EventFilter.info(pattern = "Winner is", occurrences = 1) intercept {
//        if (Random.nextBoolean()) pingActor ! Start 
//        else                      pongActor ! Start
//      }
//    }
//  }
}
