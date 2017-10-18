package lectures.functions

import org.scalatest.{FlatSpec, Matchers}

class Fibonacci2Test extends FlatSpec with Matchers {
  "Fibbonaci2(1)" should "be equal 1" in{
    var res = Fibonacci2.fibs2(1)
    res should be(1)
  }

  "Fibbonaci2(6)" should "be equal 8" in {
    var res = Fibonacci2.fibs2(6)
    res should be(8)
  }

  "Fibbonaci2" should "throw ArrayOutOfBoundException if passed a negative value" in {
    an[ArrayIndexOutOfBoundsException] should be thrownBy{
      Fibonacci2.fibs2(-1)
    }
  }

  "Fibbonaci2" should "throw ArrayOutOfBoundException if passed a zero value" in {
    an[ArrayIndexOutOfBoundsException] should be thrownBy{
      Fibonacci2.fibs2(0)
    }
  }

  "Fibbonaci2" should "calculate wrong value for very big numbers" in {
    var res = Fibonacci2.fibs2(8000)
    res should be < 0
  }
}
