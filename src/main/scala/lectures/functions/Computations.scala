package lectures.functions

/**
  *
  * В объекте 'Computation' в методе computation сравниваются 2 массива.
  * Результатом сравнения будет массив, содержащий слова, принадлежащие обоим массивам
  * В данном случа результатом будет массив, содержащий 2 элемента Array("Клара", "Карла")
  *
  * С помощью Thread.sleep имитируется прододжительное вычисление
  */
trait Data {
  val filterData = "Клара у Карла украла корралы, Карл у Клары украл кларнет"
  val dataArray = "Клара Цеткин обожала Карла Маркса".split(" ")
}

object Computation extends Data {

  def computation(filterData: String, dataProducer: Array[String]): Array[String] = {
    //EMULATE HEAVY LOAD
      Thread.sleep(10)
    //PRODUCE WORDS ARRAY FROM A STRING
    val filterArray = filterData.split(" ")
    //EMULATE HEAVY LOAD
    Thread.sleep(100)
    // LEAVE ONLY EQUAL WORDS IN BOTH ARRAYS
    dataProducer.filter(dataItem => filterArray.contains(dataItem))
  }

  val result = computation(filterData, dataArray)
  result.foreach(println)
}

/**
  * Допишите curriedComputation, так, что бы после вызова partiallyAppliedCurriedFunction
  * результат был бы тем же, что и в предыдущем случае
  *
  * Раскомментируйте последнюю строчку
  *
  * Какой тип имеет partiallyAppliedCurriedFunction - ?
  */
object CurriedComputation extends Data {

  def curriedComputation(filterData: String)(dataProducer: Array[String]): Array[String] = {
    Thread.sleep(10)
    val filterArray = filterData.split(" ")
    Thread.sleep(100)
    dataProducer.filter(dataItem => filterArray.contains(dataItem))
  }
  val partiallyAppliedCurriedFunction = curriedComputation(filterData)(_)
  val result = partiallyAppliedCurriedFunction(dataArray)
  result.foreach(println)
}

/**
  * Допишите реализации методов так, что бы результат совпадал с предыдущими.
  *
  * При этом постарайтесь минимизировать количество разбиений строки filterData на отдельные слова.
  */
object FunctionalComputation extends Data {

    def functionalComputation(filterData: String): (Array[String]) => Array[String] = {
      Thread.sleep(10)
      val filterArray = filterData.split(" ")
    (st: Array[String]) => {
      Thread.sleep(100)
      st.filter(s => filterArray.contains(s))
    }
  }
  val filterApplied = functionalComputation(filterData)
  val result = filterApplied(dataArray)
  result.foreach(println)
}
