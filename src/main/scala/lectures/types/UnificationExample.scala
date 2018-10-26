package lectures.types

import scala.reflect.runtime.universe._

object UnificationExample extends App {

  def identity[F[_], A](implicit tt: TypeTag[F[A]]): Unit = {
    println(tt.tpe.toString)
  }
  def identityParameter[F[_]](x: F[_]): F[_] = x
  def identityPrm[F[_], A](x: F[A])(implicit tt: TypeTag[F[A]], tt2: TypeTag[A]): Unit = {
    println(tt.tpe.toString)
    println("A is " + tt2.tpe.toString)
  }

  identityParameter(Some(10))
  identityParameter(Left(10))
  identityPrm(Left(10))

  // вычислить тип из подходящего значения
  // компилятор с частичным обобщением может,
  // а вот трансформировать явно указанный тип - нет
  // Если мы раскоменнитируем это срочку с вызовос метода identity
  // то увидим слудующую ошибку
  // Left[_, Nothing] takes no type parameters, expected: one
  //identity[Left[_, Nothing], Int]
//  identity[Left[Nothing, _], Int]
}


