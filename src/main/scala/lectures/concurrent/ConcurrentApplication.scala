package lectures.concurrent

import java.util.concurrent.Executors

import scala.io.StdIn
import scala.util.Try

object ConcurrentApplication extends App {

  private val CpuTaskSize = 1000 * 1000

  def doCpuBoundTask(id: Int): Unit = {
    var i = 1
    for (k <- 1 to CpuTaskSize) {
      i += Math.pow(k, 2).toInt
    }
  }

  println("Press Enter to start")
  StdIn.readLine()

  val poolSize = 4
  val executor = Executors.newFixedThreadPool(poolSize)
  val queue = new java.util.Vector[Int]()
  val totalWorkQty = 10000
  (1 to totalWorkQty).foreach(queue.add)
  (1 to poolSize).map { _ =>
    new Runnable {
      override def run(): Unit = {
        var work: Int = -1
        do {
          work = Try(queue.remove(0)).getOrElse(-1)
          if (work >= 0) {
            doCpuBoundTask(work)
          }
          if (work % (totalWorkQty / 10) == 0) {
            println(s"- done: ${Math.round((work * 1.0 / totalWorkQty) * 100)}%")
          }
        } while (work >= 0)
      }
    }
  }.foreach(executor.submit)

  while (queue.size() > 0) {
    Thread.sleep(100)
  }
  println("Done!")
  println("Press Enter to finish")
  StdIn.readLine()
}
