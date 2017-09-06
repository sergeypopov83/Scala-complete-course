package lectures.di

import lectures.functions.User

/**
  * CakePattern - техника предоставления зависимосетей через множественной наследование
  *
  *
  */
class CakeUserProgram {
  self: UserService with ConnectionManager with Configuration =>

  def run(): Option[User] = ???
}
