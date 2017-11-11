package lectures.collections

import scala.annotation.tailrec

/**
  * Постарайтесь не использовать мутабильные коллекции и var
  * Подробнее о сортировке можно подсмотреть здесь - https://en.wikipedia.org/wiki/Merge_sort
  *
  */
object MergeSortImpl extends App {

  @tailrec
  def merge(left: Seq[Int], right: Seq[Int], res: Seq[Int] = Seq()): Seq[Int] = {
    (left, right) match {
      case (Nil, _) => res ++ right
      case (_, Nil) => res ++ left
      case (l :: leftTail, r :: rightTail) =>
        if (l < r) merge(leftTail, right, res :+ l)
        else merge (left, rightTail, res :+ r)
    }
  }

  def mergeSort(data: Seq[Int]): Seq[Int] = {
    if (data.size <= 1) data
    else {
      val (lToSort, rToSort) = data.splitAt(data.size / 2)
      val left = mergeSort(lToSort)
      val right = mergeSort(rToSort)
      merge(left, right)
    }
  }

  println(mergeSort(Seq(-5, 117, 0, 567, 345, 4, 5, 678, 900)))
  println(mergeSort(Seq()))
  println(mergeSort(Seq(-100500)))
}
