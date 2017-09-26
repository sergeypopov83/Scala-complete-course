package lectures.di

import java.sql.Connection

import lectures.functions.{LPCredentials, LPUser, User}

import scala.collection.mutable

/**
  * UserServiceImpl, принимает соединение в качестве параметра конструктора
  * Это, очевидно, неправильно, т.к. соединение будет закрыто после первого
  * выполненного метода.
  *
  * Необходимо
  * * создать реализацию трейта ConnectionManager
  * * заменить параметр connection на connectionManager
  *
  * @param connection - connection to local users database
  */
class UserServiceImpl(connection: Connection) extends UserService {
  override def userByCredentials(name: String, pwd: String): Option[User] = try {
    val stmt = connection.prepareStatement("select name, pwd, id from users where name = ?")
    stmt.setString(1, name)
    val rs = stmt.executeQuery()
    Some(LPUser(rs.getInt(3), LPCredentials(rs.getString(1), rs.getString(2))))
  } catch {
    case _: Exception => None
  } finally {
    connection.commit()
  }

  override def updateUserPwd(id: String, pwd: String): Unit = try {
    val stmt = connection.prepareStatement("update users set pwd = ? where id = ?")
    stmt.setString(1, pwd)
    stmt.setString(2, id)
    stmt.executeUpdate()
  } finally {
    connection.commit()
  }
}

object ConfigurationImpl {
  def apply(map: Map[String, String]) = new ConfigurationImpl(mutable.Map[String, String](map.toSeq: _*))
}

class ConfigurationImpl(map: mutable.Map[String, String]) extends Configuration {
  override def attribute(attrName: String): Option[String] =
    map.get(attrName)

  override def setAttribute(attrName: String, value: String): Unit =
    map.update(attrName, value)
}

