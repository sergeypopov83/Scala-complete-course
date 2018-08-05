package lectures.cat

import org.scalatest.{Matchers, Pending, WordSpec}
import org.scalatest.mockito.MockitoSugar

/**
  * Создайте инстансы моноида и группы над строками.
  * Ассоциативной операцией должна быть операция конкатенации строк
  *
  * Докажите, реализовав тесты, что строки с конкатенацией - это моноид но не группа.
  *
  * Для доказательсва в тестах должны использоваться
  * lectures.cat.MonoidLaw и lectures.cat.GroupLaw соответвенно.
  */

class MonoidLawTest extends WordSpec with MockitoSugar with Matchers {

  "String monoid" should {
    "pass Monoid laws test" in Pending
  }

  "String group " should {
    "fail Group laws test" in Pending
  }
}

