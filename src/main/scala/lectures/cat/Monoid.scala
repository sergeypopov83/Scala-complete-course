package lectures.cat

trait Monoid[T] {
  def identity: T

  def op(arg1: T, arg2: T): T
}
