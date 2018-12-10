package lectures.reflection

import java.net.URL

class DocAnnotation(val doc: String) extends scala.annotation.StaticAnnotation

@DocAnnotation("BASE trait for java reflection examples")
trait ReflectExampleTrait {
  protected val field: Int
  @deprecated
  private var privateField: String = ""

  val publicField: Long = 0

  def const(): Unit = field
}

object JavaReflectExampleObject extends ReflectExampleTrait {
  override protected val field: Int = 30
}

class ReflectExample extends ReflectExampleTrait {

  private def identity[T](t: T): T = t

  override val field: Int = 20
}

object PlainJavaReflection extends App {

  // анализ полей и членов классов
  val jert1 = new ReflectExampleTrait(){
    override protected val field: Int = 100
  }
  // полчуение класса по инстансу
  val classByInstance = jert1.getClass
  // по типу объектса
  val classByObject = BigDecimal.getClass
  // по строковому имени тип
  val classForName = Class.forName("lectures.reflection.JavaReflectExample")
  // у массивов нет связанных с ними инстансов Class
  val array: Array[Int] = Array(1,2,3,4)
  // gc здесь буде равен Null
  val gc = array.getClass

  //получение класслоадера
  val loaderFroBigDecimal = classByObject.getClassLoader
  val loaderForJavaReflectionExample = classForName.getClassLoader
  val systemClassLoader = ClassLoader.getSystemClassLoader

 val thread = new Thread{
    override def start(): Unit = {
      super.start()
      val threadContextClassLoader = Thread.currentThread().getContextClassLoader() //is the key !
      print(threadContextClassLoader)
    }
  }
  thread.start()

  // ресурсы относительно класслоадера и класса
  val forNameResource: URL = classForName.getResource("forName.txt")
  val getClassResource: URL  = this.getClass().getResource("forName.txt")
  val relativeToClassLoader: URL  = this.getClass().getResource("./forName.txt")
  val relativeToClassLoader3: URL = Thread.currentThread().getContextClassLoader().getResource("forContext.txt")
  val relativeToClassLoader4: URL = classForName.getClassLoader.getResource("forContext.txt")


  for( i <- 1 to 20 ){
    Thread.sleep(100)
    Thread.`yield`()
  }
}
