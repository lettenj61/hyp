package com.github.thisisvesca
package facade

import scala.scalajs.js
import scala.scalajs.js.|
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("vue", JSImport.Default, globalFallback = "Vue")
class Vue(options: js.Any) extends js.Object {}

@js.native
trait VNode extends js.Any

@js.native
trait CreateElement extends js.Function3[js.Any, js.Any, js.Array[_], VNode] {
  def apply(tag: String, options: js.Any): VNode
}