package lectures.concurrent

import scala.io.Source

/**
  * В данном задании необходимо реализовать поискового робота, в задачу которого входит
  * подсчитать количество русских слов в английской Википедии.
  *
  * Начинать надо с заглавной страницы и идти по ссылкам с нее далее вглубь.
  * Чтобы не выкачивать всю википедию, предлагается ограничиться 1000 страниц.
  * Считать надо только уникальные страницы, скачивать дубли нельзя!
  * За пределы домена en.wikipedia.org выходить не надо.
  *
  * Необходимо реализовать как однопоточный, так и многопоточный вариант (с заданным уровнем параллельности).
  * Использовать можно только родные Java-конструкции для работы с многопоточностью:
  * Thread, synchronized, concurrent-коллекции, примитивы работы с потоками.
  *
  */
class WebCrawler(numberOfThreads: Int) {

  private val WebPagesLimit = 1000
  private val InitialPage = "https://en.wikipedia.org/wiki/Main_Page"
  private val Word = """\b+([А-Яа-яЁё]+)\b+""".r

  def crawl(): Long = {
    // Start your implementation from here

    val page = Source.fromURL(InitialPage).mkString
    countWords(page)
  }

  private def countWords(page: String): Long = {
    Word.findAllIn(page).size
  }
}

object WebCrawler extends App {

  val startTime = System.currentTimeMillis()
  val result = new WebCrawler(1).crawl()
  val elapsedTime = System.currentTimeMillis() - startTime
  println(s"SingleCrawler took $elapsedTime ms, got $result words")

  val fourfoldCrawler = new WebCrawler(4)
  val result4 = new WebCrawler(4).crawl()
  val elapsedTime4 = System.currentTimeMillis() - startTime
  println(s"FourfoldCrawler took $elapsedTime4 ms, got $result4 words")

}
