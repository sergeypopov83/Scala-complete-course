package lectures.concurrent.akka

import org.scalatest.WordSpec
import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props
import akka.event.Logging

class MyActor extends Actor {
  val log = Logging(context.system, this)

  def receive = {
    case "test" =>
      val sendRef = sender()
      print(sendRef.path.toString)
      print("received test")
    case _      =>
      val sendRef = sender()
      print(sendRef.path.toString)
      print("received unknown message")
  }
}

class AkkaExampleTest extends WordSpec{

  "Akka actor" should{
    "print sender and correct message" in {
        val system = ActorSystem("mySystem")
        val myActor = system.actorOf(Props[MyActor], "alias")
        myActor ! "test"
        myActor ! "another test"
         Thread.sleep(1000)
         system.terminate()

    }
  }
}
