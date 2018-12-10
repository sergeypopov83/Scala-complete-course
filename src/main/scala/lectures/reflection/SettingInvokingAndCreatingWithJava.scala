package lectures.reflection

import java.lang.reflect.InvocationTargetException
import java.util.Locale

object SettingInvokingAndCreatingWithJava extends App{

 val inst =  new ReflectExample()
 val clazz = classOf[ReflectExample]
 val m = clazz.getDeclaredMethod("identity", classOf[Any])

  try {
    m.setAccessible(true)
    val o = m.invoke(inst, Seq(10))
    print(s"Result of reflect call $o")
    // Handle any exceptions thrown by method to be invoked.
  } catch {
  case x: InvocationTargetException =>
  val cause = x.getCause
  println(s"invocation of %s failed: ${m.getName} cause: ${cause.getMessage}")
}
  val field = clazz.getDeclaredField("field")
  try{
    field.setAccessible(true)
    field.set(inst, 40)
    val afterSet  = inst.field
    println("New Value of field `field` " + afterSet)
  }

  val const = clazz.getConstructor(Seq[Class[_]](): _*)
  val reflectiveInst = const.newInstance()
  print(s" $reflectiveInst")
}
