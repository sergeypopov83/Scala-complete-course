package lectures.concurrent.akka

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import scala.concurrent.duration._

object Worker {
  def props(pingDelay: Long, breakDelay: Long, pingsForPong: Int, pongsToSwitch: Int): Props = {
    Props(classOf[Worker], pingDelay, breakDelay, pingsForPong, pongsToSwitch)
  }
}

class Worker(pingDelay: Long,
             breakDelay: Long,
             pingsForPong: Int,
             pongsToSwitch: Int
            ) extends Actor with ActorLogging {

  import context.dispatcher

  val scheduler = context.system.scheduler

  def getNewRole: Receive = {
    case BecomePing(ponger: ActorRef) =>
      context.become(processPing(ponger, pongsToSwitch))
      self ! Ping
    case BecomePong() =>
      context.become(processPong(0))
      scheduler.scheduleOnce(breakDelay millis) {
        self ! PongerFailure("failure imitation")
      }
  }

  def processPing(ponger: ActorRef, pongsLeftToSwitch: Int): Receive = getNewRole.orElse {
    case Ping =>
      scheduler.scheduleOnce(pingDelay millis) {
        self ! Ping
      }
      ponger ! Ping
    case Pong if pongsLeftToSwitch == 1 =>
      context.become(getNewRole)
      context.parent ! SetFinished(ponger, self)
    case Pong => context.become(processPing(ponger, pongsLeftToSwitch - 1))
    case WorkerStatusTest => sender ! WorkerStatus(true)   // case for test purpose only
    case msg => log.debug("UNKNOWN MESSAGE" + msg)
  }

  def processPong(pingCount: Int): Receive = getNewRole.orElse {
    case PongerFailure(msg) =>
      scheduler.scheduleOnce(breakDelay millis) {
        self ! PongerFailure("failure imitation")
      }
      throw new PongerFailureException(msg)
    case Ping if pingCount == (pingsForPong - 1) =>
      context.become(processPong(0))
      sender ! Pong
    case Ping =>
      context.become(processPong(pingCount + 1))
    case WorkerStatusTest =>
      sender ! WorkerStatus(false)   // case for test purpose only
    case msg => log.debug("UNKNOWN MESSAGE" + msg)
  }

  def receive = getNewRole

  override def postStop() = {
    log.info(s"${this.self.path} has stopped")
  }
}
