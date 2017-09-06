package lectures.di

import java.sql.DriverManager

object UserServiceTestSuite {

  val createTable =
    """ CREATE TABLE USERS(
    ID   NUMBER NOT NULL,
    NAME  TEXT  NOT NULL,
    PWD   CHAR(50)  NOT NULL);"""

  val dropTale = "drop table users"

  val addUser = "insert into users(name, pwd) values (?, ?)"

  val users: Map[String, String] = Map(
    "Daniil" -> "qwerty1",
    "Felix" -> "qwerty2",
    "Frosya" -> "qwerty3",
    "Jimmy" -> "qwerty4",
    "Gevorg" -> "qwerty5",
    "Vakhtang" -> "qwerty6",
    "Isolda" -> "qwerty7",
    "Segismund" -> "qwerty8",
    "Avraam" -> "qwerty9",
    "Isaac" -> "qwerty10",
    "Don King the thirdVasya" -> "qwerty10"
  )

}

class UserServiceTestSuite(thunk:  => Unit) {

  import UserServiceTestSuite._

  Class.forName("org.sqlite.JDBC")
  private val c = DriverManager.getConnection("jdbc:sqlite:memory")
  c.setAutoCommit(false)

  private val dropTaleStmt = c.prepareStatement(dropTale)
  dropTaleStmt.execute()

  private val createStmt = c.prepareStatement(createTable)
  createStmt.execute()

  private val insertStmt = c.prepareStatement(addUser)
  c.commit()

  for ((k, v) <- users) {
    insertStmt.setString(1, k)
    insertStmt.setString(2, v)
    insertStmt.execute()
  }

  thunk

  dropTaleStmt.execute()
  c.commit()
  c.close()
}
