package lectures.eval

import org.scalacheck.Gen
import org.scalatest.{Matchers, WordSpec}
import org.scalatest.prop.PropertyChecks
import LazySchedulerView._

/**
  *
  *
  */
class LazySchedulerTest extends WordSpec with PropertyChecks with Matchers {

  val nott = afterWord("not")

  "Lazy scheduler view " should {
//    "iterate over all elements" when {
//      "it has not expired yet" in {
//        val g = Gen.listOfN(100, Gen.alphaStr)
//        forAll(g) { l =>
//          val r = l.lazySchedule(Long.MaxValue).map {
//            identity
//          }
//          r should have size 100
//        }
//      }
//    }
//
//    "return lazy views " which {
//      "conform timeout contract" in {
//        val ls = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).lazySchedule(500)
//        val filteredView = ls.takeWhile(_ > 0)
//        filteredView should have size 10
//        Thread.sleep(500)
//        filteredView should have size 10
//        val filteredView2 = ls.takeWhile(_ > 0)
//        filteredView2 should have size 0
//      }
//    }
  }

  "Lazy scheduler view " should nott {
//    "iterate" when {
//      "it already has expired" in {
//        val g = Gen.listOfN(100, Gen.alphaStr)
//        forAll(g) { l =>
//          val r = l.lazySchedule(-1).map {
//            identity
//          }
//          r should have size 0
//        }
//      }
//    }
  }

}
