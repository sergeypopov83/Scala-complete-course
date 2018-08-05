package lectures.cat

/**
  *  Option - особый случай копроизведения, где один из морфизмов
  *  исходит из типа, содержащего единсвенное значение null.
  *  К сожадению, такого типа в scala нет, но есть изоморфный ему Unit
  *
  *      (T)   (null)
  *      //     \\
  *     //f    g \\
  *    / \      /  \
  *   /  Option[T]  \
  *  /       |       \
  *fP\       | m     /gP
  *   \      |      /
  *    \     |     /
  *  Either[T, Unit]
  *
  * @tparam T -
  */
class OptionToEitherIso[T]{

  def toOpt(e: Either[T, Unit]): Option[T] = e match {
    case Right(a) => None
    case Left(s) => Some(s)
  }

  /** Это наш уникальный морфизм m
    * @param o -
    */
  def toEither(o: Option[T]): Either[T, Unit] = o match {
    case Some(t) => Left(t)
    case None => Right(())
  }

  def checkISO(o: Option[T], e: Either[T, Unit]): Unit = {
    assert((toOpt _ andThen toEither) (e) == e)
    assert((toEither _ andThen toOpt) (o) == o)
  }
}