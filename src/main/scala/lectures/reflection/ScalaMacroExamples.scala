package lectures.reflection

import scala.reflect.macros.whitebox

object ScalaMacroExamples{
  def bundledMacro(prm: String): Any = macro BundledMacroExample.generateMore
  def scalaMacro(prm: String): Any = macro ExampleMacro.generate
  def describer[T] = macro DescriberMacro.describer[T]
}

object ExampleMacro {

 def generate(c: whitebox.Context)(prm: c.Tree): c.Expr[Int] = {
  import c.universe._
   reify(10)
 }
}
class BundledMacroExample(val c: whitebox.Context) {
  import c.universe._

  def generateMore(prm: Tree): Expr[Int] = {
    reify(10)
  }
}
