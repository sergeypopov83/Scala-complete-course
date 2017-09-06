package lectures.di.reader

import cats.Id
import cats.arrow.FunctionK
import cats.data.Reader
import cats.implicits._
import ReaderTOption._
import DIDomain.ReaderTOption
import lectures.di.{Configuration, UserService, UserServiceProgramDeps}
import lectures.functions.{AnonymousUser, User}

case class UserServiceProgramDepndencies(us: UserService, configuration: Configuration) extends UserServiceProgramDeps

object UserServiceReaderDIProgramImpl {
  def apply(): ReaderTOption[UserServiceProgramDeps, User] =
    for {u <- ConfigurationMonadic.attribute("user")
         pwd <- ConfigurationMonadic.attribute("password")
         user <- UserServiceMonadic.userByCred(u, pwd)
    } yield user

  def applyWithDefault: Reader[UserServiceProgramDeps, User] = {
    apply().mapF[Id, User] {
      case Some(user) => user
      // Monad transformers often need some logic that should be hidden inside monad
      // In this particular case choosing AnonymousUser as a default value is a part of a business logic
      // It has to be hidden in a UserService implementation
      case None => AnonymousUser()
    }
  }

}


