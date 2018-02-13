package lectures.concurrent

import java.util
import java.util.concurrent._

import org.scalameter.api._
import org.scalameter.picklers.Implicits._

import scala.util.Try

object ScalaMeterExample
  extends Bench.OfflineReport {
//  extends Bench.LocalTime {

  val poolSizes = Gen.enumeration("PoolSize")(1, 2, 3, 4, 5, 6, 8, 12, 16, 24, 32, 64, 128)
  val taskSizes = Gen.enumeration("TaskSize")(100, 1000, 10000)
  val poolAndTaskSizes = Gen.crossProduct(poolSizes, taskSizes)

  performance of "CPU bound task" in {
    measure method "Vector" in {
      using(poolAndTaskSizes) in { case (poolSize, taskSize) =>
        println(s"Vector ${poolSize}_$taskSize ")
        performTest(new VectorQueue[Int](), poolSize, taskSize)
      }
    }

    measure method "ConcurrentLinkedQueue" in {
      using(poolAndTaskSizes) in { case (poolSize, taskSize) =>
        println(s"ConcurrentLinkedQueue ${poolSize}_$taskSize ")
        performTest(new QueueWrapper(new ConcurrentLinkedQueue[Int]()), poolSize, taskSize)
      }
      println
    }

    measure method "ArrayBlockingQueue" in {
      using(poolAndTaskSizes) in { case (poolSize, taskSize) =>
        println(s"ArrayBlockingQueue ${poolSize}_$taskSize ")
        performTest(new QueueWrapper(new ArrayBlockingQueue[Int](10000)), poolSize, taskSize)
      }
      println
    }
  }

  private def performTest(myQueue: MyQueue[Int], poolSize: Int, taskSize: Int): Unit = {
    val totalWorkQty = 10000
    val executor = Executors.newFixedThreadPool(poolSize)

    (1 to totalWorkQty).foreach(myQueue.enqueue)

    (1 to poolSize).map { _ =>
      new Runnable {
        override def run(): Unit = {
          var isWorking = true

          while (isWorking) {
            val workOpt = myQueue.dequeue()
            workOpt.fold {
              isWorking = false
            } { work =>
              doCpuBoundTask(work, taskSize)
            }
          }
        }
      }
    }.foreach(executor.submit)

    while (myQueue.getSize > 0) {
      Thread.sleep(1)
    }

    executor.shutdown()
//    val e = executor.asInstanceOf[ThreadPoolExecutor]
//    println(s"Done (${e.getActiveCount} / ${e.getCompletedTaskCount} / ${e.getQueue.size()})")
  }

  private def doCpuBoundTask(id: Int, taskSize: Int): Unit = {
    var i = 1
    for (k <- 1 to taskSize) {
      i += Math.pow(k, 2).toInt
    }
    if (i == 123) print(" ")
  }

  trait MyQueue[T] {
    def enqueue(element: T)
    def dequeue(): Option[T]
    def getSize: Int
  }

  class QueueWrapper[T](queue: util.Queue[T]) extends MyQueue[T] {
    override def enqueue(element: T): Unit = queue.add(element)
    override def dequeue(): Option[T] = Option(queue.poll())
    override def getSize: Int = queue.size()
  }

  class VectorQueue[T] extends MyQueue[T] {
    private val queue = new java.util.Vector[T]()
    override def enqueue(element: T): Unit = queue.add(element)
    override def dequeue(): Option[T] = Try(queue.remove(0)).toOption
    override def getSize: Int = queue.size()
  }
}
