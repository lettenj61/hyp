package com.github.thisisvesca
package facade
package preact

import scala.scalajs.js
import scala.scalajs.js.|
import scala.scalajs.js.annotation.JSImport

import org.scalajs.dom

@js.native
@JSImport("preact", JSImport.Default, globalFallback = "preact")
object Preact extends js.Object {
  def h(`type`: String, props: js.Any, children: ComponentChild*): VNode         = js.native
  def h(`type`: ComponentChild, props: js.Any, children: ComponentChild*): VNode = js.native

  def render(
      vnode: ComponentChild,
      parent: dom.Element,
      replaceNode: js.UndefOr[dom.Element] = js.native
    ): Unit = js.native
}

@js.native
@JSImport("preact/hooks", JSImport.Default, globalFallback = "preactHooks")
object PreactHooks extends js.Object {
  type Reducer[A, F] = js.Function2[A, F, A]

  def useState[A](initialState: A): js.Tuple2[A, SetState[A]] = js.native
  def useReducer[A, F](
      reducer: Reducer[A, F],
      initialState: A
    ): js.Tuple2[A, js.Function1[A, Unit]] =
    js.native

  def useMemo[A](factory: js.Function0[A], dependencies: js.Array[_]): A = js.native
}

@js.native
trait SetState[A] extends js.Function1[A, Unit] {
  def apply(nextState: A): Unit
  def apply(nextState: js.Function1[A, A]): Unit
}

@js.native
sealed trait ComponentChild extends js.Object

@js.native
trait VNode extends ComponentChild
