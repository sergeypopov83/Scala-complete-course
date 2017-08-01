package lectures.features

trait Figure {
  val color: String
}

case class Cube(color: String = "Red") extends Figure

case class Pyramid(color: String = "Yellow") extends Figure

case class Sphere(color: String = "Green") extends Figure

case class ToyPuzzle(figure1: Figure, figure2: Figure, figure3: Figure)

object ToyPuzzle {
  def apply(): ToyPuzzle = new ToyPuzzle(Cube(), Pyramid(), Sphere())

//  def unapply(puzzle: ToyPuzzle): Option[(String, String, String)] =
//    Some((puzzle.figure1.color, puzzle.figure2.color, puzzle.figure2.color))
}

object DefaultPuzzle {

  def apply(): ToyPuzzle = new ToyPuzzle(Cube(), Pyramid(), Sphere())

  def unapply(puzzle: ToyPuzzle): Option[(Figure, Figure, Figure)] = {
    val ToyPuzzle(f1, f2, f3) = puzzle
    if (f1.isInstanceOf[Cube] && f2.isInstanceOf[Pyramid] && f3.isInstanceOf[Sphere])
      Some(f1, f2, f3)
    else None
  }
}

object OR {
  def unapply(puzzle: ToyPuzzle): Option[(Figure, Figure)] = {
    val ToyPuzzle(f1, f2, f3) = puzzle
    if (f1.isInstanceOf[Cube] && f2.isInstanceOf[Pyramid])
      Some(f1, f2)
    else None
  }
}

object Main extends App {

  val p1 = ToyPuzzle(Sphere(), Sphere(), Sphere())
  val p2 = ToyPuzzle(Pyramid(), Sphere(), Cube())
  val p3 = ToyPuzzle()
  val badPuzzle = ToyPuzzle(Cube(), Pyramid(), Pyramid())

  def matchPuzzle(puzzle: ToyPuzzle) = puzzle match {
    case ToyPuzzle(f1, f2, f3) if (f1 == f2) && (f1 == f3) => println("Toy puzzle")
    case OR(cube, pyramid) => print(cube)
    case DefaultPuzzle(cube1, pyramid1, sphere1) => print(cube1)
    case ToyPuzzle(Pyramid(_), _, _) => print("Puzzle with pyramid in front ")
    //case puzzle =>  //DOn't forget to put all possible cases
  }

  // Assignment
  val ToyPuzzle(cube, pyramid, sphere) = ToyPuzzle()
  println(cube)
  println(pyramid)
  println(sphere)

  // Pattern Match
  matchPuzzle(p1)
  matchPuzzle(p2)
  matchPuzzle(p3)
  //matchPuzzle(badPuzzle)
}

