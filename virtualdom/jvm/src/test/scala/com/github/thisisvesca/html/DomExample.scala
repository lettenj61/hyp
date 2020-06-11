package com.github.thisisvesca
package html

import scala.language.implicitConversions

import com.github.thisisvesca.virtualdom._
import com.github.thisisvesca.virtualdom.VirtualDom

object html extends HtmlFactory
object attributes extends AttributeName {
  type Value = String
}
