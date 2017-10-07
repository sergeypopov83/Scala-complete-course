package lectures.functions

/**
  * Цель упражнения: вычислить 9 - е число Фибоначчи
  * Для этого раскомментируйте строчку в методе fibs и исправьте ошибку компиляции.
  *
  * Данная реализация вычисления чисел фибоначчи крайне не оптимальна (имеет показатеьную сложность O(a.pow(n)) )
  * Для того, что бы в этом убедиться, Вы можете раскомментировать
  * строчку с вычислением 1000-ого числа фибоначчи
  *
  */
object Fibonacci {
  def fibs(num: Int): Int = {
    if (num <= 0) {
      throw new RuntimeException ("Negative number of fibonacci")
    }
    if (num == 1) 1
    else if (num == 2) 1
    else fibs(num - 1) + fibs(num - 2)
  }
  def main (args: Array[String]): Unit ={
    println(fibs(9))
    //println(fibs(1000))
  }
}






