package lectures.types
import scala.reflect.runtime.universe._

object KindProjectorExample extends App{

  type E2[T] = Either[T, _]

 //Так не сработает т.к. Either[T, ?] == [A]Either[String, A], т.е один парамер не связанн
 //type ENop[T] = Either[T, ?]
   val l:List[_] = List(1,2,3,5)
   val l2 = List(1,2,3,5)
  type E1[I] = Either[String, I]

//  val f = new Functor[(_, _)]{
//    override def map[A, B](fa: Tuple1[A])(f: A => B): Tuple1[B] = ???
//  }
//
  def identity[F[_], A](implicit tt: TypeTag[F[A]]): Unit = {
    println(tt.tpe.toString)
    println(tt.tpe.dealias.toString)
  }

  def identityPrm[F[_], A](x:F[A])(implicit tt: TypeTag[F[A]]): Unit = {
    println(tt.tpe.toString)
    println(tt.tpe.dealias.toString)
  }

//  identity[({type E2[A] = Either[_, A]})#E2, Int]
//  identity[E2, Int]
//  identity[Either[_, ?], Int]

  identityPrm(l)
  identityPrm(l2)
}
