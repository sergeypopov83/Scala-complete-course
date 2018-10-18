package lectures.types

/**
  * SeqTransformer превращает Seq параметров в инстанс произвольного типа G[_]
  * Тип G[_] определяется внутри каждого конкретного типа, наследника SeqTransformer
  *
  */
trait SeqTransformer {
  type G[_]
  def transform[T](vals: Seq[T]): G[T]
}

object SeqTransformer {
  def transform[T](vals: T*)(implicit tr: SeqTransformer): tr.G[T] =
    tr.transform[T](vals)
}

class SeqToVectorTransformer() extends SeqTransformer {

  override type G[A] = Vector[A]

  override def transform[T](vals: Seq[T]): G[T] = vals.toVector
}

