package lectures.reflection

import java.lang.reflect.Modifier

import com.samskivert.mustache
import com.samskivert.mustache.Mustache
import scala.collection.JavaConverters._
import scala.reflect.macros.blackbox

class DescriberMacro(val c: blackbox.Context) {

  import c.universe._

  private val templateStr =
    """
      This {{cls}} has a name {{clsName}}
      with a list of parents
      {{#parents}}
        {{parent}}
      {{/parents}}
      and a list of public members
      {{#members}}
        {{member}}
      {{/members}}
    """.stripMargin

  val tmpl: Mustache.Compiler = Mustache.compiler()
  val template: mustache.Template = tmpl.compile(templateStr)

  def describer[T](implicit tt: WeakTypeTag[T]): c.Expr[String] = {
    val typee = tt.tpe
    val typeSymbol = typee.typeSymbol
    // Что за тип
    val (cls, clsName, clazz) = typeSymbol match {
      case cs: ClassSymbol if cs.isModule || cs.isModuleClass =>
        ("object", cs.fullName, Class.forName(cs.fullName))
      case cs: ClassSymbol if cs.isTrait =>
        ("trait", cs.fullName, Class.forName(cs.fullName))
      case cs: ClassSymbol if cs.isCaseClass =>
        ("case class", cs.fullName, Class.forName(cs.fullName))
      case cs: ClassSymbol if cs.isJava =>
        ("java primitive", cs.fullName, Class.forName(cs.fullName))
      case cs: ClassSymbol if cs.isClass =>
        ("class", cs.fullName, Class.forName(cs.fullName))
      case cs: Symbol =>
        c.abort(c.enclosingPosition, "Unknown symbol")
    }
    // список родителей
    val parents = typee.baseClasses.map(p => Map("parent" -> p.fullName).asJava).asJava
    // список публичных членов
//    val clazz = ct.runtimeClass

    val publicMembers =
      clazz.getDeclaredFields.filter(meth =>
        Modifier.isPublic(meth.getModifiers)).map(f => Map("member" -> s"field: ${f.getName}").asJava) ++
        clazz.getDeclaredMethods.filter(meth =>
          Modifier.isPublic(meth.getModifiers)).map(m => Map("member" -> s"method: ${m.getName}").asJava)

    val dataMap = Map[String, Any](
      "cls" -> cls,
      "clsName" -> clsName,
      "parents" -> parents,
      "members" ->
        (if (publicMembers.length == 0)
          Map("member" -> "no public members").asJava
        else publicMembers)
    )
    val res = template.execute(dataMap.asJava)
    c.Expr[String](q"$res")
//    c.Expr[String](Literal(Constant(res)))
  }
}
