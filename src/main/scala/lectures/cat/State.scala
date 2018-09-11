package lectures.cat

import cats.data.{State, StateT}
import cats.implicits._
import scala.util.Random

/**
  * Example copypasted from scalaz
  */
class StateStack {
  type Stack = List[Int]

  val pop: State[Stack, Int] = State[Stack, Int]{
    case Nil => (Nil, 0)
    case x :: xs => (xs, x)
  }

  def push(a: Int): State[Stack, Unit] = State[Stack, Unit] {
    xs => (a :: xs, ())
  }

  def stackManip: State[Stack, Int] = for {
    _ <- push(3)
    a <- pop
    b <- pop
  } yield a + b

}

/**
  * StateForN -  генерирует случайное число и передает его в функцию
  */
class StateForN {
  case class FoldState[S, T](s: S, list: List[T])
  type SState = (Int, Int)

  def forRandomN[Res](f:(Int) => Res): StateT[Option, SState, Res] = {
    val ff = (s: SState) => {
      if (s._2 <= 0)
        None
      else
        Some[(SState, Res)](((Random.nextInt(), s._2 - 1), f(s._1)))
    }

    StateT(ff).flatMap{ _: Res => forRandomN(f)}
  }
}

object StateExample extends App {
  val res = new StateStack().stackManip
   println(res.runS(List(5)).value)
   println(res.runA(List(5)).value)

  val forN = new StateForN
  val nState = forN.forRandomN((i: Int) => println(i))
  nState.run((10,10))
}