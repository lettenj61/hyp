package com.github.thisisvesca

import scala.scalajs.js
import com.github.thisisvesca.{ virtualdom => vdom }
import com.github.thisisvesca.virtualdom._
import com.github.thisisvesca.virtualdom.VirtualDom._

object html {
  type Html[+F] = Node[F]
  type Attribute[+F] = vdom.Attribute[F]

  object Html extends HtmlFactory
  object Attributes extends AttributeProvider {
    type Value = js.Any
  }
}