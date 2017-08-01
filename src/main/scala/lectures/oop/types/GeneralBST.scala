package lectures.oop.types

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

trait GeneralBST {
  //val value: T
  val left: Option[GeneralBST]
  val right: Option[GeneralBST]

  def add(newValue: Int): GeneralBST

  def find(value: Int): Option[GeneralBST]
}

class GeneralBSTImpl extends GeneralBST {
  //override val value = _

  override def find(value: Int): Option[GeneralBST] = ???

  override def add(newValue: Int): GeneralBST = ???

  override val left: Option[GeneralBST] = ???
  override val right: Option[GeneralBST] = ???
}