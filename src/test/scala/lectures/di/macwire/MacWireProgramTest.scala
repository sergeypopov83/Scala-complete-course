package lectures.di.macwire

import java.sql.{Connection, DriverManager}

import lectures.functions.{LPUser, User}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpec}

import scala.collection.mutable

class MacWireProgramTestDependencies(override val connection: Connection) extends MacWireProgramDependency(connection) {
  override val configurationMap = mutable.Map.empty
}

class MacWireProgramTest extends WordSpec with BeforeAndAfterAll with Matchers {

  Class.forName("org.sqlite.JDBC")
  private val connection = DriverManager.getConnection("jdbc:sqlite:memory")
  connection.setAutoCommit(false)

  override def afterAll(): Unit = {
    connection.close()
  }

  "Reader program" should {
    "should not find any user" in {
      val deps = new MacWireProgramTestDependencies(connection)
      val r = new MacWireProgram(deps)
      val res: Option[User] = r.run
      res shouldBe None
    }

    "find correct user" in {
      val deps = new MacWireProgramDependency(connection)
      val r = new MacWireProgram(deps)
      val Some(user: LPUser) = r.run
      user.credentials.login shouldBe "Frosya"
      user.id shouldBe 22
    }
  }
}