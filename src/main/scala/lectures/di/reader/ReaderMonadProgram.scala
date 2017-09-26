package lectures.di.reader

import java.sql.Connection

import cats.Id
import cats.data.Reader
import cats.implicits._
import lectures.di.reader.DIDomain.ReaderTOption
import lectures.di.{Configuration, UserServiceImpl, UserServiceProgramDeps}
import lectures.functions.{AnonymousUser, User}


case class UserServiceProgramDepsImpl(connection: Connection, configuration: Configuration) extends UserServiceProgramDeps {
  // lazy val connectionManager = ConnectionManagerImpl(configuration)
  lazy val us = new UserServiceImpl(connection)
}

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


