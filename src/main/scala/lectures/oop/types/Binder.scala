package lectures.oop.types

import scala.reflect.runtime.{universe => ru}
import ru._


/**
  * Идем дальше.
  * Определим трейт, который будет обозначать группу методов
  * для создания инстансов коллекций
  * При этом нам пока не важно, каких
  *
  */

trait Binder3[M[_]] {
  def bind[T](item: T): M[T]
  def fill[T](count: Int, item: T): M[T]
}

class SeqBinder extends Binder3[Seq] {
  override def bind[T](item: T): Seq[T] = Seq(item)
  override def fill[T](count: Int, item: T): Seq[T] = Seq.fill(count)(item)
  def badFill(count: Int, item: (T) forSome {type T}): Seq[_] = Seq.fill(count)(item)
}

class SetBinder extends Binder3[Set] {
  override def bind[T](item: T): Set[T] = Set(item)
  override def fill[T](count: Int, item: T): Set[T] = Set(Seq.fill(count)(item): _*)
}

class ConcreteSetBinder[C] extends Binder3[Set] {
  def bind[T](item: T): Set[T] = Set(item)
  def concreteBind(item: C): Set[C] = Set(item)
  override def fill[T](count: Int, item: T): Set[T] = Set(Seq.fill(count)(item): _*)
}

object Binder4 {
  trait Builder[M[_]] {
    def build[T](item: T): M[T]
  }

  implicit var seqBuilder = new Builder[Seq] {
    override def build[T](item: T): Seq[T] = Seq(item)
  }
  implicit val setBuilder = new Builder[Set] {
    override def build[T](item: T): Set[T] = Set(item)
  }

  def bind[T, B[_]](item: T)(implicit builder: Builder[B]): B[T] = {
    builder.build(item)
  }

  def fill[T, B[_]](count: Int, item: T)(implicit builder: Builder[B]) = ???
}

object BinderExample extends App {
  import Binder4._

  def withType[T:TypeTag, ClassTag](t: T): Unit = {
    val tt = typeOf[T]
    println(tt.typeParams)
  }

  val strBinder = new ConcreteSetBinder[String]()
  strBinder

  withType(new SeqBinder)
  (new SeqBinder).bind(100)
  (new SetBinder).bind(100)
  (new SeqBinder).fill(10, 100)
  (new SeqBinder).badFill(10, 100)
  (new SetBinder).fill(10, 100)
  //но вот так мы сделать не можем
  //val b = new Binder3[List]()

  val s = bind[Int, Set](10)

  print(s)
}
