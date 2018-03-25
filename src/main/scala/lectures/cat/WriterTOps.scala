package lectures.cat

import cats.data.WriterT
import cats.{FlatMap, Applicative => CatsApplicative, Monoid => CatsMonoid}

object WriterTOps {

  implicit class SeqWriterTOps[V](list: Seq[V]) {

    def logActions() = ???

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
