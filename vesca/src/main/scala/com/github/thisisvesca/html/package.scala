package com.github.thisisvesca

import scala.scalajs.js
import com.github.thisisvesca.{virtualdom => vdom}
import com.github.thisisvesca.virtualdom._
import com.github.thisisvesca.virtualdom.VirtualDom._

package object html {
  type Html[+F]      = Node[F]
  type Tag[+F]       = VirtualDom.Tag[F]
  type Attribute[+F] = vdom.Attribute[F]

  object Html extends HtmlFactory
  object Attributes extends StandardAttributeProvider with AttributeIntrinsic {
    type Value = js.Any

    protected def makeStyleMap(values: Iterable[(String, String)]): Value =
      js.Dictionary(values.toSeq: _*)
  }
  object Events extends GlobalEventProvider
}
