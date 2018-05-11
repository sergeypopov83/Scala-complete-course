package lectures.types.lambda

import lectures.types.lambda.Booleans.{IF => _, _}
import org.scalatest.{Matchers, Pending, WordSpec}
import BooleanSupplementary._

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
      val res = IF[AND[TRUE, TRUE]#v] {
        print("blaaa")
        true
      }
      res shouldBe true
    }

    "NOT[FALSE] resolve to true" in {
      //      val res = IF[NOT[FALSE]#v] {
      //        true
      //      }
      //      res shouldBe true
    }

    " OR resole to false" in {
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
