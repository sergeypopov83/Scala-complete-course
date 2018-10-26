package lectures.types

import scala.reflect.runtime.universe._

object UnderscoreTypeBehaviour extends App {

  // Подчеркивание как экзистенциальный тип
  //a будет иметь тип Any
  val a = 1: T forSome {type T}
  // Такая запись невозможна т.к. _ - это тайп параметр, а не тип
  // val a2 :_ = 1
  //val a2  = 1:_
  //val s2 = Set[_](1,2,3,4,5)
  // а так можно
  val s1 = Set[T forSome {type T}](1, 2, 3, 4, 5)

  // Подчеркивание связывается с самым внутренним типом. Типы s2 и s3 идентичны
  val ss2 = Set[Set[_]]()
  val ss1 = Set[Set[T] forSome {type T}](Set(1, 2, 3, 4, 5))

  //подчеркивание в наследовании
  class UnderscoreTypeBehaviour[G[_]] {
    type GG[_] = G[_]
    //так нельзя, хотя type параметры передаются именно так
    //так как справа может быть только тип, но не род(kind)
    //type T[_] = List
  }

  class UnderscoreTypeBehaviourSubClass extends UnderscoreTypeBehaviour[List] {}

  //val ub2: UnderscoreTypeBehaviour[List[_]] = new UnderscoreTypeBehaviour[List[Int]]
  val ub1: UnderscoreTypeBehaviour[List] = new UnderscoreTypeBehaviour[List]

  // подчеркивание в определении типов с помощью ключевого слова type
  type T2[_] = List[_]
  type T3[Pr] = List[Pr]

  def transformExistential[P](vals: P*): T2[P] = vals.toList

  def transformUniversal[P](vals: P*): T3[P] = vals.toList

  //
  //  val resExist: List[Int] = transformExistential(1,2,3)// WON'T COMPILE т.к. List[_] != List[Int]
  val resExist2 = transformExistential(1, 2, 3)
  val resUniversal: List[Int] = transformUniversal(1, 2, 3)

  // подчеркивание в определении методов
  //def m0(i:_) = i
  def m[F[_]](t: F[_]): F[_] = t
  def m2[F <: List[_]](f: F): F = ??? // F - это тип, не принимающий параметров
  def m3(t: List[_]): List[_] = List(1, 2, 3)

  // def m4[F[_]: List[_]](t: F[_]) = ???
  //  def m5[F[_] <: List[_]](): F[_] = List(1,2,3)
  def m52[F[_] <: List[_]](t: F[_]): F[_] = t

  ///List(1,2,3)
  val m52r = m52(List(1, 2, 3))

  //unbound wildcard type
  //val l: List[_] = List[_](1,23,45,67)
  //val l = List[_](1,23,45,67)
  //val l3:List[Any] = List(1,2,3,4,5)
  val l3: List[_] = List(1, 2, 3, 4)
  //scala.collection.immutable.Set[_$3] = Set(1, 2, 3, 4)
  val l4: Set[_] = Set(1, 2, 3, 4)
}