package lectures.cat

import cats.data.{Writer, WriterT}
import org.scalatest.{Matchers, WordSpec}
import lectures.cat.WriterTOps._
import cats.{FlatMap, Id, Applicative => CatsApplicative, Monoid => CatsMonoid}

import scala.util.Random

// класс хранящий метрики выполнения программы
case class Metrics(cnt: Int = 0, log: String = "")

/**
  * Реализуйте последний тест в классе SeqWriterTOpsTest
  * Дан класс Metrics, сдежащий поля
  * * * * cnt - счетчик
  * * * * log  - текстовый лог
  * Ваша задача создасть инсанс Writer и вызвать n раз flatMap с методом next,
  * таким образом, что после выполнения всех операций в результате Metrics.cnt содержал число n
  *
  */
class SeqWriterTOpsTest extends WordSpec with Matchers {

  implicit val im: cats.Monoid[Int] = new CatsMonoid[Int] {
    override def empty: Int = 0

    override def combine(x: Int, y: Int): Int = x + y
  }

  implicit def seqMonoid[T]: cats.Monoid[Seq[T]] = new CatsMonoid[Seq[T]] {
    override def empty: Seq[T] = Seq.empty[T]

    override def combine(x: Seq[T], y: Seq[T]): Seq[T] = x ++ y
  }

  implicit def strMonoid: cats.Monoid[String] = new CatsMonoid[String] {
    override def empty: String = ""

    override def combine(x: String, y: String): String = x + y
  }

  implicit val sa: CatsApplicative[Seq] = new CatsApplicative[Seq] {
    override def pure[A](x: A): Seq[A] = Seq(x)

    override def ap[A, B](ff: Seq[A => B])(fa: Seq[A]): Seq[B] = ff.headOption.map(f => fa.map(f)).getOrElse(Seq.empty)
  }

  implicit val fm = new FlatMap[Seq] {
    override def flatMap[A, B](fa: Seq[A])(f: A => Seq[B]): Seq[B] = fa.flatMap(f)

    override def tailRecM[A, B](a: A)(f: A => Seq[Either[A, B]]): Seq[B] = ???

    override def map[A, B](fa: Seq[A])(f: A => B): Seq[B] = fa.map(f)
  }

  "WriterT provide some constrained fold abilities" in {

    val r = Seq(1, 2, 3, 4, 4).filterW(_ > 2)

    r shouldBe Seq(3, 4, 4)

  }
  "WriterT is good in collectinng monoidal L values" in {

    val str = logActions
    str.split("\n").last shouldBe "Number are equal"

    print(str)
  }

  /*
   * Замените знаки вопроса реализацией так, что бы тест прошел
   */
  "WriterT would count how many times combie would be executed" in {
    val n = Random.nextInt(100)
    val next = (v: Int) => {
      Writer(Metrics(0, s"next value is $v"), v)
    }
   val result: Writer[Metrics, Int] = ???

   n shouldBe result.written.cnt
  }
}

