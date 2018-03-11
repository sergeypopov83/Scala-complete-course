package lectures.oop.myhttp

import java.io.IOException
import java.security.MessageDigest

import lectures.oop.myservices.{DataBase, EsbService, MailService}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits._


class HttpHandlerMyImpl(db: DataBase, ms: MailService, esb: EsbService)
  extends HttpHandler {

  private val mega = 1024 * 1024
  private val MAX_FILE_SIZE = 8 //config.getInt("...")

  def handle(request: HttpRequest): Future[HttpResponse] = {
    request.getEntity
      .map { entity =>
        if (entity.length > MAX_FILE_SIZE * mega) {
          Future.successful(HttpResponse(HttpStatusCodes.BadRequest, s"File size should not be more than $MAX_FILE_SIZE MB"))
        } else {
          uploadFile(entity)
        }
      }.getOrElse(Future.successful(HttpResponse(HttpStatusCodes.BadRequest, "Can not upload empty file")))
      .recover {
        case e: IOException =>
          HttpResponse(HttpStatusCodes.BadRequest, s"IOException: ${e.getMessage}")
        case e: Throwable =>
          HttpResponse(HttpStatusCodes.InternalServerError, s"Internal server Error: ${e.getMessage}")
      }
  }

  private def uploadFile(entity: Array[Byte]): Future[HttpResponse] = {
    Future {
      val responseBuf = new StringBuilder()
      val files = collectFilesFromEntity(entity)
      files.foreach { file =>
        val (name, trimmedBody, extension) = splitFile(file)
        val id = hash(file.trim)
        if (Seq("exe", "bat", "com", "sh").contains(extension)) {
          throw new IOException("Request contains forbidden extension")
        }
        // Emulate file saving to disk
        responseBuf.append(s"- saved file $name to " + id + "." + extension + s" (file size: ${trimmedBody.length})\n")

        db.executePostgresQuery(s"insert into files (id, name, created_on) values ('$id', '$name', current_timestamp)")
        esb.sendMessageToIbmMq(s"""<Event name="FileUpload"><Origin>SCALA_FTK_TASK</Origin><FileName>${name}</FileName></Event>""")
        ms.send("admin@admin.tinkoff.ru", "File has been uploaded", s"Hey, we have got new file: $name")
      }
      HttpResponse(200, "Response:\n" + responseBuf.dropRight(1))
    }
  }

  private def hash(s: String): String = {
    MessageDigest.getInstance("SHA-1").digest(s.getBytes("UTF-8")).map("%02x".format(_)).mkString
  }

  private def collectFilesFromEntity(entity: Array[Byte]) = {
    val stringBody = new String(entity.filter(_ != '\r'))
    val delimiter = stringBody.takeWhile(_ != '\n')
    stringBody.split(delimiter).drop(1)
  }

  private def splitFile(file: String) = {
    val (name, body) = file.trim.splitAt(file.trim.indexOf('\n'))
    val trimmedBody = body.trim
    val extension = name.reverse.takeWhile(_ != '.').reverse
    (name, trimmedBody, extension)
  }

}
