package com.github.thisisvesca
package virtualdom

object html extends HtmlFactory
object attributes extends StandardAttributeProvider with AttributeIntrinsic {
  type Value = String

  protected def makeStyleMap(values: Iterable[(String, String)]): String =
    values.toMap.toString
}
