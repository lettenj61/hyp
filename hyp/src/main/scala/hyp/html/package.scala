package hyp

import scala.scalajs.js

import hyp.virtualdom._

package object html {
  type Html[+F]      = Node[F]
  type Tag[+F]       = VirtualDom.Tag[F]
  type Attribute[+F] = virtualdom.Attribute[F]

  object Html extends HtmlFactory
  object Attributes extends StandardAttributeProvider with AttributeIntrinsic {
    type Value = js.Any

    protected def makeStyleMap(values: Iterable[(String, String)]): Value =
      js.Dictionary(values.toSeq: _*)

    protected def makeClassList(values: Iterable[(String, Boolean)]): Value =
      js.Dictionary(values.toSeq: _*)
  }
  object Events extends GlobalEventProvider
}
