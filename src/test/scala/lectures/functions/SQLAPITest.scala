package lectures.functions

import org.scalatest.{FlatSpec, Matchers}

class SQLAPITest extends FlatSpec with Matchers {
  "execute" should "return success string result on correct input" in {
    var mySQLAPI = new SQLAPI("some DB")
    mySQLAPI.execute("some SQL") should be("SQL has been executed. Congrats!")
  }

  "Connection" should "throw exception if it is not open" in {
    an[Exception] should be thrownBy{
      new SQLAPI("a").Connection("a").execute("a")
    }
  }
}
