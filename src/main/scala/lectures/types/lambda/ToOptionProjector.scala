package lectures.types.lambda
import lectures.types.lambda._


/**
  * Объект LeftToOptionProjector превращает все левые проекции Left[A] от Either[A, _] в Some[A], а правые в None
  *
  * @tparam F
  */

abstract class ToOptionProjector[F[_]] {
  type I[_] = F[_]
  def projectEither[A](va: I[A]): Option[A]
}

object TypeDefinitionExample {
  type λ[α] = Either[α, Any]
  ???
}

object LeftToOptionProjector extends ToOptionProjector[({type λ[α] = Either[α, _]})#λ] {
  def projectEither[A](va: I[A]): Option[A] = va match {
    case l: Left[A, _] => Option(l.value)
    case _ => None
  }
}



