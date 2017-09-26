package lectures.di.naive

import org.scalatest.{Matchers, Pending, WordSpec}

class NaiveUserServiceImplTest extends WordSpec with Matchers{

  "NaiveUserService" should {
    "should not find any user" in Pending

    "find correct user" in new NaiveUserServiceTestSuite {
      withConnection(createEnv)
      val service = new NaiveUserServiceProgram()
      val Some(res) = service.run
      res.id shouldBe 22
    }
  }
}
