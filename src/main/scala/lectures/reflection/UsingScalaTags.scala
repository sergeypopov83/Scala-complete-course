package lectures.reflection

import scala.reflect.runtime.{universe => ru}
import ru._

object UsingScalaTags extends App{

  val tt = typeTag[Int]
  val exampleInst = new ReflectExample()

  def weakParamInfo[T](x: T)(implicit tag: WeakTypeTag[T]): Unit = {
    val targs = tag.tpe match {
      case TypeRef(_, _, args) =>
         args
    }
    println(s"type of $x has type arguments $targs")
  }

  def paramInfo[T](x: T)(implicit tag: TypeTag[T]): Unit = {
    val targs = tag.tpe match {
      case TypeRef(_, _, args) => args
    }
    println(s"type of $x has type arguments $targs")
  }

  def patternMathWithTypeTag[T:  TypeTag](t: T): Unit = t match {
    case i: List[Int] => println(s"TAGS are great! $i")
    case s: List[String] => println(s"TAGS are great! $s")
    case _ => print("or not?!")
  }

  def weakTypeTagExample[T](): Unit = weakParamInfo(List[T]())

  //def fooError[T] = paramInfo(List[T]())
  //fooError[T]

  weakTypeTagExample[Int]
  weakParamInfo(List[Int]())
  patternMathWithTypeTag(List(1,2,3))
  patternMathWithTypeTag(List("a","b","c"))

}
