package lectures.di.naive

import java.sql.{Connection, DriverManager}

import lectures.di.UserService
import lectures.functions.{LPCredentials, LPUser, User}

class NaiveUserServiceProgram extends UserService {

  def run: Option[User] = {
    userByCredentials("Frosya", "qwerty3")
  }

  override def updateUserPwd(id: String, pwd: String): Unit = {
    Class.forName("org.sqlite.JDBC")
    val connection = DriverManager.getConnection("jdbc:sqlite:memory")
    connection.setAutoCommit(false)
    val stmt = connection.prepareStatement("update users set pwd = ? where id = ?")
    stmt.setString(1, pwd)
    stmt.setString(2, id)
    stmt.executeUpdate()
    connection.commit()
    connection.close()
  }

  override def userByCredentials(name: String, pwd: String): Option[User] =
    try {
      Class.forName("org.sqlite.JDBC")
      val connection = DriverManager.getConnection("jdbc:sqlite:memory")
      connection.setAutoCommit(false)
      userByCredentials(name: String, pwd: String, connection: Connection)
    } catch {
      case t: Exception => throw t
    }

  private[di] def userByCredentials(name: String, pwd: String, connection: Connection): Option[User] =
    try {
      val stmt = connection.prepareStatement("select name, pwd, id from users where name = ?")
      stmt.setString(1, name)
      val rs = stmt.executeQuery()
      Some(LPUser(rs.getInt(3), LPCredentials(rs.getString(1), rs.getString(2))))
    } catch {
      case t: Exception => throw t
    } finally {
      connection.commit()
    }
}
