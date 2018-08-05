package lectures.cat

import java.util.Scanner

import cats._
import cats.free.Free

/**
  * IO очень просто реализовать с помощью Free
  * Ваше задача
  * * * * Дополнить доменную модель
  * * * * Реализовать интерпертатор
  *
  * Так, что бы IOIsFree печатало в консоль реультат запуска program
  */
sealed trait IO4Free[+R]

object IO4Free {

  import cats.free.Free._

  val interpreter = new (IO4Free ~> Id) {
    override def apply[A](fa: IO4Free[A]): Id[A] = ???
  }

  implicit def toIO[A](f: () => A): Free[IO4Free, A] = ???
  implicit def toFree[A](iof: IO4Free[A]): Free[IO4Free, A] = liftF(iof)

  def computationBasedOnInput(i: Int): Free[IO4Free, String] = for {
    v <- () => 10 * i
    r <- () => " and the result is "
  } yield r + v
}

object IOIsFree extends App {

  import IO4Free._

  val program = for {
    _ <- () => println("Start computation")
    v <- () => new Scanner(System.in).nextInt()
    calcResult <- computationBasedOnInput(v)
  } yield calcResult

  print(program.foldMap(interpreter))
}