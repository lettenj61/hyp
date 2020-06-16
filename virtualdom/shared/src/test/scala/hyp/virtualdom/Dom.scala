package hyp
package virtualdom

object html extends HtmlFactory
object attributes extends StandardAttributeProvider with AttributeIntrinsic {
  type Value = String

  protected def makeStyleMap(values: Iterable[(String, String)]): String =
    values.toMap.toString

  protected def makeClassList(values: Iterable[(String, Boolean)]): String =
    values.toMap.toString
}
object events extends EventProvider {

}
