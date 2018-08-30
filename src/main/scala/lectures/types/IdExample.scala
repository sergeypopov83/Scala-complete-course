package lectures.types

import lectures.types.Id.Id
import shapeless.tag.@@

object Id {
  def apply[U](str: String): Id[U] = str.asInstanceOf[Id[U]]

  type Id[U] = String @@ U
}

object SubscriberService {
  class Subscriber
  class Contract
  class Balance

  // confusing untyped signature
  def increaseBalance(subId: String, sourceContract: String, balanceId: String, sum: Double) = ???

  // more
  def increaseBalanceTyped(subId: Id[Subscriber], sourceContract: Id[Contract], balanceId: Id[Balance], sum: Double) =
    increaseBalance(subId, sourceContract, balanceId, sum)

  val subId = Id[Subscriber]("1")
  val sourceContract = Id[Contract]("2")
  val balanceId = Id[Balance]("3")
  val sum = 100
  increaseBalance(subId, sourceContract, balanceId, sum)
  increaseBalanceTyped(subId, sourceContract, balanceId, sum)
  increaseBalance(sourceContract, subId, balanceId, sum) // ERROR!!!
//  increaseBalanceTyped(sourceContract, subId, balanceId, sum) // won't compile

}