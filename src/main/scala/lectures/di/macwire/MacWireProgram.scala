package lectures.di.macwire

import java.sql.Connection

import lectures.di.{Configuration, ConfigurationImpl, ConnectionManagerImpl, UserServiceImpl}
import lectures.functions.{AnonymousUser, User}

import scala.collection.mutable

class MacWireProgramDependency(val connection: Connection) {
  import com.softwaremill.macwire._

  val configurationMap: mutable.Map[String, String]= mutable.Map("user" -> "Frosya", "password" -> "qwerty3")
  lazy val configuration = wire[ConfigurationImpl]

  lazy val userService = wire[UserServiceImpl]
}

class MacWireProgram(dependency: MacWireProgramDependency) {
  import com.softwaremill.macwire._
  import dependency._

  val connectionManager = wire[ConnectionManagerImpl] // just an example

  def run: Option[User] = {
    for {u <- configuration.attribute("user")
         pwd <- configuration.attribute("password")
         user <- userService.userByCredentials(u, pwd)
    } yield user
  }

  def runWithDefault: User = {
    run match {
      case Some(user) => user
      case None => AnonymousUser()
    }
  }

}
