package org.sebi

import akka.actor.{Actor, ActorRef}

/**
  * Topics
  * - complex messages
  * - communication between actors
  * - stopping actors
  * - stateless
  */

object PingPong {
  
  class Ping(referee: ActorRef, pong: ActorRef) extends Actor {
    override def receive: Receive = {
      case Start   => playPingPong(0, referee, pong)
      case Ball(n) => playPingPong(n, referee, pong)
      case Stop    => context.stop(self)
    }
  }

  class Pong(referee: ActorRef, ping: ActorRef) extends Actor {
    override def receive: Receive = {
      case Start   => playPingPong(0, referee, ping)
      case Ball(n) => playPingPong(n, referee, ping)
      case Stop    => context.stop(self)
    }
  }

  private def playPingPong(i: Int, referee: ActorRef, opponent: ActorRef)(implicit sender: ActorRef) = {
    if (i < 10) {
      println(s"Playing $i to $opponent")
      opponent ! Ball(i + 1)
    }
    else referee ! Ball(i)
  }

  class Referee extends Actor {
    override def receive: Receive = {
      case Ball => println(s"Winner is $sender")      
    }
  }
  
  sealed trait Protocol
  case object Start             extends Protocol
  case object Stop              extends Protocol
  case class  Ball(number: Int) extends Protocol

}