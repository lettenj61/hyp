package com.github.thisisvesca
package tea

import scala.scalajs.js

import com.github.thisisvesca.html._
import org.scalajs.dom

trait Projector[E] {

  def makeRenderFunction[F](vnode: Html[F]): js.Function1[_, E]
}
