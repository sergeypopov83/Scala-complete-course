package lectures.reflection

import scala.reflect.runtime.{universe => ru}
import ru._

object ScalaReflection extends App {

  // получение информации о классе
  val reflectExampleTraitCls = classOf[ReflectExampleTrait]
  val reflectExampleCls = classOf[ReflectExample]
  val mirror = ru.runtimeMirror(this.getClass.getClassLoader)

  // получение информации о типе
  val reflectExampleTpe = typeOf[ReflectExampleTrait]
  val reflectExTpe = typeOf[ReflectExample]
  val declarations = reflectExTpe.decls
  val baseClasses = reflectExTpe.baseClasses
  assert(reflectExTpe <:< reflectExampleTpe)

  // получение информации о типе и классе из инстанса
  val exampleInst = new ReflectExample
  val classSymbol = mirror.classSymbol(exampleInst.getClass)
  val typeFromSymbol = classSymbol.asType.toType
  assert(reflectExTpe =:= typeFromSymbol)

  // Динамическое сосздание инстанса класса
  // Если попробовать созать инстанс трейта, будет ошибка
  // val traitClassSymbol = mirror.classSymbol(reflectExampleTraitCls)
  val traitClassSymbol = mirror.classSymbol(reflectExampleCls)
  val classMirror = mirror.reflectClass(traitClassSymbol)
  val constructor = traitClassSymbol.asType.toType.decl(ru.termNames.CONSTRUCTOR).asMethod
  val reflectedConstructorMirror = classMirror.reflectConstructor(constructor)
  val dynamicInst = reflectedConstructorMirror.apply()
}
