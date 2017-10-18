package lectures.functions

import org.scalatest.{FlatSpec, Matchers}

class FunctionalComputationTest extends FlatSpec with Matchers {
  "functionalComputation" should "results with roper array on test strings" in {
    var res = FunctionalComputation.functionalComputation(FunctionalComputation.filterData)(FunctionalComputation.dataArray)
    res should be(Array("Клара", "Карла"))
  }
  
  "filterAplied" should "results with full string on two identical strings" in {
    var res = FunctionalComputation.filterApplied(FunctionalComputation.filterData.split(" "))
    res should be(FunctionalComputation.filterData.split(" "))
  }
}
