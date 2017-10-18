package lectures.functions

import org.scalatest.{FlatSpec, Matchers}

class ComputationTest extends FlatSpec with Matchers {
  "computation" should "results with Клара, Карла on test strings" in {
    var res = Computation.computation(Computation.filterData, Computation.dataArray)
    res should be(Array("Клара", "Карла"))
  }

  "computation" should  "results with empty Array on completely different strings" in {
    var res = Computation.computation("aaaas wwewe ss", "bbbb aaa".split(" "))
    res should be(Array())
  }
}
