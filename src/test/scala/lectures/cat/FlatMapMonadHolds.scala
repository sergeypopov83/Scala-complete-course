package lectures.cat

import org.scalatest.FlatSpec

class FlatMapMonadHolds extends FlatSpec {

  implicit val listFlatMonad = new FlatMapMonad[List] {

    override def flatMap[T, U](f: (T) => List[U])(v: List[T]): List[U] = v.flatMap(f)

    override def unit[T](v: T): List[T] = List(v)
  }

  "FlatMapMonad laws" should "hold" in {
    val list = List[Int](1, 2, 3, 4, 5)

    val f1 = (i: Int) => List(i * i)
    val f2 = (i: Int) => List("" + i)

    FlatMapMonadLaws.identityLaw[List, Int, Int](list)(f1)

    FlatMapMonadLaws.associativityLaw[Int, Int, String, List](list)(f1, f2)
  }
}
