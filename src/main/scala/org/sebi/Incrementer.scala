package org.sebi

import akka.actor.Actor
import org.sebi.IncrementerProtocol._

/**
  * Topics
  *  - handling state
  *  - handling state without mutation
  */

class Incrementer(initialValue: Int = 0) extends Actor {
  
  private var res = initialValue
  
  override def receive: Receive = {
    case Increment  => res = res + 1
    case HowMuch    => sender ! res
  }
}

class MutationLessIncrementer(initialValue: Int = 0) extends Actor {

  override def receive: Receive = active(initialValue)

  private def active(res: Int): Receive = {
    case Increment  => context.become(active(res + 1))
    case HowMuch    => sender ! res
  }
}

object IncrementerProtocol {
  case object Increment
  case object HowMuch
}
