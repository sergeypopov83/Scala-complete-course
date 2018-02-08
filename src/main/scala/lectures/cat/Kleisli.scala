package lectures.cat

/**
  * В категории типов и функций, функции - это полноценные морфизмы, т.к. они
  * * * *  обладают композицией
  * * * *  с их помощью можно выразить Id морфизм
  *
  * Для того,что бы стать полноценным морфизмом
  * Kleisli arrow не хвататет одной функции.
  * * * *  допишите эту функцию
  * * * *  реализуйте KleisliCategoryLaws
  * * * *  напишите тесты в lectures.cat
  *
  **/

abstract class Kleisli[F[_], A, B](val run: A => F[B]) {
  self =>

  def >=>[C](f: B => F[C]): Kleisli[F, A, C] = andThen(f)

  def andThen[C](f: B => F[C]): Kleisli[F, A, C]

  def andThen[C](k: Kleisli[F, B, C]): Kleisli[F, A, C] =
    this andThen k.run

  def compose[Z](f: Z => F[A]): Kleisli[F, Z, B]

  def compose[Z](k: Kleisli[F, Z, A]): Kleisli[F, Z, B] =
    this compose k.run

  def apply(a: A): F[B] = run(a)


}

