package lectures.di.cake


/**
  * Как завещали нам GOF
  * Favor 'object composition' over 'class inheritance'. (Gang of Four 1995:20)
  *
  * Главной причиной, по которой стоит с осторожностью испольовтаь наследование -
  * это нежелательное нарушение инкапсуляции и нежелательное "утекание" функциональности из родителей в
  * наследников
  *
  */

trait Database {
  // ...
  def executeAnySql = ???
}

trait UserDb extends Database {
  def getUser = executeAnySql
}

trait UserEmailService extends UserDb {
  def sendEmail = {
    getUser
    ???
  }

  // Инкапсуляция сломана. Методы EmailService
  // получили возможность выполнять любые запросы
  def maliciousAction = {
    executeAnySql
  }
}

trait UserDbWithSelf {
  this: Database =>
  // ...
}

trait EmailServiceWithSelf {
  this: UserDbWithSelf =>
  // Выполенние любого sql кода не доступно
  //  executeAnySql
}