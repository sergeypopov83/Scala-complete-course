package lectures.functions

/**
  * Представим себе, как бы мог выглядеть API для работы, например, с БД
  * Строить методы этого API будем через композицию уже определенных методов.
  *
  * Ваша задача реализовать метод execute, композируя методы из объекта SQLAPI
  * Метод execute из объекта SQLAPI должен выполнить следующие действия
  * * * * * залогировать ресурс
  * * * * * получить соединение с помощью метода connection
  * * * * * залогировать запрос
  * * * * * открыть соединение
  * * * * * выполнить SQL
  * * * * * залогировать результат
  *
  * В результате в консоль должно быть выведено сообщение
  *    some DB
  *    some SQL
  *    SQL has been executed. Congrats!
  *
  *
  * Обратите внимание на то, что композиция функций учит писать код в декларативном виде
  * Благодаря этому мы можем отделить реализацию методов от их применения и, в конечном итоге, иметь переиспользуемую
  * декларацию, которую можно применить, например, для настоящей БД
  *
  *
  */
class SQLAPI(resource: String) {

  case class Connection(resource: String, opened: Boolean = false) {

    private val result = "SQL has been executed. Congrats!"

    def open(): Connection = this.copy(opened = true)

    def execute(sql: String): String = if (opened) result else throw new Exception("You have to open connection before execute")

  }

  private def logParameter(prm: String): String  = {
    println(prm)
    prm
  }

  val connection = (resource: String) => Connection(resource)

  def execute(sql: String): String = {
    ((((logParameter _) andThen (Connection.apply(_: String).open)) andThen (openConnection(_: Connection) compose logParameter _))(_: String) andThen logParameter _)(resource)(sql)
  } // use resource from constructor


  def openConnection(connection: Connection): (String) => String =
    (sql: String) => {
      connection.open execute sql
  }

}

object SQLCheck extends App {

  new SQLAPI("some DB").execute("some SQL")

}
