package lectures.collections

/**
  * Постарайтесь не использовать мутабильные коллекции и var
  * Подробнее о сортировке можно подсмотреть здесь - https://en.wikipedia.org/wiki/Merge_sort
  *
  *
  */
object MergeSortImpl extends App {

  def Merge(first: Seq[Int], second: Seq[Int]): Seq[Int] = {
    var res: Seq[Int] = Seq[Int]()
    var data1 = first
    var data2 = second
    while (data1.nonEmpty || data2.nonEmpty){
      if (data1.isEmpty){
        res = res :+ data2.head
        data2 = data2.drop(1)
      } else {
        if (data2.isEmpty){
          res = res :+ data1.head
          data1 = data1.drop(1)
        } else {
          var left = data1.head
          var right = data2.head
          if (left < right) {
            res = res :+ left
            data1 = data1.drop(1)
          } else {
            res = res :+ right
            data2 = data2.drop(1)
          }
        }
      }
    }
    res
  }

  def mergeSort(data: Seq[Int]): Seq[Int] = {
    if (data.length == 1) data else {
      Merge(mergeSort(data.take(data.length / 2)), mergeSort(data.drop(data.length / 2)))
    }
  }

}
