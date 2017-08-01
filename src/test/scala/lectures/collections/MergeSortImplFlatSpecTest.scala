package lectures.collections

import org.scalatest.{FlatSpec, Matchers}

class MergeSortImplFlatSpecTest extends FlatSpec with Matchers {

  val defaultSeq = Seq(1, 2, 3, 4, 5, 6, 7, 8, 9)

  "shuffled seq " should "not equal sorted seq" in {
    val shuffled = scala.util.Random.shuffle(defaultSeq)
    shuffled should not equal defaultSeq
  }

  "sorted seq " should "equal to already sorted seq" in {
    val shuffled = scala.util.Random.shuffle(defaultSeq)
    shuffled should not equal defaultSeq

    val sorted = MergeSortImpl.mergeSort(shuffled)
    sorted shouldBe defaultSeq
  }

  it  should "have the the same size as origignal seq" in {
    val shuffled = scala.util.Random.shuffle(defaultSeq)
    shuffled should not equal defaultSeq

    val sorted = MergeSortImpl.mergeSort(shuffled)
    sorted should have size shuffled.size
  }

  "an error" should "occur if index exceed seq size" in {
    val shuffled = scala.util.Random.shuffle(defaultSeq)
    shuffled should not equal defaultSeq
    intercept[IndexOutOfBoundsException] {
      val sorted = MergeSortImpl.mergeSort(shuffled)
      sorted(shuffled.length)
    }
  }

}
