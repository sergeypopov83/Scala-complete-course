package lectures.oop.myhttp
import scala.concurrent.Future

object HttpStatusCodes {
  val BadRequest = 400
  val Ok = 200
  val NotFound = 404
  val InternalServerError = 500
}

object HttpMethods {
  val Get = "GET"
  val Post = "POST"
  val Delete = "DELETE"
  val Put = "PUT"
}

class HttpRequest(parameters: Option[String] = None, entity: Option[String] = None){

  def getStringParameter(param: String): String = ???

  def getEntity: Option[Array[Byte]] =
    entity.map ( _.getBytes)
}

case class HttpResponse(status: Int, entity: String)

case class HttpRoute(method: String = HttpMethods.Get, path: String)

trait HttpHandler {
  def handle(request: HttpRequest): Future[HttpResponse]
}