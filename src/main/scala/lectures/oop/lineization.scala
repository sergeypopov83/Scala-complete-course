package lectures.oop

abstract class AbstractTest {
  val name: String = ""
  println("Deepest level of inheritance: AbstractTest")

  def whoAmI()
}

class NameProvider extends AbstractTest {

  override val name: String = "name provided by trait"
  println("One level up: NameProvider")

  override def whoAmI() = {
    println(s"name is `$name` in NameProvider")
  }
}

trait AnotherNameProvider extends AbstractTest {

  override val name: String = "name provided by another trait"
  println("Almost on the surface: AnotherNameProvider")

  abstract override def whoAmI() = {
    println(s"name is `$name` in AnotherNameProvider")
    super.whoAmI()
  }
}

class ConcreteClass extends NameProvider with AnotherNameProvider {

  override val name: String = "Concrete class"
  println("The uppermost level: ConcreteClass")

  override def whoAmI() = {
    println(s"name is `$name` in Concrete class")
    super.whoAmI()
  }
}

object InheritanceTest extends App {
  val k = new ConcreteClass()
  k.whoAmI()
  val n = k.name
}

/*
Deepest level of inheritance: AbstractTest
One level up: NameProvider
Almost on the surface: AnotherNameProvider
The uppermost level: ConcreteClass
name is `Concrete class` in Concrete class
name is `Concrete class` in AnotherNameProvider
name is `Concrete class` in NameProvider
*/












