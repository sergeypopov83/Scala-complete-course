package lectures.di

import java.sql.DriverManager

class NaiveUserService extends UserService {
  override def setUserPwd(id: String, pwd: String): Unit = {
    Class.forName("org.sqlite.JDBC")
    val c = DriverManager.getConnection("jdbc:sqlite::memory")
    val stmt = c.prepareStatement("update users set pwd = ? where id = ?")
    stmt.setString(1, pwd)
    stmt.setString(2, id)
    stmt.executeUpdate()
    c.commit()
    c.close()
  }
}
