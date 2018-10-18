package lectures.types

import org.scalatest.{Matchers, WordSpec}

class SeqTransformerTest extends WordSpec with Matchers{


  "Result of seq convertion" should {
    "have concrete class" in {

      implicit val tr = new SeqToVectorTransformer()

      val vector: Vector[Int] = SeqTransformer.transform(1,2,3,4,5)
      vector.isInstanceOf[Vector[Int]] shouldBe true

    }
  }
}
