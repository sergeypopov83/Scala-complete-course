package lectures.di

import java.sql.Connection

import lectures.functions.User

/**
  * В этом задании мы познакомимся с разными приемами организации DI
  * в scala приложениях.
  *
  * Для этого мы, нескольками способами, реализуем простое приложение,
  * которое, ищет нужного нам пользователя в локальной БД по определенным критериям
  *
  * Что бы выполнить данное задание нужно
  * * * Реализовать трейт ConnectionManager в соответствии с описанием в файле ConnectionManagerImpl
  * * * Заменить параметр `connection` конструктора UserServiceImpl, параметром connectionManager: ConnectionManager
  * * * Получать и закрывать соединение нужно внутри методов UserServiceImpl, используя для этого connectionManager
  * * * Переписать тесты к реализациям DI в пакетах `cake`, `macwire`, `reader`
  * * * Нужно выбрать одну из следующих библиотек (ScalaDI, Guice, Spring или другую на ваш выбор)
  * * * * и реализовать то же приложение с ее помощью
  */

trait ConnectionManager {
  def connection: Connection
  def close(connection: Connection): Unit
}

trait UserService {
  def userByCredentials(name: String, pwd: String): Option[User]

  def updateUserPwd(id: String, pwd: String): Unit
}

trait Configuration {
  def attribute(attrName: String): Option[String]

  def setAttribute(attrName: String, value: String): Unit
}

trait UserServiceProgramDeps {
  val us: UserService
  val configuration: Configuration
}
