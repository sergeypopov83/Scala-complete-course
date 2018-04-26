package lectures.functions

import scala.annotation.tailrec

/**
  * Цель упражнения: используя приемы динамического программирования,
  * реализовать более оптимальный алгоритм подсчета чисел фибоначчи
  * Для этого нужно реализовать функцию fibsImpl.
  * Сигнатуру функции Вы можете расширять по своему усмотрению,
  * но реализация должна удовлетворять следующим требованиям
  * * * * метод fibsImpl - должен быть tail recursive
  * * * * параметр acc - аккумулятор посчитанных значений
  *
  */
object Fibonacci2 {

  def fibs2(num: Int) = {
    if (num <=0) throw new RuntimeException ("Negative number of fibonacci")
    if (num <= 3) Array(1, 1, 2)(num - 1)
    else fibsImpl(num, Array(1, 1, 2))(num - 1)
  }

  @tailrec
  private def fibsImpl(num: Int, acc: Array[Int]): Array[Int] = {
    val n = acc.length
    if (n < num){
      val newAcc = acc :+ acc(n-1)+acc(n-2)
      fibsImpl (num, newAcc)
    }else{
      acc
    }
  }
  def main (args: Array[String]): Unit ={
    println(fibs2(16))
    println(fibs2(1000))
  }
}
