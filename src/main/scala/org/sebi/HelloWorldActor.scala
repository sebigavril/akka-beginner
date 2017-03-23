package org.sebi

import akka.actor.Actor

/**
  * Topics
  *  - actor interface
  *  - messages
  *  - sender
  *  - actor creation
  */

class HelloWorldActor extends Actor {
  override def receive: Receive = {
    case s => sender ! s"Hello, $s!"
  }
}
