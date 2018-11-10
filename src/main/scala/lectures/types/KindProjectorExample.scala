package lectures.types

import scala.reflect.runtime.universe._

object KindProjectorExample extends App {

  // теперь мы можем привести типы большей арности к нужной размерности
  abstract class UnderscoreTypeBehaviourSubClass2[A] extends UnderscoreTypeBehaviour[Either[?, A]]

  new UnderscoreTypeBehaviour[Either[?, Int]] {
    override val value: Either[Int, Int] = Left[Int, Int](10)
  }

  new UnderscoreTypeBehaviour[Either[Int, ?]] {
    override val value: Either[Int, Long] = Left[Int, Long](10)
  }

  // Either[Int, ?] - это не тип, это Kind
  // Either[T, ?] == [A]Either[String, A], т.е один парамер не связанн
  //  val l: Either[Int, ?] = Left(1)
  //  val r: Either[?, Int] = Right(1)
  //  type E2[T] = Either[T, _]
  //  type ENop[T] = Either[T, ?]

  type R[T] = List[List[T]]
  //List[List[?]] == List[ A => List[A]]

  def identity[F[_], A](implicit tt: TypeTag[F[A]]): Unit = {
    println(tt.tpe.toString)
    println(tt.tpe.dealias.toString)
  }

  def identityPrm[F[_], A](x:F[A])(implicit tt: TypeTag[F[A]]): Unit = {
    println(tt.tpe.toString)
    println(tt.tpe.dealias.toString)
  }

  def compose[G[_],F[_]](): UnderscoreTypeBehaviour[λ[A => G[F[A]]]] = ???

}
