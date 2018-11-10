package lectures.check

import java.time.Instant
import java.util.UUID

import org.scalatest.Matchers._
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{FlatSpec, Matchers}
import org.mockito.Mockito._
import org.mockito.Matchers.any

case class Card(cardNumber: String, expireDate: Instant)

case class Account(id: UUID, balance: Double, cards: Seq[Card])

case class User(id: UUID)

trait CardsService {

  def getCardsOfAccount(accountId: UUID): Seq[Card]
}

trait BalanceService {

  def getBalanceOfAccount(accountId: UUID): Double
}

trait AccountsService {

  def getAccounts(user: User): Seq[Account]
}

trait AccountVerificationService {

  def isBlockedAccount(accountId: UUID): Boolean
}


object AccountsService {

  /** user id to seq accounts ids mapping */

  val accountsData: Map[UUID, Seq[UUID]] = Map(
    UUID.fromString("14373b83-bf7b-4cf7-9534-295300fd1d49") ->
      Seq(
        UUID.fromString("be3273c3-2ca7-4479-b96b-b5be0509df9a"),
        UUID.fromString("93eab795-6926-4a80-be27-ba1e6485ca96")
      )
  )
}

class AccountsServiceImpl(balanceService: BalanceService,
                          cardsService: CardsService,
                          accountVerification: AccountVerificationService) {

  import AccountsService.accountsData

  def getAccounts(user: User): Seq[Account] = {
    accountsData.get(user.id).toSeq
      .flatMap(_.collect {
        case accountId
          if !accountVerification.isBlockedAccount(accountId) => composeAccount(accountId)
      })
  }

  private def composeAccount(accountId: UUID): Account = {
    Account(
      id = accountId,
      balance = balanceService.getBalanceOfAccount(accountId),
      cards = cardsService.getCardsOfAccount(accountId)
    )
  }

}

class AccountServiceTest extends FlatSpec with Matchers with MockitoSugar {

  trait mocks {
    val accountId: UUID = UUID.fromString("be3273c3-2ca7-4479-b96b-b5be0509df9a")
    val account2Id: UUID = UUID.fromString("93eab795-6926-4a80-be27-ba1e6485ca96")
    val userId: UUID = UUID.fromString("14373b83-bf7b-4cf7-9534-295300fd1d49")
    val user: User = User(userId)
    val testCard = Card("123456", Instant.now().plusSeconds(3600))
    val cardsService: CardsService = mock[CardsService]
    val balanceService: BalanceService = mock[BalanceService]
    val accountVerification: AccountVerificationService = mock[AccountVerificationService]
    val accountsService = new AccountsServiceImpl(balanceService, cardsService, accountVerification)

    when(cardsService.getCardsOfAccount(any())).thenReturn(Seq(testCard))
    when(balanceService.getBalanceOfAccount(any())).thenReturn(100.0)
    when(accountVerification.isBlockedAccount(any())).thenReturn(false)
  }

  "AccountsService" should "return user accounts" in new mocks {
    accountsService.getAccounts(user) shouldEqual
      Seq(
        Account(accountId, 100.0, Seq(testCard)),
        Account(account2Id, 100.0, Seq(testCard)),
      )

    verify(balanceService).getBalanceOfAccount(accountId)
    verify(cardsService).getCardsOfAccount(accountId)
    verify(accountVerification).isBlockedAccount(accountId)

    verify(balanceService).getBalanceOfAccount(account2Id)
    verify(cardsService).getCardsOfAccount(account2Id)
    verify(accountVerification).isBlockedAccount(account2Id)
  }

  it should "filter blocked accounts" in new mocks {
    when(accountVerification.isBlockedAccount(account2Id)).thenReturn(true)
    accountsService.getAccounts(user) shouldEqual
      Seq(Account(accountId, 100.0, Seq(testCard)))

    verify(balanceService).getBalanceOfAccount(accountId)
    verify(cardsService).getCardsOfAccount(accountId)
    verify(accountVerification).isBlockedAccount(accountId)

    verify(balanceService, never()).getBalanceOfAccount(account2Id)
    verify(cardsService, never()).getCardsOfAccount(account2Id)
    verify(accountVerification).isBlockedAccount(account2Id)
  }

}
