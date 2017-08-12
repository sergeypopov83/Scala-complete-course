package lectures.di

import org.scalatest.WordSpec

class NaiveUserServiceImplTest extends WordSpec {

  "UserService" should {
    "return correct user " in new UserServiceTestSuite(print("done"))
  }
}
