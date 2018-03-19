package lectures.cat

/**
  * Интерфейс FlatMapMonad предполагает, что функтор "спрятан внутри"
  * Т.е. если нам удалось для F реализовать flatMap и unit, то map мы получаем "автоматически"
  * Кроме того монада еще и аппликативный функтор
  *
  * Задание:
  * * * * Реализуйте методы map и apply через flatMap и unit
  * * * * Изучите FlatMapMonadLaws и создайте такие же для JoinMonad
  * * * * Реализуйте метод flatMap у JoinMonad,
  * * * * Создайте инстанс JoinMonad для List
  * * * * Напишите тест, аналогичный FlatMapMonadHolds
  *
  */
trait FlatMapMonad[F[_]] {

  def map[T, U]() = ???

  def apply[A, B](f: F[A])(ff: F[A => B]): F[B] = ???

  def flatMap[T, U](f: T => F[U])(v: F[T]): F[U]

  def unit[T](v: T): F[T]
}


trait JoinMonad[F[_]] {

  implicit val FF: Functor[F]
  def flatMap[T, U](f: T => F[U])(v: F[T]): F[U] = ???
  // обычно этот метод называют join, но логичнее, по-моему, и
  // для, scala программиста, более привычно, назвать его flatten
  def flatten[U](f: F[F[U]]): F[U]

  def unit[T](x: T): F[T]
}