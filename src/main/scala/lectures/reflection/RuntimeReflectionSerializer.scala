package lectures.reflection

/**
  * Реализуйте методы сериализации\десериализации из произвольного типа T в строку
  * и в обратную сторону.
  * Серилизатор должерн уметь сериазизовать как минимум
  * * * * все скалярные типы
  * * * * основные коллекци
  * * * * классы и трейты с одним простым тайп параметром
  *
  * Если строка не может быть приведена в тип T переданный в deserialize, должна быть выброшена ошибка
  * SerializationException
  */
object RuntimeReflectionSerializer {

  def serialize[T](t: T): String = ???

  def deserialize[T](json: String): T = ???

}
