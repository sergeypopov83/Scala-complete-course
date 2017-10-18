package lectures.functions
import org.scalatest._
import scala.collection.mutable.Queue
class SQLAPITest extends FlatSpec with Matchers{
  trait Dialog {
    val dbName = "Name of dataBase"
    val request = "some SQL request"
    val success = "SQL has been executed. Congrats!"
    val logs = Queue[String]()

    def collectLogs(str: Any): Unit = {
      logs.enqueue(str.toString)
    }
  }

  "SQLAPI" should "generate correct dialog" in new Dialog {

    new SQLAPI(dbName, collectLogs).execute(request)
    logs.dequeue() shouldBe dbName
    logs.dequeue() shouldBe request
    logs.dequeue() shouldBe success
  }
  it should "open connection successfully" in {
    val connection = new SQLAPI("some DB").connection("some SQL").open()
    assertResult(true){
      connection.opened
    }

  }
}
