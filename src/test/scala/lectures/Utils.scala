package lectures

object Utils {
  def shuffle(seq: Seq[Int]): Seq[Int] =
    scala.util.Random.shuffle(seq)
}

