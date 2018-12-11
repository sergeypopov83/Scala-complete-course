package lectures.reflection

import scala.reflect.macros.whitebox

object ScalaMacroExamples{
  def bundledMacro(prm: String): Any = macro BundledMacroExample.generateMore
  def scalaMacro(prm: String) = macro ExampleMacro.generate
  def scalaMacroT[T](prm: String): T = macro ExampleMacro.generateT[T]
  def describer[T]: String = macro DescriberMacro.describer[T]
}

object ExampleMacro {

 def generateT[T : c.WeakTypeTag](c: whitebox.Context)(prm: c.Tree): c.Expr[T] = {
  import c.universe._
   val r = null.asInstanceOf[T]
   c.Expr[T](Literal(Constant(r)))
 }

  def generate(c: whitebox.Context)(prm: c.Tree): c.Expr[Any] = {
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
