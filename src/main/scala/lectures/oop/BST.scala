package lectures.oop

import scala.annotation.tailrec
import scala.collection.mutable


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
  private val nodeWidth = 8

  private def emptyStr(n: Int, s: String = "."): String = s * n

  private def iToS(x: Int): String = s"[%${nodeWidth-2}d]".format(x)

  private def getLevelPositionByNum(n: Int): (Int, Int) = {
    var level = 1
    var mult = 2
    while (mult <= n){
      mult *= 2
      level += 1
    }
    val position = n - Math.pow(2,level - 1).toInt
    (level, position)
  }
}

case class BSTImpl(value: Int,
                   left: Option[BSTImpl] = None,
                   right: Option[BSTImpl] = None) extends BST {
  import BSTImpl._

//  def add(newValue: Int): BST = {
//    if (newValue == value) this
//    else {
//      if (newValue < value){
//        left match {
//          case None => BSTImpl(value, Some(BSTImpl (newValue)), right)
//          case Some(tree) => BSTImpl(value, Some(tree.add(newValue)), right)// <=== тут надо BSTImpl
//        }
//      }
//    }
//  }

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
      leaf.flatMap(_.find(value))
    }
  }

  override def toString(): String = {
    case class NodeInQueue(node: BSTImpl, num: Int)
    val depth = this.dfs(1)
    val numOfLeaves = Math.pow(2,depth-1).toInt
    val qu = mutable.Queue(NodeInQueue(this, 1))
    var resultString = s"Binary tree (depth = $depth):\n"
    var lastLevel = 0
    var lastPos = 0

    var strToWriteInThisLevel = "";
    while (qu.nonEmpty) {
      val qNode = qu.dequeue()
      val (curLevel, curPos) = getLevelPositionByNum(qNode.num)
      val numOfNodes = Math.pow(2,curLevel-1).toInt
      val interval = (numOfLeaves - numOfNodes)*nodeWidth/numOfNodes
      if (curLevel > lastLevel) {
        resultString += "\n%2d:".format(curLevel)
        lastPos = -1;
        resultString += emptyStr(interval / 2)
      } else resultString += strToWriteInThisLevel

      for (i <- 1 until (curPos - lastPos))
      resultString += emptyStr(nodeWidth, "_") + emptyStr(interval)
      resultString += iToS(qNode.node.value)
      strToWriteInThisLevel = emptyStr(interval)

      lastPos = curPos
      lastLevel = curLevel

      qNode.node.left match {
        case Some(tree) => qu.enqueue(NodeInQueue(tree, qNode.num * 2))
        case _ =>
      }

      qNode.node.right match {
        case Some(tree) => qu.enqueue(NodeInQueue(tree, qNode.num * 2 + 1))
        case _ =>
      }
    }
    resultString
  }

  private def dfs(level: Int): Int = {
    (left, right) match {
      case (None, None) => level
      case (Some(lTree), None) => lTree.dfs(level + 1)
      case (None, Some(rTree)) => rTree.dfs(level + 1)
      case (Some(l), Some(r)) =>
        Math.max(l.dfs(level + 1), r.dfs(level + 1))
    }
  }

}


object TreeTest extends App {

  val sc = new java.util.Scanner(System.in)
  val maxValue = 110000
  val nodesCount = sc.nextInt()

  val markerItem = 49930//(Math.random() * maxValue).toInt
  val markerItem2 = 92818//(Math.random() * maxValue).toInt
  val markerItem3 = 98307//(Math.random() * maxValue).toInt

  // Generate huge tree
  val root: BST = BSTImpl(maxValue / 2)
  val tree: BST = (1 until nodesCount) // generator goes here
    .map (_ => (Math.random() * maxValue).toInt)
    .foldLeft(root) { (tree, value) =>
      tree.add(value)
    }

  // add marker items
  val testTree = tree.add(markerItem).add(markerItem2).add(markerItem3)

//  // check that search is correct
  require(testTree.find(markerItem).isDefined)
  require(testTree.find(markerItem).isDefined)
  require(testTree.find(markerItem).isDefined)

  println(testTree)
}