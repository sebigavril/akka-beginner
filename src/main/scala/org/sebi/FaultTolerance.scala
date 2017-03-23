package org.sebi

import java.io.IOException
import scala.util.Random

import akka.actor.SupervisorStrategy.{Restart, Stop}
import akka.actor.{Actor, OneForOneStrategy, Props}

class FaultTolerance extends Actor {
  
  println(s"${self.path.name}: started")
  self ! "start"
  
  override def receive: Receive = {
    case "start" => Random.nextInt(3) match {
      case 0 => logAndThrow(new NullPointerException)
      case 1 => logAndThrow(new IOException)
      case 2 => println(s"${self.path.name}: Whew, I'm safe!")
    }
  }
  
  def logAndThrow(t: Throwable) = {
    println(s"${self.path.name}: Will throw ${t.getClass.getSimpleName}")
    throw t
  }
}

class Supervisor extends Actor {
  
  (0 to 5).map(i => context.actorOf(Props[FaultTolerance], s"fault-tolerance-$i"))
  
  override val supervisorStrategy =
    OneForOneStrategy(maxNrOfRetries = 5) {
      case _: NullPointerException      => Stop
      case _: IOException               => Restart
    }

  override def receive: Receive = Actor.emptyBehavior
}
