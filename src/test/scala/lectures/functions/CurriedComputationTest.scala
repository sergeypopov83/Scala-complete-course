package lectures.functions

import org.scalatest.{FlatSpec, Matchers}

class CurriedComputationTest extends FlatSpec with Matchers {
  "curriedComputation" should "results with roper array on test strings" in {
    var res = CurriedComputation.curriedComputation(CurriedComputation.filterData)(CurriedComputation.dataArray)
    res should be(Array("Клара", "Карла"))
  }

  "partiallyAppliedCurriedFunction" should "results with full string on two identical strings" in {
    var res = CurriedComputation.partiallyAppliedCurriedFunction(CurriedComputation.filterData.split(" "))
    res should be(CurriedComputation.filterData.split(" "))
  }

}
