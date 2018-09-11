package lectures.types

import org.scalatest.{Matchers, WordSpec}

class AuxPatternTest extends WordSpec with Matchers {


  "Result of seq convertion" should {
    "have concrete class" in {

      implicit val tr = new SeqToVectorTransformerWithAux()

      val vector = SeqTransformerWithAux.transform[Int, Vector](1, 2, 3, 4, 5)
      vector.isInstanceOf[Vector[Int]] shouldBe true

    }
  }
}