package lectures.functions
import org.scalatest._
import Fibonacci.fibs
class FibonacciTest extends  FlatSpec with Matchers{
  "Fibonacci" should "be 1" in {
    fibs(1) shouldBe 1
    fibs(2) shouldBe 1
  }
  it should "be 2" in {
    fibs(3) shouldBe 2
  }
  it should "be 55" in {
    fibs(10) shouldBe 55
  }
  it should "be 377" in {
    fibs(14) shouldBe 377
  }

  it should "throw RuntimeException when num < 0" in {
    assertThrows[RuntimeException](fibs(-15))
  }
  it should "throw RuntimeException when num = 0" in {
    assertThrows[RuntimeException](fibs(0))
  }
}
