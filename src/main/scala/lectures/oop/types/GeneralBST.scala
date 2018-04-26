package lectures.oop.types
import scala.math.Ordering.Implicits._
import scala.util.Random
import lectures.matching.SortingStuff.Watches


/**
  * Модифицируйте реализацию BSTImpl из предыдущего задания.
  * Используя тайп параметры и паттерн Type Class, реализуйте GeneralBSTImpl таким образом,
  * чтобы дерево могло работать с произвольным типом данных.
  *
  * Наследников GeneralBSTImpl определять нельзя.
  *
  * Создайте генератор для деревьев 3-х типов данных:
  * * * * float
  * * * * String
  * * * * Watches из задачи SortStuff. Большими считаются часы с большей стоимостью
  */

trait GeneralBST[T] {
  val value: T
  val left: Option[GeneralBST[T]]
  val right: Option[GeneralBST[T]]

  def add(newValue: T): GeneralBST[T]

  def find(value: T): Option[GeneralBST[T]]
}

class GeneralBSTImpl[T : Ordering](override val value: T,
                        override val left: Option[GeneralBST[T]] = None,
                        override val right: Option[GeneralBST[T]] = None
                       ) extends GeneralBST[T] {

  override def find(value: T): Option[GeneralBST[T]] = {
      if (this.value < value) {
        right.flatMap(_.find(value))
      } else if (this.value == value) {
        Some(this)
      } else {
        left.flatMap(_.find(value))
      }
  }

  override def add(newValue: T): GeneralBST[T] = {
    if (newValue == this.value)
      this
    else if (newValue > this.value) {
      val newNode = this.right.map(_.add(newValue)).getOrElse(new GeneralBSTImpl[T](newValue))
      new GeneralBSTImpl[T](this.value, left = this.left, right = Some(newNode))
    } else {
      val newNode = this.left.map(_.add(newValue)).getOrElse(new GeneralBSTImpl[T](newValue))
      new GeneralBSTImpl[T](this.value, left = Some(newNode), right = this.right)
    }
  }

}

object GeneralBSTTest extends App {

  val nodesCount = 100

  val doubleItem = 100.500
  val treeDouble: GeneralBST[Double]  = (1 until nodesCount)
    .map (_ => Math.random() * 500)
    .foldLeft(new GeneralBSTImpl(500.0 / 2): GeneralBST[Double]) { (tree, value) =>
      tree.add(value)
    }.add(doubleItem)

  require(treeDouble.find(doubleItem).isDefined)

  val r = new Random()
  val stringItem = "goodString"
  val treeString: GeneralBST[String] = (1 until nodesCount)
    .map (_ => r.nextString(r.nextInt(20)))
    .foldLeft(new GeneralBSTImpl("jjjjjjjjjj"): GeneralBST[String]) { (tree, value) =>
      tree.add(value)
    }.add(stringItem)

  require(treeString.find(stringItem).isDefined)


  implicit val ord: Ordering[Watches] = new Ordering[Watches]{
    override def compare(x: Watches, y: Watches) = (x.cost - y.cost).signum
  }


  val watchesItem = Watches("Nautica", 10000)
  val treeWatches: GeneralBST[Watches] = (1 until nodesCount)
    .map (_ => Watches(r.nextString(10), r.nextFloat()*10000))
    .foldLeft(new GeneralBSTImpl(Watches("aaa", 5000.0f)): GeneralBST[Watches]) { (tree, value) =>
      tree.add(value)
    }.add(watchesItem)

  require(treeWatches.find(watchesItem).isDefined)
}