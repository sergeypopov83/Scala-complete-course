package lectures.operators

/**
  * Проходит чемпионат по спортивному киданю костей)
  * Сражаются "Наши" и "Приезжие"
  *
  * Каждый член команды бросил кубик и должен сравнить свой результат с каждым результатом из команды соперника
  *
  * Итог сравнений должн быть записан в ассоциативный массив в таком виде
  * val results: Array[(String, Int)] = (("Artem vs John" -> 3), ("Artem vs James" -> 5), ... )
  * При этом числовое значение должно быть получено как разность между результатами первого и второго игроков
  *
  * Когда составлен массив results, надо подсчитать, чья взяла.
  * Если результат встречи >0, то finalResult увеличивается на единицу
  * Если <0, уменьшается
  *
  * В итоге надо напечатать:
  * "Наша взяла", если наших побед больше, т.е. finalResult > 0
  * "Продули", если победили приезжие
  * "Победила дружба" в случае ничьи
  *
  * Для решения задачи раскомментируйте тело объекта Competition
  */

object Competition extends App {
  def stringToPrint(result: Int) :String = {
    if (result > 0 ){
      "Наша взяла"
    } else if (result < 0) {
      "Продули"
    } else {
      "Победила дружба"
    }
  }

  def calculateResult(resultsMap: Map[String, Int]):Int = {
    var ret = 0
    for (r <- resultsMap) {
      if (r._2 >0) ret = ret + 1
      else if (r._2 < 0) ret = ret - 1
      else ret = ret
    }
    ret
  }

  val locals = Map("Artem" -> 6, "Sergey" -> 5, "Anton" -> 2, "Vladimir" -> "2", "Alexander" -> 4l)
  val foreigners = Map[String, Int]("John" -> 3, "James" -> 1, "Tom" -> 2, "Dick" -> 5, "Eric" -> 6)
  val results = for (l <- locals;
                     f <- foreigners) yield {
    val localName = l._1
    val localValue = l._2.toString.toInt
    val foreignersName = f._1
    val foreignersValue = f._2
    val str = localName + " vs " + foreignersName
    str  -> (localValue - foreignersValue)
  }
  var finalResult = calculateResult(results)
  println(stringToPrint(finalResult))
}
