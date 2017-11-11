package lectures.di.reader

import java.sql.Connection

import lectures.di.UserServiceTestSuite

class ReaderUserServiceTestSuite(connection: Connection)(thunk:  => Unit) {

  import UserServiceTestSuite._

  private val dropTaleStmt = connection.prepareStatement(dropTale)
  dropTaleStmt.execute()

  private val createStmt = connection.prepareStatement(createTable)
  createStmt.execute()

  private val insertStmt = connection.prepareStatement(addUser)
  connection.commit()

  for ((id, k, v) <- users) {
    insertStmt.setInt(1, id)
    insertStmt.setString(2, k)
    insertStmt.setString(3, v)
    insertStmt.execute()
  }
  thunk
}