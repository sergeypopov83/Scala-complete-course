package lectures.concurrent.akka

import akka.testkit.TestActorRef
import akka.util.Timeout
import org.scalatest.{Matchers, WordSpec}
import scala.concurrent.Await
import akka.pattern.ask
import scala.concurrent.duration._

/**
  *
  */
class SupervisorUnitTest extends WordSpec with Matchers with TestActorSystem {

  private val pingDelay = 1000l
  private val breakDelay = 1000l
  private val pingsForPong = 2
  private val pongsToSwitch = 2
  private val setCount = 2
  implicit val timeout: Timeout = Timeout(10 second)

  "supervisor actor" should {
    "create two children" when {
      "proper message accepted" in {
        val testActorRef = TestActorRef(new SupervisorActor(pingDelay, breakDelay, pingsForPong, pongsToSwitch, setCount))
        testActorRef ! StartTheGame
        testActorRef.children should have size 2
      }
    }
    "stop it self" when {
      "set count limit reached" in {
        val testActorRef = TestActorRef(new SupervisorActor(pingDelay, breakDelay, pingsForPong, pongsToSwitch, 1))
        testActorRef ! StartTheGame
        Await.ready(testActorRef ? SetFinished(testActorRef.children.head, testActorRef.children.tail.head), 10 seconds)
        testActorRef.children should have size 0
      }
    }
  }
}
