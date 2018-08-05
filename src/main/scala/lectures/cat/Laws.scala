package lectures.cat

import lectures.cat.{Kleisli => KleisliL}

/**
  * Реализуйте методы проверки свойств моноида и группы
  *
  */
class MonoidLaw[A](monoid: Monoid[A]) {

  def identityLaw: Boolean = ???

  def associativityLaw(arg1: A, arg2: A, arg3: A): Boolean = ???

}

class GroupLaw[A](group: Group[A]) extends MonoidLaw(group) {

  def inverseLaw: Boolean = ???
}

class KleisliCategoryLaws() {
  private def identity[P](p: P): P = p

  /**
    * Дана стрелка(функция) identity и входной параметр a: A
    * Постройте такую Kleisli стрелку, что бы выпонялось она стала id морфизмом
    *
    * @param a - параметр любого типа
    * @return
    */
  def identityMorphismLaw[F[_], A, B](a: A): Boolean = {
    val idArrow = new KleisliL[Option, A, A](???) {

      override def andThen[C](f: (A) => Option[C]): KleisliL[Option, A, C] = ???
      override def compose[Z](f: (Z) => Option[A]): KleisliL[Option, Z, A] = ???
    }

    // idArrow(a).??? == a
    false
  }

  /**
    * Даны 3 Kleisli срелики a, b, c
    * Докажите, что
    * (a compose b) compose c == a compose (b compose c)
    *
    */
  def compositionLaw[A, B, C, D](a: KleisliL[Option, A, B],
                     b: KleisliL[Option, B, C],
                     c: KleisliL[Option, C, D]): Nothing = ???

}
