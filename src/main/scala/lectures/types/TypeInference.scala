package lectures.types

/**
  * Не запуская приложения, предположите, чему будет равен 'result'.
  * Почему?
  *
  */
object TypeInference extends App {

  def printSomething() = "2 плюс 3 - это "

  def calculateSomething() = 1 + 1

  def result = printSomething + 3 + calculateSomething

  print(result)

}

