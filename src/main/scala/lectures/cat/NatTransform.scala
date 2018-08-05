package lectures.cat

import cats.arrow.FunctionK

import scala.util.{Failure, Success, Try}

/**
  * Ваша программа рабоатет с гипотетическим источником данных UnstableSource
  * Ее целья взять образец из полученных данных
  *
  * Т.к. источник данных нестабильный, перед сэмлированием его нужно преобразовать в
  * стабильный с помощью SourceTransformer
  *
  * Обратите внимение, как с помощью FunctionK, можно отделить код, ответсвенный за работу с источниками данных
  * от кода, ответсвенно непосредственно за работу с данными
  *
  * Реализуйте
  * val sampler: Sampler = ???
  * val sourceTransformer: SourceTransformer = ???
  *
  * Тест NatTransformTest должен проходить
  *
  */
object NatTransform {

  /**
    * Функция которая берет "образец" из источника данных
    */
  type Sampler = FunctionK[Source, Option]

  /**
    * Преобразование, которое отвечает за обработку сбоев источника данных
    * Если произошла ошибка возвращает пустой source
    */
  type SourceTransformer = FunctionK[UnstableSource, Source]

  /**
    * Несиабильный источник данных для сэмплирования
    */
  type UnstableSource[A] = Try[List[A]]

  /**
    * Стабильный источник данных
    * Для нашего случая подходит просто List
    */
  type Source[A] = List[A]

  /**
    * Функция, которая берет сэмпл из набора данных
    * В данном случае, первый элемент, если он есть
    */
  val sampler: Sampler = ???

  val sourceTransformer: SourceTransformer = ???
}
