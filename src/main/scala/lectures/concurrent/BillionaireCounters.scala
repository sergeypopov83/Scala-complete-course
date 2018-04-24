package lectures.concurrent

import java.util.concurrent.Executors

/**
  *
  * Ваш дядя-миллиардер, как и вы, начал осваивать параллельное программирование и решил
  * подсчитать все свои активы. Однако, как он ни пытался, у него все равно большая часть
  * активов где-то терялась.
  *
  * Он очень просит вас помочь найти и исправить ошибку, а так же, по возможности, написать
  * наиболее быстрое решение.
  *
  * https://upload.wikimedia.org/wikipedia/ru/archive/e/ed/20100210210141%21Scrooge2.jpg
  *
  * Так как вы уже кое-что знаете, то давайте не ударим в грязь лицом и предоставим сразу
  * несколько решений и выпишем сюда время их работы на вашей машине:
  *
  * - SimpleBillionaireCounter        - ??? мс
  * - AtomicBillionaireCounter        - ??? мс
  * - ExtremelyFastBillionaireCounter - ??? мс
  *
  * Некоторые правила:
  * - количество тасков и итераций внутри Runnable не меняем (иначе ваш дядя обидится)
  * - трейт CounterWithReporter не меняем
  * - выводимый прогресс каждый раз должен монотонно увеличиваться (дядя очень щепетилен на этот счет)
  */


/**
  * Найдите и исправьте ошибку. Возможно, придется использовать блокировки.
  */
object SimpleBillionaireCounter extends App with CounterWithReporter {

  var counter = 0

  override def getCounterValue: Int = counter

  override def getTasks: Seq[Runnable] =
    (1 to 1000).map { _ =>
      new Runnable {
        override def run(): Unit = {
          (1 to 1000000).foreach { _ =>
            counter += 1
          }
        }
      }
    }

  countEverything()

}


/**
  * Напишите аналогичную реализацию подсчета без использования блокировок.
  */
object AtomicBillionaireCounter extends App with CounterWithReporter  {
  override def getCounterValue: Int = ???
  override def getTasks: Seq[Runnable] = ???
}


/**
  * А слабо написать еще быстрее?
  */
object ExtremelyFastBillionaireCounter extends App with CounterWithReporter {
  override def getCounterValue: Int = ???
  override def getTasks: Seq[Runnable] = ???
}


trait CounterWithReporter {
  def getCounterValue: Int

  def getTasks: Seq[Runnable]

  final def countEverything(): Unit = {

    val executor = Executors.newFixedThreadPool(10)

    val startTime = System.currentTimeMillis()

    println("Starting to count...")

    getTasks.foreach(executor.submit)

    executor.shutdown()
    while (!executor.isTerminated) {
      println(s"- progress: %,d".format(getCounterValue))
      Thread.sleep(1000)
    }

    val elapsedSeconds = System.currentTimeMillis() - startTime
    println(s"Total count is $getCounterValue, elapsed $elapsedSeconds ms")
  }
}

