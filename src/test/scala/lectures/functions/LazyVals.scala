package lectures.functions

class ServiceA(c: =>ServiceC) {
  def getC = c
}

class ServiceC(val a: ServiceA)

object LazyVals extends App {

  val a: ServiceA = new ServiceA(c)
  val c: ServiceC = new ServiceC(a)

  println(a.getC)

  assert(a.getC == c)
  a.getC == c
}
