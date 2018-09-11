package lectures.functions

import scala.util.Random

/**
  * Эта задача имитирует авторизацию в интернет банке.
  * Авторизоваться можно 2-я способами. Предоставив карту или логин/пароль
  * Вам дан список зарегистрированных банковских карт и
  * AuthenticationData.registeredCards
  * и список зарегистрированных логинов/паролей
  * AuthenticationData.registeredLoginAndPassword
  *
  * Ваша задача, получая на вход приложения список тестовых юзеров
  * AuthenticationData.testUsers
  * Оставить в этом списке только тех пользователей, чьи учетные данные
  * совпадают с одними из зарегистрированных в системе
  *
  * Пользователи бывают 3-х видов
  * AnonymousUser - пользователь, который не указал своих учетных данных
  * CardUser - пользователь, который предоствил данные карты
  * LPUser - пользователь, предоставивший логин и пароль
  *
  * Для решения задачи раскомметируйте код в теле объекта Authentication
  * Реализуйте методы authByCard и authByLP, заменив
  * знаки ??? на подходящие выражения.
  *
  * Что-либо еще, кроме знаков ???, заменять нельзя
  */
object Authentication extends App {

  import AuthenticationData._

 val authByCard: PartialFunction[User, Option[User]] = {
   case user:AnonymousUser => None
   case user:CardUser if !registeredCards.contains(user.credentials) => None
   case user:CardUser if registeredCards.contains(user.credentials) => Option(user)
 }

 val authByLP: PartialFunction[User, Option[User]] = {
   case user:AnonymousUser => None
   case user:LPUser if !registeredLoginAndPassword.contains(user.credentials) => None
   case user:LPUser if registeredLoginAndPassword.contains(user.credentials) => Option(user)

 }

  val authenticated: List[Option[User]] = for (user <- testUsers) yield authByCard.orElse(authByLP)(user)

 authenticated.flatten foreach println

}
