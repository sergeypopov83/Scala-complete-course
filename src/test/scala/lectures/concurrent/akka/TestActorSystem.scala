package lectures.concurrent.akka

import akka.actor.{Actor, ActorSystem, Terminated}
import akka.testkit.{TestKit, TestKitBase}
import org.scalatest.{BeforeAndAfterAll, Suite}

object TerminationWatcher {
}

class TerminationWatcher extends Actor {

  override def receive: Receive = {
    case t: Terminated => context.parent ! t
  }
}

trait TestActorSystem extends TestKitBase with BeforeAndAfterAll { this: Suite =>

  implicit lazy val system = ActorSystem(getClass.getSimpleName)

  override def afterAll() {
    super.afterAll()
    TestKit.shutdownActorSystem(system)
  }
}
