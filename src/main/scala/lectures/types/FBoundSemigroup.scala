package lectures.types
import java.time.Instant

/**
  * Ваша задача сделать класс FBoundSemigroup f-bounded
  * Реализовать его наследников, заменив все типы Nothing на нужные
  * Убедиться, что все сделано правильно, выполнив задание в FBoundSemigroupTest.scala
  * @tparam F
  */
trait FBoundSemigroup{
  def |+|(other: Nothing): Nothing
}

/**
  * @param msgs - список записей в лог с времением записи
  */
case class Log(msgs: Seq[(String, Instant)])

class StringSemigroup(val string: String) extends FBoundSemigroup {
  override def |+|(other: Nothing): Nothing = ???
}

class LogSemigroup(val log: Log) extends FBoundSemigroup{
  override def |+|(other: Nothing): Nothing = ???
}




