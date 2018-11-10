package lectures.types

import scala.reflect.runtime.universe._

object ScalaTypeConstraints extends App{

  // Мы можем передать в UnderscoreTypeBehaviour любой тип формы * -> *
  abstract class UnderscoreTypeBehaviourSubClass extends UnderscoreTypeBehaviour[List]
  //Но не можем, например  (* -> *) -> *
  //Ни так
  //class UnderscoreTypeBehaviourSubClass2[A] extends UnderscoreTypeBehaviour[Either[_, A]] {}
  //Ни так. Вообще никак
  //class UnderscoreTypeBehaviourSubClass3 extends UnderscoreTypeBehaviour[Either] {}

  def identityPrm[F[_], A](x:F[A])(implicit tt: TypeTag[F[A]], ta: TypeTag[A]): Unit = {
    println(ta.tpe.toString)
  }
  //Спасибо PartialUnification - это работает, но не так, как хотелось бы
  val l: Either[Int, _] = Left(1)
  val r: Either[_, Int] = Right(1)
  identityPrm(r) // здесь тип будет Int
  identityPrm(l) // а вот здесь нет.Будет что-то вроде _$1

  // мы не можем описать более сложные выражения над тайп параметрами
  // def compose[G[_],F[_]](): UnderscoreTypeBehaviour[G[F[_]]] = ???
  // нам бы хотелось, что бы метод compose вернул тип вида T -> G[F[T]] (как List, например)
  def compose(): UnderscoreTypeBehaviour[List] = ???
  // но он вернул просто *
  // и так тоже не сработает
  //def compose[G[_],F[_]](): UnderscoreTypeBehaviour[G[F]] = ???

}
