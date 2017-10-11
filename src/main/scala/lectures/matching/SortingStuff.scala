package lectures.matching

/**
  * Раскладывание вещей по коробкам
  *
  * У Вас есть вещи 3-х разных типов:
  * * * * Watches - часы
  * * * * Boots - кроссовки
  * * * * Book - книги
  *
  * Вы хотите привести свои вещи в порядок и оставить только нужные
  * Для этого Вы завели себе коробку с отделениями для каждого типа вещей
  * * * * * StuffBox
  *
  * В StuffBox вы кладете вещи таким образом:
  * * * * * Все часы дороже 1000 - в StuffBox.watches
  * * * * * Все кроссовки от Converse или Adidas - в StuffBox.boots
  * * * * * Все интересные книги - в StuffBox.books
  * * * * * Остальное - в StuffBox.junk
  *
  * После того, как Вы разложили вещи, вы пытаетеь найти свой любимый нож,
  * который вы могли случайно положить в junk.
  *
  * После запуска SortingStuff должен напечатать ответ на вопрос:
  * "Is the knife in a junk? - "
  *
  * Чтобы выполнить задание, раскомментируйте код и следуйте подсказкам
  *
  */

object SortingStuff extends App {

  object Knife extends Stuff

  trait Stuff

  case class Boots(brand: String, size: Int = 43) extends Stuff

  case class Watches(brand: String, cost: Float) extends Stuff

  case class Book(name: String, isInteresting: Boolean = false) extends Stuff

  val stuff = List(
    Boots("Adidas", 42),
    Book("Game of thrones 1", true),
    Boots("Reebok", 42),
    Watches("Nautica", 10000),
    Boots("Converse", 43),
    Boots("Converse", 43),
    Watches("Breitling", 100000),
    Watches("Electronika", 1000),
    Boots("Skorohod", 50),
    Boots("Noname", 45),
    Watches("Zarya", 15000),
    Book("Game of thrones 3"),
    Book("Game of thrones 4"),
    Watches("Casio", 5000),
    Watches("Citizen", 5000),
    Book("Game of thrones 2", true),
    Book("Idiot", true),
    Book("Lubovnaya lubov"),
    Knife
  )

  case class StuffBox(books: List[Book] = Nil,
                      watches: List[Watches] = Nil,
                      boots: List[Boots] = Nil,
                      junk: List[Stuff] = Nil)

  def sortJunk(stuff: List[Stuff]): StuffBox = {

    def sort(stuff: List[Stuff], stuffBox: StuffBox): StuffBox = {
      stuff match {
        case item :: rest =>
          val newBox = putStuffInRightBox(item, stuffBox)
          sort(rest, newBox)
        case _ => stuffBox
      }

    }

    def putStuffInRightBox(item: Stuff, stuffBox: StuffBox) = {
      item match {
        case book@(item: Book) => stuffBox.copy(books = item :: stuffBox.books)
        case boots@(item: Boots) if item.brand == "Converse" || item.brand == "Adidas" => stuffBox.copy(boots = item :: stuffBox.boots)
        case watches@(item: Watches) if item.cost > 1000 => stuffBox.copy(watches = item :: stuffBox.watches)
        case junk@_ => stuffBox.copy(junk = item :: stuffBox.junk)
      }
    }

    sort(stuff, StuffBox())
  }

  def findMyKnife(stuffBox: StuffBox): Boolean = stuffBox match {
    case StuffBox(_, _, _, _) if stuffBox.junk.contains(Knife) => true
    case _ => false
  }

  // //вместо вопросов подставьте композицию функций  sortJunk и findMyKnife
  val knifeIsInJunk = findMyKnife(sortJunk(stuff))
  print(s"Is knife in a junk? - $knifeIsInJunk")

}