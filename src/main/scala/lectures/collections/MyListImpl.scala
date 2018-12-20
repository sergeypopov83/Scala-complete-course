package lectures.collections

import scala.collection.mutable.{ListBuffer, ArrayBuffer}

/**
  * Представим, что по какой-то причине Вам понадобилась своя обертка над списком целых чисел List[Int]
  *
  * Вы приняли решение, что будет достаточно реализовать 4 метода:
  * * * * * def flatMap(f: (Int => MyList)) -  реализуете на основе соответствующего метода из List
  * * * * * метод map(f: (Int) => Int) - с помощью только что полученного метода flatMap класса MyList
  * * * * * filter(???) - через метод flatMap класса MyList
  * * * * * foldLeft(acc: Int)(???) - через декомпозицию на head и tail
  *
  * Для того, чтобы выполнить задание:
  * * * * * раскомментируйте код
  * * * * * замените знаки вопроса на сигнатуры и тела методов
  * * * * * не используйте var и мутабильные коллекции
  *
  */
object MyListImpl extends App {

  case class MyList[T, CollectionType <: Traversable[T]](data: Traversable[T]) {

    def flatMap(f: T => MyList[T, CollectionType]): MyList[T, CollectionType] =
      MyList(data.flatMap(it => f(it).data))

    def map(f: T => T): MyListImpl.MyList[T, CollectionType] =
      flatMap(it => MyList(List(f(it))))

    def foldLeft(acc: T)(f: ((T, T)) => T): T = data match {
      case h :: t => MyList(t).foldLeft(f(acc, h))(f)
      case _ => acc
    }

    def filter(f: T => Boolean): MyListImpl.MyList[T, CollectionType] =
      flatMap(it => {
        if (f(it)) MyList(List(it)) else MyList(List.empty)
      })

  }

  class MyListBuffer[T](inp: ListBuffer[T]) extends MyList[T, ListBuffer[T]](inp) {}

  class MyIndexedList[T](inp: IndexedSeq[T]) extends MyList[T, IndexedSeq[T]](inp) {}

  require(MyList[Int, List[Int]](List(1, 2, 3, 4, 5, 6)).map(p => p * 2).data == List(2, 4, 6, 8, 10, 12))
  require(MyList[Long, ListBuffer[Long]](ListBuffer(1, 2, 3, 4, 5, 6)).filter(_ % 2 == 0).data == List(2, 4, 6))
  require(MyList[Int, List[Int]](List(1, 2, 3, 4, 5, 6)).foldLeft(0)(tpl => tpl._1 + tpl._2) == 21)
  require(MyList[Float, IndexedSeq[Float]](ArrayBuffer.empty[Float]).foldLeft(0)(tpl => tpl._1 + tpl._2) == 0)

  require(new MyListBuffer[Long](ListBuffer(1, 2, 3, 4, 5, 6)).filter(_ % 2 == 0).data == List(2, 4, 6))
  require(new MyIndexedList[Float](ArrayBuffer.empty[Float]).foldLeft(0)(tpl => tpl._1 + tpl._2) == 0)
}