package lectures.functions

import scala.util.Random

/**
  * Created by s.popov2 on 10.06.2016.
  */

trait Credentials

object CardCredentials {
  def apply(): CardCredentials = CardCredentials((Math.random()* 1000).toInt )
}
case class CardCredentials(cardNumber: Int) extends Credentials

object LPCredentials {
  def apply(): LPCredentials = LPCredentials(Random.alphanumeric.take(10).mkString, Random.alphanumeric.take(10).mkString)
}
case class LPCredentials(login: String, passwordHash: String) extends Credentials

trait User {
  def id: Int
  def credentials: Credentials
}
case class AnonymousUser() extends User {
  def id = throw new Exception("Anonymous user have no identification")
  def credentials = throw new Exception("Anonymous user have no credentials")
}

object CardUser {
  def apply(): CardUser = CardUser((Math.random() * 5).toInt , CardCredentials())
}
case class CardUser(id: Int, credentials: CardCredentials) extends User

object LPUser {
  def apply(): LPUser = LPUser((Math.random() * 5).toInt , LPCredentials())
}
case class LPUser(id: Int, credentials: LPCredentials) extends User

object AuthenticationData {

  val cardUserCreds = CardCredentials(12345678)
  val cardUserCreds2 = CardCredentials(87654321)
  val authUserCreds = LPCredentials("qwerty", "qwerty")
  val authUserCreds2 = LPCredentials("qwerty2", "qwerty2")

  val registeredCards: Set[CardCredentials] = Set(
    CardCredentials(), CardCredentials(), CardCredentials(), cardUserCreds,
    CardCredentials(), CardCredentials(), CardCredentials(), CardCredentials(),
    CardCredentials(), CardCredentials(), cardUserCreds2
  )

  val registeredLoginAndPassword: Set[LPCredentials] = Set(
    LPCredentials(), LPCredentials(), LPCredentials(), LPCredentials(),
    LPCredentials(), LPCredentials(), LPCredentials(), LPCredentials(),
    LPCredentials(), LPCredentials(), LPCredentials(), LPCredentials(),
    LPCredentials()
  )

  val testUsers = List[User](
    AnonymousUser(), AnonymousUser(), AnonymousUser(), AnonymousUser(), AnonymousUser(),
    AnonymousUser(), AnonymousUser(), AnonymousUser(), AnonymousUser(), AnonymousUser(),
    AnonymousUser(), AnonymousUser(), AnonymousUser(), AnonymousUser(), AnonymousUser(),
    CardUser(), CardUser(), CardUser(), CardUser(), CardUser(), CardUser(), CardUser(),
    CardUser(), CardUser(), CardUser(), CardUser(), CardUser(), CardUser(), CardUser(),
    CardUser(), CardUser(), CardUser(), CardUser(), CardUser(), CardUser(), CardUser(),
    LPUser(), LPUser(), LPUser(), LPUser(), LPUser(), LPUser(), LPUser(), LPUser(), LPUser(),
    LPUser(), LPUser(), LPUser(), LPUser(), LPUser(), LPUser(), LPUser(), LPUser(), LPUser(),
    LPUser(), LPUser(), LPUser(), LPUser(), LPUser(), LPUser(),
    LPUser(1234, authUserCreds), LPUser(4567, authUserCreds2),
    CardUser(4567, cardUserCreds), CardUser(45679, cardUserCreds2)
  )
}