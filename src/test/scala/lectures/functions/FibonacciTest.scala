package lectures.functions
import org.scalatest._
import Fibonacci.fibs
class FibonacciTest extends  FlatSpec with Matchers{
  "Fibonacci" should "be calculated correct" in {
    fibs(1) shouldBe 1
    fibs(2) shouldBe 1
    fibs(3) shouldBe 2
    fibs(10) shouldBe 55
    fibs(14) shouldBe 377
  }
  it should "throw RuntimeException when num < 1" in {
    assertThrows[RuntimeException](fibs(0))
    assertThrows[RuntimeException](fibs(-15))
  }
}
