package lectures.concurrent.coroutines

import org.scalatest.concurrent.Eventually
import org.scalatest.time.{Seconds, Span}
import org.scalatest.{FunSuite, Matchers}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.async.Async.{async, await}
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

class ContinuationsExampleTest extends FunSuite with Matchers with Eventually {

  implicit val pc = PatienceConfig(timeout = Span(10, Seconds), interval = Span(3, Seconds))
  test("shoud do it right") {
    def slowCalcFuture: Future[Int] = Future{
      Thread.sleep(1000)
      10
    }

    /**
      * Метод sync - это макрос, который превратит тело своего метода в
      * код A - нормального вида, помещенный в state машину
      * Ниже показан код, котороый нужен что бы лучше понять, что примерно происзодит
      * При этом действительный код будет отличтаться
      *
      * slowCalcFuture.zip(slowCalcFuture).map { tpl =>
      *  val callback = (i:Int, j: Int) = i + j
      *  val(slowRes1, slowRes2) = tpl
      *  callback(slowRes1, slowRes2)
      * }
      *
      * @return
      */
    def combined: Future[Int] = async {
      await(slowCalcFuture) + await(slowCalcFuture)
    }

//    eventually {
//      combined.isCompleted shouldBe true
//      combined.value.get.get shouldBe 20
//    }
     val x: Int = Await.result(combined, 10.seconds)
     x shouldBe 20
  }

}
