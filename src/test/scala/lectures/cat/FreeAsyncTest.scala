package lectures.cat

import org.scalatest.WordSpec
import scala.concurrent.duration._
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global

class FreeAsyncTest extends WordSpec{

"Free interpreter" should {
  "interpret it " in {

   val fap = new FreeAsyncProgram
   val r = Await.result(fap.doTheJob(), 10 seconds)
   print(r)
  }

}
}
