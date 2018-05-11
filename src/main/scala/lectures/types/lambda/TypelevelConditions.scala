package lectures.types.lambda

import lectures.types.lambda.Booleans.{FALSE, TRUE}


/**
  * Пример применения лямбда исчисления для вычисления логических выражений на типах
  * В данном случае кейс классы TRUE, FALSE, IF, AND, являются полными аналогами соответвующих термов лямбда исчисления
  *
  * BooleanSupplementary - набор вспомогательных средств
  *
  * Задача
  * * * * реализовать терм NOT
  * * * * рРеализовать терм OR
  * * * * раскомментировать и дописать тесты в lectures.types.lambda.TypelevelConditions
  */
object Booleans {

  trait BOOL {
    type a[t <: BOOL, f <: BOOL] <: BOOL
    type v = a[TRUE, FALSE]
  }

  final class TRUE extends BOOL {
    type a[t <: BOOL, f <: BOOL] = t
  }

  final class FALSE extends BOOL {
    type a[t <: BOOL, f <: BOOL] = f
  }

  trait IF[x <: BOOL, y <: BOOL, z <: BOOL] extends BOOL {
    type a[t <: BOOL, f <: BOOL] = x#a[y, z]#a[t, f]
  }

  trait AND[x <: BOOL, y <: BOOL] extends BOOL {
    type a[t <: BOOL, f <: BOOL] = IF[x, y, FALSE]#a[t, f]
  }

  trait NOT[x <: BOOL] extends BOOL {
    type a[t <: BOOL, f <: BOOL] = FALSE // Необходимо правильно реализовть
  }

  trait OR[x <: BOOL, y <: BOOL] extends BOOL {
    type a[t <: BOOL, f <: BOOL] = FALSE // Необходимо правильно реализовть
  }

  // aliases for nicer syntax
  //  type ![x <: BOOL] = NOT[x]
  //  type ||[x <: BOOL, y <: BOOL] = OR[x, y]
  type &&[x <: BOOL, y <: BOOL] = AND[x, y]
}

object BooleanSupplementary {

  /**
    * Создает вспомогательный инстанс нужного типа
    * Созданный инстанс заставляет выполнится одно из имплиситных преобразований, описанных ниже
    *
    * @tparam B
    * @return
    */
  def IF[B]: B = null.asInstanceOf[B]

  /**
    * Если вычисленный логический тип  == TRUE
    * Имплиситно будет вызван apply TrueBranch, который выполнит переданные действия
    *
    * @param t
    * @return
    */
  implicit def envokeTrueBranch(t: TRUE): TrueBranch = new TrueBranch

  /**
    * Если вычисленный логический тип == FALSE
    * Имплиситно будет вызван apply FalseBranch, который напишет в консоль сообщение FALSE
    *
    * @param f
    * @return
    */
  implicit def exclude(f: FALSE): FalseBranch.type = FalseBranch

  class TrueBranch {
    def apply(block: => Any): Any = block
  }

  object FalseBranch {
    def apply(block: => Any): Any = {
      false
    }
  }

}