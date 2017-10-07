package lectures.functions

import org.scalatest._
import lectures.functions.{Data, Computation, CurriedComputation, FunctionalComputation}
trait Result{
  val result = Set("Клара", "Карла")
}



class CurriedComputationTest extends FlatSpec with Result {
  "Curried computation" should "compute result" in new Data {
    assertResult(result) {
      CurriedComputation.partiallyAppliedCurriedFunction(dataArray).toSet
    }
  }
}

class ComputationTest extends FlatSpec with Result {
  "Computation" should "compute result" in new Data {
    assertResult(result)(Computation.computation(filterData, dataArray).toSet)
  }
}

class FunctionalComputationTest extends FlatSpec with Result{
  "Functional computation" should "compute result" in new Data {
    assertResult(result) {
      FunctionalComputation.filterApplied(dataArray).toSet
    }
  }

}
