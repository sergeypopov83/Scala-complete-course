package lectures.cat

/**
  * Applicative - это функциональный паттерн
  * характеризующийся наличием 2-х функций
  *
  * def pure[A](a: A): F[A]
  * def apply[A,B](f: F[A])(ff: F[A => B]): F[B]
  *
  * Докажте:
  * * * * что любой Applicative - это функтор, для этого
  * * * * реализуйте методы map и lift трейта Functor через apply и pure
  * * * * реализуйте методы map22 через map2 и pure
  *
  * * * * что обе формы представления Applicative равносильны, для этого
  * * * * реализуйтe apply2 через map2
  *
  * * * * изучите реализацию map2 через apply, приведенную в трейте
  *
  * @tparam F - контейнер - функтор
  */
trait Applicative[F[_]] extends Functor[F] {

  def pure[A](a: A): F[A]

  //canonical applicative form
  def apply[A, B](f: F[A])(ff: F[A => B]): F[B]

  def apply2[A, B](f: F[A])(ff: F[A => B]): F[B]

  // alternative applicative form
  def map2[A, B, C](a: F[A], b: F[B])(ff: (A, B) => C): F[C] = {
    val fabc = pure { a: A =>
      (b: B) => ff(a, b)
    }
    val fbc = apply(a)(fabc)
    apply(b)(fbc)
  }

  def applyf[A, B](ff: F[A => B]): F[A] => F[B]

  override def map[A, B](a: F[A])(f: A => B): F[B]
  def map22[A, B](a: F[A])(f: A => B): F[B]

  /**
    * Few functions that can not be implemented by pure categorical functor
    *
    */
  def traverse[A, B](seq: List[A])(f: A => F[B]): F[List[B]] =
    seq.foldLeft(pure(List[B]())) { (fAcc, item) =>
      map2(fAcc, f(item)) { (acc, b) => b :: acc
      }
    }

  def sequence[A](seq: Seq[F[A]])(): F[Seq[A]]
}
