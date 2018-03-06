package lectures.cat

import cats.arrow.FunctionK
import org.scalatest.{Matchers, WordSpec}

import scala.util.{Failure, Success}
import lectures.cat.NatTransform._

class NatTransformTest extends WordSpec with Matchers{

  "transformation" should { "work properly" in {

    val result = sampler(sourceTransformer(Success(List(1,2,3))))
    val result2 = sampler(sourceTransformer(Failure(new Exception())))

   result.contains(1) shouldBe true
   result2.isEmpty  shouldBe true
  }
  }


}

