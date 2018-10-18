package lectures.types

import org.scalatest.WordSpec


class PhantomTypesTest extends WordSpec{
  import CheckProcessor._

  "Phantom types" should {
    "apply constraints based on type parameters" in {

      val checkProcessor = new CheckProcessor

      val createdCheck = checkProcessor.createACheck()

      // WON'T COMPILE BECAUSE OF CONSTRAINTS
      // checkProcessor.clearCheck(createdCheck)

       val sentCheck = checkProcessor.sendACheck(createdCheck)

       //WONT'T COMPILE because there is no implicit found
       //checkProcessor.processStatus(sentCheck)
       val clearedCheck = checkProcessor.clearCheck(sentCheck)
       // WOULD PRINT `paycheck was cleared`
       checkProcessor.processStatus(clearedCheck)
    }

  }

}
