package org.sebi

import akka.actor.{Actor, ActorLogging, ActorRef}
import org.sebi.PingPongProtocol._

/**
  * Topics
  * - complex messages
  * - communication between actors
  * - stopping actors
  * - stateless
  */

class PingPong(referee: ActorRef, oponent: ActorRef) extends Actor {
  override def receive: Receive = {
    case Start    => oponent ! Ball(0)
    case Win      => println(s"I, ${self.path.name}, have won!!!")
    case b: Ball  => playBall(b)
  }

  private def playBall(b: Ball) = {
    println(s"${self.path.name} is playing ball $b")
    if (b.number < 10)
      oponent ! Ball(b.number + 1)
    else
      referee ! b
  }
}

class Referee(players: List[ActorRef]) extends Actor with ActorLogging{
  override def receive: Receive = {
    case b: Ball =>
      log.info(s"Winner is $sender")
      sender() ! Win
  }
}

object PingPongProtocol {
  sealed trait Protocol
  case object Start               extends Protocol
  case object Win                 extends Protocol
  case class  Ball(number: Int)   extends Protocol
}