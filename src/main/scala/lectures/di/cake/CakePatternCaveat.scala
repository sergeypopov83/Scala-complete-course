package lectures.di.cake

case class DependencyOne(value: String = "DependencyOne")

case class DependencyTwo(value: String = "DependencyTwo")

trait FirstService {
  self: SecondService =>

  val dep = DependencyOne(depTwo.value)
}

trait SecondService {
  self: FirstService =>

  val depTwo = DependencyTwo()
}

class NullService extends FirstService with SecondService

class GoodService extends SecondService with FirstService

object RunService extends App {

  // This service would throw NUll Pointer exception
  //  val nullService  = new NullService
  //  print(nullService.dep.value)

  // But this service wouldn't
  val goodService = new GoodService
  print(goodService.dep.value)

}

