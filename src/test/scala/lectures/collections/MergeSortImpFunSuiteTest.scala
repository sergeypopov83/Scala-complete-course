package lectures.collections

import org.scalatest.{FunSuite, Matchers}

/**
  *
  *
  *
  *
  */
class MergeSortImpFunSuiteTest extends FunSuite with Matchers {

  val defaultSeq = Seq(1,2,3,4,5,6,7,8,9)

  test("shuffled seq should not equal sorted seq") {
    val shuffled = scala.util.Random.shuffle(defaultSeq)
    shuffled should not equal defaultSeq
  }

  test("a seq sorted with an Impl should equal to already sorted seq") {
    val shuffled = scala.util.Random.shuffle(defaultSeq)
    shuffled should not equal defaultSeq

    val sorted = MergeSortImpl.mergeSort(shuffled)
    sorted shouldBe defaultSeq
  }

  test("a size of a sorted seq should be the same") {
    val shuffled = scala.util.Random.shuffle(defaultSeq)
    shuffled should not equal defaultSeq

    val sorted = MergeSortImpl.mergeSort(shuffled)
    sorted should have size shuffled.size
  }

  test("an error should occur if index exceed seq size") {
    val shuffled = scala.util.Random.shuffle(defaultSeq)
    shuffled should not equal defaultSeq
    intercept[IndexOutOfBoundsException] {
      val sorted = MergeSortImpl.mergeSort(shuffled)
      sorted(shuffled.length)
    }
  }

}
