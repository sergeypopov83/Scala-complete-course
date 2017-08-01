package lectures.concurrent.akka

import com.typesafe.config.ConfigFactory

/**
  * Реализовать на Акке игру в пин-понг. Игра должна представлять собой 2 актора.
  * В начале игры первый актор посылает “пинг” каждые N ms.
  * Второй актор отвечает сообщением “понг” на каждые M посланных “пингов”.
  * Известно, что актор, который принимает пинги, часто падает с ошибкой.
  * Необходимо имитировать поломку каждые B ms > N и поднимать сломавшийся актор заново с тем значением пингов, с которым он упал.
  * После K принятых “pong” акторы меняются местами.
  * Игра в пин-понг заканчивается,  после G перемен игроков.
  * Входные параметры:
  *  •	N – задержка между пингами
  *  •	B – задержка между поломками
  *  •	M – количество “пингов”, необходимых для “понга”
  *  •	K – количесво “pong”, после которого акторы меняются местами
  *  •	G - количество “сетов”, после которого игра заканчивается
  *
  */
object AkkaPinPongExample extends App {

  val actorSystemConfig = ConfigFactory.load()

  val actorSystem = akka.actor.ActorSystem("Fintech", actorSystemConfig)

  val supervisorActor = actorSystem.actorOf(SupervisorActor.props(), "game_supervisor")

  supervisorActor ! StartTheGame
}
