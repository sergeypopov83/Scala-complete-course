package lectures.types


/**
  * SeqTransformerWithAux
  *
  * Задание:
  * Реализуйте инстанс ValueTransformer, который совершает любую трансформацию над типом T по вашему выбору.
  * Напишите тест в AuxPatternTest.scala
  *
  */
trait SeqTransformerWithAux {
  type G[_]
  def transform[T](vals: T*): G[T]

}

/**
  * Инстансы этого типа умеют преобразовывать G[T]
  */
trait ValueTransformer[T, G[_]] {
  def transformValue(v: T): T
}

object SeqTransformerWithAux {
  type Aux[G1[_]] = SeqTransformerWithAux{ type G[T] = G1[T]}
  def transform[T, G[_]](vals: T*)(implicit tr: SeqTransformerWithAux.Aux[G], valueTransformer: Option[ValueTransformer[T, G]] = None): tr.G[T] =
    tr.transform[T](vals: _*)
}

class SeqToVectorTransformerWithAux extends SeqTransformerWithAux{
  type G[T] = Vector[T]
  override def transform[T](vals: T*): G[T] = vals.toVector
}