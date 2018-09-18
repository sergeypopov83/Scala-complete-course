package lectures.cat
import cats.free.Free
import cats.free.Free._
import cats.{Id, ~>}

import scala.collection.mutable

/**
  * В этом примере доменная модель, бизнес логика и интерретатор
  * разнесены в разные объекты
  * Это сделано исключительно для наглядности.
  * В реальных приложенпиях так делать не обязательно
  *
  * Доменная модель приложения и функции-помошники
  *
  */
object CatsKVStoreDomain {

  sealed trait KVStoreDomain[A]
  case class Put[T](key: String, value: T) extends KVStoreDomain[Unit]
  case class Get[T](key: String) extends KVStoreDomain[Option[T]]
  case class Delete(key: String) extends KVStoreDomain[Unit]

  type KVStore[A] = Free[KVStoreDomain, A]
  // Put returns nothing (i.e. Unit).
  def put[T](key: String, value: T): KVStore[Unit] =
    liftF[KVStoreDomain, Unit](Put[T](key, value))

  // Get returns a T value.
  def get[T](key: String): KVStore[Option[T]] =
    liftF[KVStoreDomain, Option[T]](Get[T](key))

  // Delete returns nothing (i.e. Unit).
  def delete(key: String): KVStore[Unit] =
    liftF(Delete(key))

  // Update composes get and set, and returns nothing.
  def update[T](key: String, f: T => T): KVStore[Unit] =
    for {
      vMaybe <- get[T](key)
      _ <- vMaybe.map(v => put[T](key, f(v))).getOrElse(Free.pure(()))
    } yield ()
}

/**
  * Интерпретатор для программы, написанной в терминах
  * доменной модели
  *
  */
object CatsKVStoreCompiler {
  import CatsKVStoreDomain._
  // the program will crash if a key is not found,
  // or if a type is incorrectly specified.
  def impureCompiler: KVStoreDomain ~> Id  =
  new (KVStoreDomain ~> Id) {

    // a very simple (and imprecise) key-value store
    val kvs = mutable.Map.empty[String, Any]

    def apply[A](fa: KVStoreDomain[A]): Id[A] =
      fa match {
        case Put(key, value) =>
          println(s"put($key, $value)")
          kvs(key) = value
          ()
        case Get(key) =>
          println(s"get($key)")
          kvs.get(key).map(_.asInstanceOf[A])
        case Delete(key) =>
          println(s"delete($key)")
          kvs.remove(key)
          ()
      }
  }
}

/**
  * Приложение
  */
object CatsKVStoreProgram extends App {

  import CatsKVStoreDomain._
  import CatsKVStoreCompiler._

  val program: Free[KVStoreDomain, Option[Int]] = for {
    _ <- put("wild-cats", 2)
    _ <- update[Int]("wild-cats", _ + 12)
    _ <- put("tame-cats", 5)
    n <- get[Int]("wild-cats")
    _ <- delete("tame-cats")
  } yield n

  val result = program.foldMap(impureCompiler)
  print(result)
}
