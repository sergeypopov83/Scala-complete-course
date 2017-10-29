package lectures.oop

import scala.annotation.tailrec


/**
  * BSTImpl - это бинарное дерево поиска, содержащее только значения типа Int
  *
  * * Оно обладает следующими свойствами:
  * * * * * левое поддерево содержит значения, меньшие значения родителя
  * * * * * правое поддерево содержит значения, большие значения родителя
  * * * * * значения, уже присутствующие в дереве, в него не добавляются
  * * * * * пустые значения (null) не допускаются
  *
  * * Завершите реализацию методов кейс класс BSTImpl:
  * * * * * Трейт BST и BSTImpl разрешается расширять любым образом
  * * * * * Изменять сигнатуры классов и методов, данные в условии, нельзя
  * * * * * Постарайтесь не использовать var и мутабильные коллекции
  * * * * * В задаче про распечатку дерева, нужно раскомментировать и реализовать метод toString()
  *
  * * Для этой структуры нужно реализовать генератор узлов.
  * * Генератор:
  * * * * * должен создавать дерево, содержащее nodesCount узлов.
  * * * * * не должен использовать переменные или мутабильные структуры.
  *
  */
trait BST {
  val value: Int
  val left: Option[BST]
  val right: Option[BST]

  def add(newValue: Int): BST

  def find(value: Int): Option[BST]
}

object BSTImpl {
  private def addInner(treeOpt: Option[BSTImpl], newVal: Int): BSTImpl = treeOpt match {
    case None => BSTImpl(newVal)
    case Some(tree) =>
      if (newVal == tree.value) tree
      else if (newVal < tree.value)
        BSTImpl(tree.value, Some(addInner(tree.left, newVal)), tree.right)
      else
        BSTImpl(tree.value, tree.left, Some(addInner(tree.right, newVal)))
  }
}

case class BSTImpl(value: Int,
                   left: Option[BSTImpl] = None,
                   right: Option[BSTImpl] = None) extends BST {
  import BSTImpl._

  def add(newValue: Int): BST = {
    //из-за того, что по условию нельзя изменить на add():BSTImpl
    addInner(Some(this), newValue)
  }

  def find(value: Int): Option[BST] = {
    if(value == this.value) Some(this)
    else {
      val leaf =
        if (value < this.value) this.left
        else this.right
      leaf match {
        case None => None
        case Some(tree) => tree.find(value)
      }
    }
  }

  // override def toString() = ???

}


object TreeTest extends App {

  val sc = new java.util.Scanner(System.in)
  val maxValue = 110000
  val nodesCount = sc.nextInt()

  val markerItem = (Math.random() * maxValue).toInt
  val markerItem2 = (Math.random() * maxValue).toInt
  val markerItem3 = (Math.random() * maxValue).toInt

  // Generate huge tree
  val root: BST = BSTImpl(maxValue / 2)
  val tree: BST = root // generator goes here

  // add marker items
  val testTree = tree.add(markerItem).add(markerItem2).add(markerItem3)

//  // check that search is correct
  require(testTree.find(markerItem).isDefined)
  require(testTree.find(markerItem).isDefined)
  require(testTree.find(markerItem).isDefined)

  println(testTree)
}