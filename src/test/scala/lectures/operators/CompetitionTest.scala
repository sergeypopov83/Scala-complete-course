package lectures.operators

import org.scalatest.{Matchers, FlatSpec}

class CompetitionTest extends FlatSpec with Matchers  {
  "Associative massive" should "have form ('Artem vs John' -> 3)" in {
    Competition.results.foreach{res =>
      res._1 should include (" vs ")
    }
  }
  it should "contain (\"Artem vs James\" -> 5)" in {
    Competition.results should contain(("Artem vs James", 5))
  }

  "Competition" should "be won" in{
    Competition.finalResult should be > 0
  }
}
