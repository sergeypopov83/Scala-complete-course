package lectures.types

//подчеркивание в наследовании
abstract class UnderscoreTypeBehaviour[G[_]]{
  val value: G[_]
}

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
  val ss1 = Set[Set[T forSome {type T}]](Set(1, 2, 3, 4, 5))

  abstract class UnderscoreTypeBehaviourSubClass extends UnderscoreTypeBehaviour[List]

  //val ub2: UnderscoreTypeBehaviour[List[_]] = new UnderscoreTypeBehaviour[List[Int]]
  val ub1: UnderscoreTypeBehaviour[List] = new UnderscoreTypeBehaviour[List] {
    override val value: List[_] = List("","af","uf")
  }
  // подчеркивание в определении типов с помощью ключевого слова type
  type T2[_] = List[_]
  type T3[Pr] = List[Pr]
  def transformExistential[P](vals: P*): T2[P] = vals.toList
  def transformUniversal[P](vals: P*): T3[P] = vals.toList

  //  val resExist: List[Int] = transformExistential(1,2,3)// WON'T COMPILE т.к. List[_] != List[Int]
  val resExist2 = transformExistential(1, 2, 3)
  val resUniversal: List[Int] = transformUniversal(1, 2, 3)

  // подчеркивание в определении методов
  //def m0(i:_) = i
  def m[F[_]](t: F[_]): F[_] = t
  def m2(t: List[_]): List[_] = List(1, 2, 3)
  def m3[F <: List[_]](f: F): F = f // F - это тип, не принимающий параметров
  def m4[F[_] <: List[_]](t: F[_]): F[_] = t

  //def m5[F[_] <: List[_]](): F[_] = List(1,2,3)
  // def m6[F[_]: List[_]](t: F[_]) = ???

  val m3r: List[Int] = m3(List(1, 2, 3))
  val m1r = m(List(1, 2, 3)) //: List[Int]
  val m2r = m2(List()) //: List[Int]
}