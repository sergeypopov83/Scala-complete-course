package lectures.di.naive

import java.sql.{Connection, DriverManager}

import lectures.di.UserServiceTestSuite

class NaiveUserServiceTestSuite(thunk: => Unit) {

  import UserServiceTestSuite._

  def withConnection(thunk2: (Connection) => Unit): Unit = {
    Class.forName("org.sqlite.JDBC")
    val connection = DriverManager.getConnection("jdbc:sqlite:memory")
    connection.setAutoCommit(false)
    thunk2(connection)
    connection.commit()
    connection.close()
  }

   def createEnv(connection: Connection): Unit = {
    val dropTaleStmt = connection.prepareStatement(dropTale)
    dropTaleStmt.execute()

    val createStmt = connection.prepareStatement(createTable)
    createStmt.execute()

    val insertStmt = connection.prepareStatement(addUser)
    connection.commit()

    for ((id, k, v) <- users) {
      insertStmt.setInt(1, id)
      insertStmt.setString(2, k)
      insertStmt.setString(3, v)
      insertStmt.execute()
    }
  }

  def dropEnv(connection: Connection): Boolean = {
    val dropTaleStmt = connection.prepareStatement(dropTale)
    dropTaleStmt.execute()
  }
}