package lectures.oop

object Hobbit {
  def  destroyStuff(hobbit:Hobbit) = hobbit.otherStuff
//  def  destroyTheRing(hobbit:Hobbit) = hobbit.precious
}
class Hobbit {
  private val otherStuff: String = ""
  private[this] val precious: String = "the Ring"

  private def showSomeStuff() = otherStuff

  private[this] def lookAtPrecious() = {
  }

  def visit(bilbo: Hobbit) = {
    bilbo.showSomeStuff()
//    bilbo.lookAtPrecious()
  }
}
