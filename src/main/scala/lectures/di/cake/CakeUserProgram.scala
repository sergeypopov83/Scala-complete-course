package lectures.di.cake

import lectures.di.{Configuration, UserService}
import lectures.functions.{AnonymousUser, User}

/**
  * CakePattern - техника предоставления зависимосетей через множественной наследование
  *
  */
class CakeUserProgram {
  self: UserService with Configuration =>

  def apply: Option[User] = {
    for {u <- attribute("user")
         pwd <- attribute("password")
         user <- userByCredentials(u, pwd)
    } yield user
  }

  def applyWithDefault: User = {
    apply match {
      case Some(user) => user
      case None => AnonymousUser()
    }
  }
}

