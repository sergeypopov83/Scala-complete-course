package lectures.oop

import org.scalatest.WordSpec

/**
  * Раскомментируйте и допишите тесты на
  * класс lectures.oop.Application
  */
class ApplicationTest extends WordSpec {

  private val started = new AfterWord("started")

  "Application" should {
    "return correct answer" when started{
      "in a test environment" in {
        //??? shouldBe 5
      }
      "in a production environment" in {
      //   ??? shouldBe 2
      }
    }
  }
}
