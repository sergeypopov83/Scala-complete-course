package lectures.operators

import org.scalatest.{FlatSpec, Matchers}

class CompetitionTest extends FlatSpec with Matchers {
  "stringToPrint" should "results with positive result if result more than zero  " in {
    Competition.stringToPrint(1) should be("Наша взяла")
  }

  "calculateResult" should "be positive on only positive input" in {
    Competition.calculateResult(Map("asas" -> 1, "ss" -> 1)) should be > 0
  }
}
