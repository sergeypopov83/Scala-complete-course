package lectures.concurrent.akka

import akka.testkit._
import org.scalatest.{Matchers, WordSpec}

/**
  *
  * Do the sync tests
  * All test are sync because TestActorRef uses CallingThreadDispatcher
  *
  */
class WorkerUnitTest extends WordSpec with Matchers with TestActorSystem with ImplicitSender {

  private val pingDelay = 1000l
  private val breakDelay = 100000l
  private val pingsForPong = 2
  private val pongsToSwitch = 2

  "worker" should {
    "become Ponger" when {
      "BecomePong message arrive" in {
        val testActorRef = TestActorRef(new Worker(pingDelay, breakDelay, pingsForPong, pongsToSwitch))
        testActorRef ! BecomePong()
        testActorRef ! WorkerStatusTest
        expectMsg(WorkerStatus(false))
      }
    }
    "become Pinger" when {
      "BecomePing message arrive " in {
        val testPongerActorRef = TestActorRef(new Worker(pingDelay, breakDelay, pingsForPong, pongsToSwitch))
        val testActorRef = TestActorRef(new Worker(pingDelay, breakDelay, pingsForPong, pongsToSwitch))
        testActorRef ! BecomePing(testPongerActorRef)
        testActorRef ! WorkerStatusTest
        expectMsg(WorkerStatus(true))
      }
    }
    "pong" when {
      "in Ponger state and it's time to Pong" in {
        val testActorRef = TestActorRef(new Worker(pingDelay, breakDelay, pingsForPong, pongsToSwitch))
        testActorRef ! BecomePong()
        for (i <- 1 to pingsForPong) {
          testActorRef ! Ping
        }
        expectMsg(Pong)
      }
    }
  }
}

