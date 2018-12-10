package lectures.reflection

object RetrievingClassInfoWithJava extends App {

  def printInfo[T](cl: Class[T]) = {

    println(s"FIELDS INFO FOR ${cl.getCanonicalName}")
    println(s"All Fields")
    val fields = cl.getFields
    for(f <- fields) {
      println(f.getName)
    }

    println(s"Declared fields")
    val declaredFields = cl.getFields
    for(f <- declaredFields) {
      println(f.getName)
    }

    println(s"\n\nMETHOD INFO FOR ${cl.getCanonicalName}")
    println(s"All Methods")
    for(f <- cl.getMethods) {
      println(f.getName)
      println(s"Annotation info for ${f.getName} ${f.getDeclaredAnnotations.map(_.annotationType().getCanonicalName()).mkString(",")}")
    }

    println(s"Declared Constructors")
    for(f <- cl.getDeclaredConstructors) {
      println(f.getName)
    }

    println(s"declared methods")
    for(f <- cl.getDeclaredMethods) {
      println(f.getName)
      println(s"Annotation info for ${f.getName}  ${f.getDeclaredAnnotations.map(_.annotationType().getCanonicalName).mkString(",")}")
    }

    println(s"\n\nANNOTATION INFO FOR ${cl.getCanonicalName}")
    for(f <- cl.getAnnotations) {
      println(f.toString)
    }
  }


  val jrAnonTraitExample = new ReflectExampleTrait {
    override protected val field: Int = 100
  }.getClass
  val jrExample = new ReflectExample().getClass
  val objectExample = JavaReflectExampleObject.getClass
  val traitClass = classOf[ReflectExampleTrait]

  printInfo(traitClass)
//  printInfo(jrAnonTraitExample)
//  printInfo(jrExample)
//  printInfo(objectExample)

}
