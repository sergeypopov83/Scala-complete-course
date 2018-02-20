package lectures.cat

object RigOperationProperties {

  /**
    *
    * Свойсвта арифметических операций полугруппы (RIG),
    * выраженные через произведения и сложение на типах
    *
    **/
  private def associateRight[A, B, C](prm: Either[Either[A, B], C]): Either[A, Either[B, C]] = prm match {
    case Left(e) => e match {
      case Left(a) => Left[A, Either[B, C]](a)
      case Right(b) => Right[A, Either[B, C]](Left[B, C](b))
    }
    case Right(c) => Right[A, Either[B, C]](Right[B, C](c))
  }

  private def associateLeft[A, B, C](prm: Either[A, Either[B, C]]): Either[Either[A, B], C] = prm match {
    case Right(e) => e match {
      case Left(b) => Left[Either[A, B], C](Right[A, B](b))
      case Right(c) => Right[Either[A, B], C](c)
    }
    case Left(c) => Left[Either[A, B], C](Left[A, B](c))
  }

  private def swap[A, B](tpl: (A, B)): (B, A) = (tpl._2, tpl._1)

  /**
    * с ⊕ b ≃ b ⊕ c  (коммутативность сложения)
    *
    * @return
    */
  def coproductCommutativity(): Boolean = ???

  /**
    * b ⛌ c  ≃ с ⛌ b   (коммутативность умножения)
    *
    * @return
    */
  def productCommutativity[A, B](tpl1: (A, B), tpl2: (B, A)): Boolean = {
    swap(tpl1) == tpl2 && swap(tpl2) == tpl1
  }

  /**
    * (a ⊕ b) ⊕ c  ≃ a ⊕ (b ⊕ c) (ассоциативность слжения)
    *
    * @return
    */
  def coproductAssociativity[A, B, C](p: Either[Either[A, B], C], p2: Either[A, Either[B, C]]): Boolean = {
    associateRight(p) == p2 && associateLeft(p2) == p
  }

  /**
    * (a ⛌ b) ⛌ c  ≃ a ⛌ (b ⛌ c) (ассоциативность  умножения)
    *
    * @return
    */
  def productAssociativity(): Boolean = ???

  /**
    * a ⛌ (b ⊕ c)  ≃ (a ⛌ b)  ⊕ (a ⛌ c)  (дистрибутивный закон)
    *
    * @return
    */
  def distributivity[A, B, C](first: (A, Either[B ,C]), second: Either[(A, B),(A, C)]): Boolean = {
   val firstCheck: Either[(A, B),(A, C)] = first._2 match{
      case Left(b) => Left((first._1, b))
      case Right(c) => Right((first._1, c))
   }
    firstCheck == second
    // Реализуйте вторую часть изморфизма и составьте полное условие
    ???
  }

  /**
    * 1⛌x ≃ x  ≃ x⛌1 (умножение на с 1)
    *
    * @return
    */
  def productWithOne(): Boolean = ???

  /**
    * x ⊕ 0 ≃ x ≃ 0 ⊕ x (сложение с 0)
    *
    * @return
    */
  def sumWithZero(): Boolean = ???

  /**
    * 0⛌x ≃ 0 ≃ x⛌0 (умножение на с 0)
    *
    * Рализовать этот метод невозможно т.к.
    * невозможно передать инстанс "ничего"
    *
    * Но можно рассуждать логически:
    * Сколько произвеений такого вида(T, "ничего") - нисколько, т.к. невозможно передать "ничего"
    * Сколько произвеений такого вида ("ничего", T) - столько же
    * И "ничего" тоже нет, т.е. это точно равномощьные множества
    *
    * @return
    */
  def productWithZero(): Boolean = ???

}
