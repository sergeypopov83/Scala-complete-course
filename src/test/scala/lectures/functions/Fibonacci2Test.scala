package lectures.functions

import org.scalatest.FlatSpec
class Fibonacci2Test  extends FlatSpec{
  "Fibonacci" should "be 5" in {
    assert(Fibonacci2.fibs2(5) === 5)
  }
  it should "be correct" in {
    assert {
      1836311903 === Fibonacci2.fibs2(47)
    }
  }
}
