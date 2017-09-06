package lectures.di.naive

import lectures.functions.User
import org.scalatest.{Matchers, WordSpec}

class NaiveUserServiceImplTest extends WordSpec with Matchers {

  "NaiveUserService" should {
    "should not find any user" in new NaiveUserServiceTestSuite {
      withConnection(createEnv)
      val service = new NaiveUserServiceImpl()
      val res: Option[User] = service.userByCredentials("unknown", "unknown")
      res shouldBe None
      withConnection(dropEnv)
    }

    "find correct user" in new NaiveUserServiceTestSuite {
      withConnection(createEnv)
      val service = new NaiveUserServiceImpl()
      val Some(res) = service.userByCredentials("Frosya", "qwerty3")
      res.id shouldBe 22
    }
  }
}
