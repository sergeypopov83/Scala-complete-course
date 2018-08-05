package lectures.cat

import cats.data.{Writer, WriterT}
import cats.{FlatMap, Id, Applicative => CatsApplicative, Monoid => CatsMonoid}

object WriterTOps {

  private val action1 = (i: Int) => Writer[String, (Double, Int)](s"Pow initial number $i\n", (Math.pow(i, 2), i))
  private val action2 = (tpl: (Double, Int)) => Writer[String, (Double, Int)](s"SQRT powed number ${tpl._1}\n", (Math.sqrt(tpl._1), tpl._2))

  /**
    * Классический пример с логированием с writer monad
    *
    * @return
    */
  def logActions(implicit monoidL: CatsMonoid[String], flatMapF: FlatMap[Id]): String =
    (for {i <- WriterT.lift[Id, String, Int](10).tell("Start computation \n")
          powedTPL <- action1(i)
          srtTPL <- action2(powedTPL)
    } yield srtTPL._1 == srtTPL._2
      ).mapBoth((l, r) =>
      (if (r) l + "Number are equal" else "Numbers are not equal", r)
    ).written


  implicit class SeqWriterTOps[V](list: Seq[V]) {
    def filterW(predicate: V => Boolean)(implicit monoidL: CatsMonoid[Seq[V]], F: CatsApplicative[Seq], flatMapF: FlatMap[Seq]): Seq[V] = {
      val lifted = WriterT.lift[Seq, Seq[V], V](list)
      val vals = lifted.flatMap[V] { v =>
        val r = if (predicate(v)) Seq(v) else Seq.empty[V]
        WriterT(Seq((r, v)))
      }

      flatMapF.flatten {
        vals.written(F)
      }
    }

  }

}
