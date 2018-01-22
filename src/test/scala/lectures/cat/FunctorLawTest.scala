package lectures.cat

import org.scalatest.{Matchers, Pending, WordSpec}
import org.scalatest.mockito.MockitoSugar

class FunctorLawTest extends WordSpec with MockitoSugar with Matchers {

  val f1: (Int) => Long = (x: Int) => x.toLong
  val f2: (Long) => String = (x: Long) => x.toString


  "Some " should {
    "obey both functor laws" in Pending
  }

  "None" should {
    "obey both functor laws" in Pending
  }
}
