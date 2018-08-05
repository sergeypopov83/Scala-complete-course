package lectures.cat

trait Group[T] extends Monoid[T] {
  def inverse: T
}
