package lectures.types

import LoggedStringBusinessLogic._
import org.scalatest.{Matchers, WordSpec}

class HasImplicitTest extends WordSpec with Matchers {

  "HasImplicit type" should {
    "constraint values to those who have implicit value in scope" in {
      val logic = new LoggedStringBusinessLogic
      logic.doSomeJob("blaaa") shouldBe "blaaa"
      //logic.doSomeJob(1) shouldBe 1
    }
  }
}

