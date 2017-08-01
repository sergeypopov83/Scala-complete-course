package lectures.oop

import org.mockito.Mockito.{when => w, _}

import org.scalatest.mock.MockitoSugar
import org.scalatest.{Matchers, GivenWhenThen, BeforeAndAfterAll, WordSpec}
import org.mockito.Matchers.{eq => exact, _}
/**
  * This test tests nothing and is intended only to show the power of Mockito mocks
  *
  *
  */
class BSTTestWithMocks extends WordSpec with Matchers with MockitoSugar with BeforeAndAfterAll with GivenWhenThen{

  val bstSUT = mock[BST]
  "BST" when {
    "empty" should {
      "not have left child" in {
        w(bstSUT.left).thenReturn(None)
        bstSUT.left shouldBe None
      }
      "and right child" in{
        w(bstSUT.right).thenReturn(None)
        bstSUT.right shouldBe None
      }
    }
  }

  it should {
    "return values" which {
      "were added" in {
        info("emulate that there is a node with the value == 10")
        w(bstSUT.find(any[Int]())).thenReturn(None)
        info("set exact value with matcher")
        w(bstSUT.find(exact[Int](10))).thenReturn(Some(bstSUT))
        info("set exact value")
        w(bstSUT.find(10)).thenReturn(Some(bstSUT))

        bstSUT.find(1) shouldBe None
        bstSUT.find(10) should be === Some(bstSUT)
        info("verify that we have executed find method for at least once")
        verify(bstSUT, atLeastOnce()).find(any[Int]()) // never, atLeast, calls..  are also available
      }
    }
  }
}
