package lectures.types

import lectures.types.LoggedCheckBound.Logged
import shapeless.tag.@@


/**
  * В данном задании ваша задача сделать возможным вызов doSomeJob в HasImplicitTest
  *
  * doSomeJob - это метод, выполняющий какую-то полезную работу, который принимает на вход строку.
  * Строка эта не обычная, а имеющая тип LoggedString
  *
  * Строка с типом LoggedString - это строка, которая была залогирована (в  данном случае просто выведена в консоль).
  * Логирование, как побочное действие, выполняет implicit конвертор LoggedCheckBound.
  * Его метод, apply, принимает на вход тип T, логирует входной параметр и возвращает нужный тип.
  * Новые инстансы на основе входных параметров создавать запрещено, все проверки должны быть выражены с помощью
  * операций над типами.
  *
  * Ни одного типа Nothing в коде остаться не должно.
  *
  * После того, как тест пройдет для метода doSomeJob
  * * * * Сделайте тоже самое для метода doSomeOtherJob(v: LoggedInt): Int
  * * * * Подумайте, как можно обобщить эти методы на произвоьлный тип T
  *
  * Тип HasImplicitBound, сведетельствует о implicit связи типов
  */
trait HasImplicitBound {
  type HasImplicit[U, T] = Nothing  // Заменить на нужный тип
}

object LoggedCheckBound extends HasImplicitBound {

  type Logged[T] = T

  type LoggedString = Nothing // Заменить на нужный тип
  type LoggedInt = Nothing  // Заменить на нужный тип

  /**
    *
    *  Этот метод должен залогировать входной параметр и
    *  вернуть нужный тип
    *
    */
  def apply[T](u: T): HasImplicit[Nothing, Nothing] = ???
}

object LoggedStringBusinessLogic {
  // это метод конвертор, который должен вызвать LoggedCheckBound.apply() и вернуть тип LoggedString
  implicit def implicitCheck(str: String) = ???
}

class LoggedStringBusinessLogic {

  import LoggedCheckBound._

  def doSomeJob(v: LoggedString): String = v
  def doSomeOtherJob(v: LoggedInt): Int = v
}





