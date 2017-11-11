package lectures.types.lambda

import lectures.types.lambda.Booleans.{FALSE, NOT, OR, TRUE}
import org.scalatest.{Matchers, Pending, WordSpec}
import  BooleanSupplementary._

class TypelevelConditions extends WordSpec with Matchers {

  "Boolean type lambdas" should {
    "resolve to true" in {
      val res = IF[TRUE] {
        print("blaaa")
        true
      }
      res shouldBe true
    }

    "and resolve to true" in {
      //      val res = IF[NOT[FALSE]#v] {
      //        true
      //      }
      //      res shouldBe true
    }

    "resole to false" in {
      //      val res = IF[OR[FALSE, FALSE]#v] {
      //        true
      //      }
      //      res shouldBe false
    }
    /**
      * Придумайте еще один или несколько тестов
      */
    "and resole to false" in Pending
  }
}
