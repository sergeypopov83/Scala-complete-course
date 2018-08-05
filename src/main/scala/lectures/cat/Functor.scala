package lectures.cat

/**
  * Реализуйте все функции трейта через lift и map
  * @tparam F - контейнер - функтор
  */
trait Functor[F[_]] {

  def lift[A](a: A): F[A]
  //canonical form of map
  def map[A, B](a: F[A])(f: A => B): F[B]

  def lift2[A, B](f: A => B): F[A] => F[B]

  def fproduct[A, B](fa: F[A])(f: A => B): F[(A, B)]

  def as[A, B](fa: F[A], b: B): F[B]

  def tupleL[A, B](fa: F[A], b: B): F[(B, A)]

  def tupleR[A, B](fa: F[A], b: B): F[(A, B)]
}
