package lectures.oop

/**
  * Программист Иван собрался на рыбалку.
  * Он хочет поймать огромную рыбину и для этого решил собрать свою любимую удочку.
  * Иван раздобыл составные части, ему осталось правильно их соединить.
  *
  * Каждая составная часть - это класс (или трейт, или объект), маркированный определенным образом.
  * Например, PartD - это часть удочки, маркированная символом D
  *
  * Раскомментируйте код, начиная с ??? PartD
  *
  * Ваша задача: с помощью extends и with добиться того, чтобы вызов println(o.str) вывел в консоль слово YDOCHKA
  *
  * * * * В самом низу иерархии должен быть класс PartO, т.е. ни один член приложения не должен быть сабклассом PartO
  * * * * Остальные часть могут иметь друг с другом любое отношение наследования
  * * * * Новые члены приложения (т.е. новые классы, трейты или объекты) вводить нельзя
  * * * * str может быть любым членом класса (методом, переменной, константой)
  * * * * Нельзя использовать явно символ-маркер одной части в str другой части
  * * * * т.е. сделать так: PartO { override val str = "YDOCHKA" } нельзя, зато
  * * * * так можно: PartD { def str = { val newStr = doSomethingWithD("D"); newStr }}
  * * * * содержимое FishermansApp менять нельзя
  **/
trait PartD {
  def str = "D"
}

trait PartY
  extends PartD{
  override def str = "Y" + super.str
}

trait PartCH {
  def str = "CH"
}

trait PartK
  extends  PartCH {
  override def str =  super.str + "K"
}

class PartA
  extends PartK {
  override def str = super.str + "A"
}

class PartO
  extends PartA
    with PartY{
  override def str = super[PartY].str + "O" + super[PartA].str
}

object FishermansApp extends App {
  val o = new PartO
  println(o.str)
}


