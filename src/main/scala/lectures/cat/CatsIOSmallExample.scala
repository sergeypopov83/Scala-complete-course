package lectures.cat

import cats.effect.IO

object CatsIOSmallExample extends App {

  val startIO = IO(println("Hello!"))
  val middleIO = IO(println("The middle"))
  val result = startIO.flatMap(_ => middleIO).map { _ =>
      Thread.sleep(1000)
      println("That's IT")
    }

  // unsafe run Sync blocks execution so
  result.unsafeRunSync()
  result.unsafeRunSync()
}


