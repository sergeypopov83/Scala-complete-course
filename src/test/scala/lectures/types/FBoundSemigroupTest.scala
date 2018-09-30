package lectures.types

import java.time.Clock

import org.scalatest.{Matchers, WordSpec}

/**
  * Раскомментируйте первые 4 закоментированные строки теста
  * Убедитесь, что тест проходит
  *
  * Раскоментируйте последние строки, убедитесь, что код перестал компилироваться.
  */
class FBoundSemigroupTest extends WordSpec with Matchers {

  "semigroup instances" should {
    "correctly compose values" in {
      val str1 = "the beginning "
      val str2 = "the end"
      val c: Clock = Clock.systemDefaultZone()
      val log1 = Log(Seq((str1, c.instant())))
      val log2 = Log(Seq((str2, c.instant())))

//      val strSemigroup = str1 |+| str2
//      val logSemigroup = log1 |+| log2
//
//      strSemigroup.string shouldBe (str1 + str2)
//      logSemigroup.log.msgs should have size 2

      // при правильной реализации, строчки ниже компилироватся они не будут
      // strSemigroup |+| logSemigroup
      // logSemigroup |+| strSemigroup
    }
  }
}
