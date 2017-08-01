package lectures.concurrent.akka

import akka.actor.{Terminated, Props}
import akka.testkit.{ImplicitSender, TestActorRef}
import org.scalatest.{Matchers, WordSpec}
import scala.concurrent.duration._

/**
  * Do the async stuff with actors created in the implicit system
  */
class PingPongIntegrationTest extends WordSpec with Matchers with TestActorSystem with ImplicitSender {

  private val pingDelay = 10l
  private val breakDelay = 1000l
  private val pingsForPong = 2
  private val pongsToSwitch = 2
  private val setsToPLay = 2

  "Game" should {
    "finish" when {
      "set count exceeded" in {
        val testActorRef = system.actorOf(Props(classOf[SupervisorActor], pingDelay, breakDelay, pingsForPong, pongsToSwitch, setsToPLay))
        testActorRef ! StartTheGame
        val res = receiveWhile(1000 millis) {
          case msg: Any => msg
        }
        res should have size 1
        res.head == GameFinished
      }
    }
  }
}
