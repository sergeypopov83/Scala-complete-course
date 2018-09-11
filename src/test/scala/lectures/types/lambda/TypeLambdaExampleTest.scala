package lectures.types.lambda

import org.scalatest.{Matchers, WordSpec}

class TypeLambdaExampleTest extends WordSpec with Matchers{


  "ToLeftProjector" should {
    "preserve types" in {

      LeftToOptionProjector.projectEither(Right(10)) shouldBe None

      LeftToOptionProjector.projectEither[Int](Left(10)) shouldBe Some(10)

    }
  }

}
