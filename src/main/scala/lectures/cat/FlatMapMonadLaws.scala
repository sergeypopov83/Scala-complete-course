package lectures.cat

object FlatMapMonadLaws {

  def identity[F[_], A](a: F[A]): F[A] = a

  /**
    * (F[A] => F[A]) X A => F[B] == F[A] X (F[B] => F[B])
    *      Id        X   F       ==  F   X      Id
    */
  def identityLaw[F[_], A, B](a: F[A])(f: A => F[B])(implicit fm: FlatMapMonad[F]): Unit = {
    val leftIdentity = fm.flatMap(f) _ compose identity[F, A]
    val rightIdentity = identity[F, B] _ compose fm.flatMap(f)
    assert(leftIdentity(a) == rightIdentity(a))
    assert(leftIdentity(a) == rightIdentity(a))
  }

  /**
    * Associativity law
    * X == flatMap
    * (_ => A X A => B) X B => C == (_ => A) X (A => B X B => C)
    * (  F    X   F   ) X   F    ==    F     X (   F   X   F   )
    */
  def associativityLaw[A, B, C, F[_]](arg1: F[A])
                                        (fa: A => F[B], fb: B => F[C])
                                        (implicit fm: FlatMapMonad[F]): Unit = {
    val leftAssoc = fm.flatMap(fa) _ andThen fm.flatMap(fb)
    val rightAssoc = fm.flatMap[A, C](a => fm.flatMap(fb)(fa(a))) _
    assert(leftAssoc(arg1) == rightAssoc(arg1))
  }

}
