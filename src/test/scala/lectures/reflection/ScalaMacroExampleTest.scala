package lectures.reflection

import java.lang.reflect.Modifier

import com.samskivert.mustache
import com.samskivert.mustache.Mustache
import lectures.di.reader.DIDomain.ReaderTOption
import lectures.di.reader.ReaderTOptionObj
import lectures.functions.{LPUser, User}
import org.scalatest.{Matchers, WordSpec}

import scala.collection.JavaConverters._
import scala.reflect.runtime.{universe => ru}
import scala.reflect.runtime._
import ru._
import scala.reflect.ClassTag

class ScalaMacroExampleTest extends WordSpec with Matchers {


  "Macro from ScalaMacroExample" should {
    "return scalar value" in {
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
