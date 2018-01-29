package lectures.oop



import lectures.oop.myhttp
import lectures.oop.myservices.{DataBase, MailService}
import lectures.oop.myhttp._

import scala.concurrent.{Await}
import scala.concurrent.duration._


/**
  * Данный класс содержит код, наспех написанный одним джуниор-разработчиком,
  * который плохо слушал лекции по эффективному программированию.
  *
  * Вам необходимо:
  * - отрефакторить данный класс, выделив уровни ответственности, необходимые
  *   интерфейсы и абстракции
  * - дописать тесты в FatUglyControllerTest и реализовать в них проверку на
  *   сохранение в БД, отправку сообщения в очередь и отправку email-а
  * - исправить очевидные костыли в коде
  *
  * Код внутри методов, помеченный как DO NOT TOUCH, трогать нельзя (сами методы
  * при этом можно выносить куда и как угодно)
  *
  * Интерфейс метода processRoute менять можно и нужно!
  * Передаваемые данные при этом должны оставаться неизменными.
  *
  * Удачи!
  */





class FatUglyController(db: DataBase, mailService: MailService) {

  private val handler = new HttpHandlerMyImpl(db, mailService)

  def processRoute(route: HttpRoute, request: HttpRequest): HttpResponse = {
    val futureResult = handler.handle(route, request)

    Await.result(futureResult, 30 seconds)
  }



}
