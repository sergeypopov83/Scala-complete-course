package lectures.oop

object SuperObject {}
trait SuperTrait {}
class SuperClass {
  val name = "SuperClass"
  protected val secretName = "secret"
}
class SubClass extends SuperClass {
  def printMySecretName = secretName
}
class SubClassWithTrait extends SuperTrait {}
//you can't extends object
//class SubClassByObject extends SuperObject{}
//
class TestApp extends App {
  val sc = new SubClass()
  sc.name
//  sc.secretName
  sc.printMySecretName
}

