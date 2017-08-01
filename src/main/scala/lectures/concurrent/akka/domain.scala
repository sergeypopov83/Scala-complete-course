package lectures.concurrent.akka

import akka.actor.ActorRef

/**
  *   Domain for Ping Pong game
  */
case object Ping

case object Pong

case object WorkerStatusTest

case object StartTheGame

case object GameFinished

case class BecomePing(ponger: ActorRef)

case class WorkerStatus(isPinger: Boolean)

case class BecomePong()

case class SetFinished(ponger: ActorRef, self: ActorRef)

case class PongerFailure(msg: String)

class PongerFailureException(message: String) extends Exception(message)