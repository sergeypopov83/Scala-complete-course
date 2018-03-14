package lectures.di.reader

import java.sql.Connection

import cats.Id
import cats._
import cats.arrow.FunctionK
import cats.data.{Kleisli, Reader}
import lectures.di.{ConnectionManager, UserService, UserServiceProgramDeps}
import lectures.di.reader.DIDomain.ReaderTOption
import lectures.functions.User

object DIDomain {
  type ReaderTOption[A, B] = Kleisli[Option, A, B]
}

object ReaderTOption {

  import DIDomain._

  def apply[A, B](f: A => Option[B]): ReaderTOption[A, B] = Kleisli(f)

  def headOption[A](list: List[A]): Option[A] = list.headOption

  val lifted: FunctionK[List, Option] = FunctionK.lift(headOption)

  implicit val toOption: FunctionK[Id, Option] = new FunctionK[Id, Option] {
    def apply[A](a: Id[A]) = Option(a)
  }
}

object ConnectionManagerMonadic {
  def connection: Reader[UserServiceProgramDeps, ConnectionManager] = ???
}

object ConfigurationMonadic {
  def attribute(attrName: String): ReaderTOption[UserServiceProgramDeps, String] =
    ReaderTOption(attr => {
      attr.configuration.attribute(attrName)
    })
}

object UserServiceMonadic {
  def userByCred(name: String, pwd: String): ReaderTOption[UserServiceProgramDeps, User] =
    ReaderTOption[UserServiceProgramDeps, User](usp => usp.us.userByCredentials(name, pwd))
}