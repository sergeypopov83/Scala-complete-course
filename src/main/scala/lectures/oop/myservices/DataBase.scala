package lectures.oop.myservices

class DataBase(url: String) {
  private val connectionId = connectToPostgresDatabase()

  def executePostgresQuery(sql: String): String = {
    // DO NOT TOUCH
    println(s"Executed SQL statement on connection $connectionId: $sql")
    s"Result of $sql"
  }

  private def connectToPostgresDatabase(): Int = {
    // DO NOT TOUCH
    println("Connected to PostgerSQL database")
    42 // pretty unique connection id
    //throw new Exception... if not success
  }
}

