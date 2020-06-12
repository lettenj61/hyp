package com.github.thisisvesca.facade.preact

import scala.scalajs.js
import scala.scalajs.js.annotation._

@js.native
@JSImport("preact-render-to-string", JSImport.Namespace)
object PreactRenderToString extends js.Object {
  def render(vnode: VNode): String = js.native
  def shallow(vnode: VNode): String = js.native
}
