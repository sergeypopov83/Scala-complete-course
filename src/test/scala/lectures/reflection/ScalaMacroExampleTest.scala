package lectures.reflection

import lectures.di.reader.ReaderTOptionObj
import lectures.functions.{LPUser, User}
import org.scalatest.{Matchers, WordSpec}

import scala.reflect.runtime._

class ScalaMacroExampleTest extends WordSpec with Matchers {


  "Macro from ScalaMacroExample" should {
    "any macros can be parametrized" in {
      val i: String = ScalaMacroExamples.scalaMacroT[String]("value")
      i shouldBe null
    }
    "not obey types signatures if it's whitebox" in {
      val i: Int = ScalaMacroExamples.scalaMacro("value")
      i shouldBe 10

    }
    "depending on parameter" in {
      val value: Unit = ScalaMacroExamples.scalaMacro("value")
      val m = currentMirror.reflect(value)
      val typeS = m.symbol.asType
      val typeName = typeS.fullName
      print(typeName)
    }
    "describer" should {
      "correctly describer type" in {
        val strDesk = ScalaMacroExamples.describer[String]
        val intDesk = ScalaMacroExamples.describer[Int]
        val caseClassDesk = ScalaMacroExamples.describer[LPUser]
        val traitDesk = ScalaMacroExamples.describer[User]
        val objectDesk = ScalaMacroExamples.describer[ReaderTOptionObj.type]

        println(strDesk)
        println(intDesk)
        println(caseClassDesk)
        println(traitDesk)
        println(objectDesk)
      }
    }
  }
}
