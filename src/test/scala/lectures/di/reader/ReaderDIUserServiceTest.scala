package lectures.di.reader

import java.sql.DriverManager

import lectures.di.{ConfigurationImpl, UserServiceImpl}
import lectures.functions.User
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpec}

class ReaderDIUserServiceTest extends WordSpec with Matchers with BeforeAndAfterAll {

  Class.forName("org.sqlite.JDBC")
  private val connection = DriverManager.getConnection("jdbc:sqlite:memory")
  connection.setAutoCommit(false)

  override def afterAll(): Unit = {
    connection.close()
  }

  "Reader program" should {
    "should not find any user" in new ReaderUserServiceTestSuite(connection)({
      val configuration = ConfigurationImpl(Map.empty[String, String])
      val programConfig = UserServiceProgramDepsImpl(connection, configuration)
      val r = UserServiceReaderDIProgramImpl()
      val res: Option[User] = r.run(programConfig)
      res shouldBe None
    })

    "find correct user" in new ReaderUserServiceTestSuite(connection) ({
      val configuration = ConfigurationImpl(Map.empty[String, String])
      configuration.setAttribute("user", "Frosya")
      configuration.setAttribute("password", "qwerty3")
      // Именно в этом месте происходит иньекция зависимостей в наше небольшое приложение
      val programConfig = UserServiceProgramDepsImpl(connection, configuration)
      val r = UserServiceReaderDIProgramImpl()
      val Some(res) = r.run(programConfig)
      res.id shouldBe 22
    })
  }
}
