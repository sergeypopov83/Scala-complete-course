package lectures.di

import java.sql.Connection

import lectures.functions.User

trait ConnectionManager {
  def connection: Connection
}

trait UserService {
  def userByCredentials(name: String, pwd: String): Option[User]

  def updateUserPwd(id: String, pwd: String): Unit
}

trait Configuration {
  def attribute(attrName: String): Option[String]

  def setAttribute(attrName: String, value: String): Unit
}

trait UserServiceProgramDeps {
  val us: UserService
  val configuration: Configuration
}
