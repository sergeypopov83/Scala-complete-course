package lectures.cat

/**
  * Реализуйте методы проверки свойств моноида и группы
  *
  */
class MonoidLaw[A](monoid: Monoid[A]) {

  def identityLaw: Boolean = ???

  def associativityLaw(arg1: A, arg2: A, arg3: A): Boolean =  ???

}

class GroupLaw[A](group: Group[A]) extends MonoidLaw(group) {

  def inverseLaw: Boolean = ???
}
