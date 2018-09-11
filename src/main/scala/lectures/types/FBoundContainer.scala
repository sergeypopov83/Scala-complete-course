package lectures.types

trait Container[T, U <: Container[T, U]] {
  val value: T

  def compare(that: U): Int
}

class AbsIntContainer(override val value: Float) extends Container[Float, AbsIntContainer]{
  val absValue = Math.abs(value).toInt
  override def compare(that: AbsIntContainer): Int = absValue - that.absValue
}

class IntContainer(override val value: Int) extends Container[Int, IntContainer]{
  override def compare(that: IntContainer): Int = ???
}

class Int2Container(override val value: Int) extends Container[Int, IntContainer]{
  override def compare(that: IntContainer): Int = ???
  // Won't compile
  //override def compare(that: Int2Container): Int = ???
}

object FBoundContainer extends App {
  val ai = new AbsIntContainer(1000.01f)
  val ai2 = new AbsIntContainer(1000.02f)
  val ii = new IntContainer(1000)

  //ai.compare(ii)
  // ii.compare(ai2)
  ai.compare(ai2) == 0
}