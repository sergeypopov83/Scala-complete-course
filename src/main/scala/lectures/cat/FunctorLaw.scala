package lectures.cat

/**
  * Функтор реализует 2 функции
  * * * lift
  * * * map
  * и следует 2 - м законам
  * * * закон соханения id морфизма
  * * * закон сохранения композзиции
  *
  * Реадизуйте методы - законы для Option, доказав, тем самым, что Option - это функтор
  *
  */

class OptionFunctorLaw[A](o: Option[A]) {

  def identityLaw: Boolean = ???

  def compositionLaw[B, C](f : A => B)(g: B => C ): Boolean =  ???

}

