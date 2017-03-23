package org.sebi

import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestKit}
import org.scalatest._

class HelloWorldActorSpec
  extends TestKit(ActorSystem("HelloWorldActorSpec"))
    with ImplicitSender
    with WordSpecLike {
  
//  "An actor" should {
//    "prepend 'Hello, ' to messages and send them back" in {
//      val helloWorldActor = system.actorOf(Props[HelloWorldActor], "hello-world")
//      helloWorldActor ! "Sebi"
//      expectMsg("Hello, Sebi!")
//    }
//  }
}
