package lectures.types

import java.time.{Clock, Instant}

sealed trait PayCheckStatus

trait New extends PayCheckStatus
trait Sent extends PayCheckStatus
trait Payed extends PayCheckStatus
trait Overdue extends PayCheckStatus

case class PayCheck[T <: PayCheckStatus](id: String, creationDate: Instant = Clock.systemDefaultZone().instant())

trait PayCheckStatusProcessor[T <: PayCheckStatus] {
  def isValid(check: PayCheck[T]): Boolean

  def process(check: PayCheck[T]): Unit
}

object CheckProcessor {

  implicit val validPc: PayCheckStatusProcessor[Payed] = new PayCheckStatusProcessor[Payed] {
    override def isValid(check: PayCheck[Payed]): Boolean = true

    override def process(check: PayCheck[Payed]): Unit =
      print("paycheck was cleared")
  }

  implicit val overduePc: PayCheckStatusProcessor[Overdue] = new PayCheckStatusProcessor[Overdue] {
    override def isValid(check: PayCheck[Overdue]): Boolean = true

    override def process(check: PayCheck[Overdue]): Unit =
      print("paycheck is in overdue state")
  }
}

class CheckProcessor {

  def createACheck(): PayCheck[New] = PayCheck[New]("1234")

  def sendACheck(check: PayCheck[New]): PayCheck[Sent] = {
    println("Send a check")
    check.copy().asInstanceOf[PayCheck[Sent]]
  }

  def overdueCheck(check: PayCheck[Sent]): PayCheck[Overdue] = {
    println("Overdue a check")
    check.copy().asInstanceOf[PayCheck[Overdue]]
  }

  def clearCheck(check: PayCheck[Sent]): PayCheck[Payed] = {
    println("Clear a check")
    check.copy().asInstanceOf[PayCheck[Payed]]
  }

  def processStatus[T <: PayCheckStatus](check: PayCheck[T])(implicit pcs: PayCheckStatusProcessor[T]): Unit = pcs.process(check)

}
