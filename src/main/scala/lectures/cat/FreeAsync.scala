package lectures.cat

import java.time.Clock

import cats.arrow.FunctionK
import cats.free.Free
import cats.{~>, Functor => FunctorC}
import cats.implicits._

import scala.concurrent.{ExecutionContext, Future}

/**
  * В программе ниже приведен пример
  * примитивного фреймворка, описывающего многопоточное приложение
  *
  * Программа сосотоит из нескольких основных частей
  * * * * Алгебраического типа данных Action, описывающего то,
  * * * * * как выполнять часть приложения, параллельно или последовательно
  * * * * Интерпретатора, непосредственно выполняющего программу
  * * * * Бизнес логики, описанной в терминах АТД
  *
  * * * * Задание1:
  * * * * * Parallel2 выполняет каждую функцию в отдельном новом потоке
  * * * * * Придумайте примитив, который будет гарантированно выполнять одну из функций
  * * * * * в том же потоке, в котором работает интерпретатор
  *
  * * * * Задание2:
  * * * * * Опишите программу поиска минимального элемента в бинарном дереве
  * * * * * с помощью терминов АДТ. Напишите тест
  *
  *
  * @tparam T
  */

trait Action[T]

case class Const[R](r: R) extends Action[R]

case class Sequential[R](f: () => R) extends Action[R]

case class Parallel2[R1, R2](f1: () => R1, f2: () => R2) extends Action[Tuple2[R1, R2]]

//case class Parallel3[I, A, B, C, R <: (A, B, C)](f1: I => A, f2: I => B, f3: I => C) extends Action[(I, R)]

object FreeAsync {
  implicit def toFree[R](a: Action[R]): Free[Action, R] = Free.liftF(a)
}

class FreeAsyncProgram(implicit ex: ExecutionContext) {

  import FreeAsync._

  val interpreter = new (Action ~> Future) {
    // continue here
    override def apply[A](fa: Action[A]): Future[A] = fa match {
      case Const(r) => Future.successful(r)
      case s: Sequential[A] => Future(s.f())
      case p: Parallel2[_, _] =>
        val f2 = Future {
          Thread.sleep(101)
          p.f2()
        }
        val f1 = Future {
          Thread.sleep(100)
          p.f1()
        }

        f1.zip {
          f2
        }.asInstanceOf[Future[A]]
    }
  }

  def doTheJob(): Future[String] = {
    val whoIsFaster = for {
      r1 <- Sequential[Clock](() => Clock.systemDefaultZone())
      r2 <- Parallel2(() => r1.instant(), () => r1.instant())
    } yield if (r2._1.isBefore(r2._2)) "Left branch wins" else "Right branch wins"
    whoIsFaster.foldMap(interpreter)
  }
}
