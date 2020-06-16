package hyp.internal.facade.preact

import scala.language.implicitConversions

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSImport, JSName}

import org.scalajs.dom

@js.native
@JSImport("preact", JSImport.Default, globalFallback = "preact")
object Preact extends js.Object {
  def h(`type`: ComponentChild, props: js.Any, children: ComponentChild*): VNode = js.native
  def h(`type`: ComponentChild): VNode                                           = js.native

  @JSName("h")
  val createElement: CreateElement = js.native

  val options: PreactOptions = js.native

  def render(
      vnode: ComponentChild,
      parent: dom.Element,
      replaceNode: js.UndefOr[dom.Element] = js.native
    ): Unit = js.native

  def Fragment: FunctionComponent[js.Any] = js.native
}

@js.native
@JSImport("preact/hooks", JSImport.Default, globalFallback = "preactHooks")
object PreactHooks extends js.Object {
  type Reducer[A, F] = js.Function2[A, F, A]

  def useState[A](initialState: A): js.Tuple2[A, SetState[A]] = js.native
  def useReducer[A, F](
      reducer: Reducer[A, F],
      initialState: A
    ): js.Tuple2[A, js.Function1[F, Unit]] =
    js.native

  def useMemo[A](factory: js.Function0[A], dependencies: js.Array[_]): A = js.native
}

@js.native
trait PreactOptions extends js.Object {
  var diffed: js.Function1[VNode, Unit]
}

@js.native
trait FunctionComponent[P] extends js.Function1[P, VNode]
object FunctionComponent {
  @inline implicit def coerceFunction0(f: js.Function0[VNode]): FunctionComponent[Unit] =
    f.asInstanceOf[FunctionComponent[Unit]]

  @inline implicit def coerceFunction1[P](f: js.Function1[P, VNode]): FunctionComponent[P] =
    f.asInstanceOf[FunctionComponent[P]]
}

@js.native
trait CreateElement extends js.Function3[ComponentChild, js.Any, js.Array[js.Any], VNode]

@js.native
trait SetState[A] extends js.Function1[A, Unit] {
  def apply(nextState: A): Unit
  def apply(nextState: js.Function1[A, A]): Unit
}

@js.native
sealed trait ComponentChild extends js.Object
object ComponentChild {
  @inline implicit def stringComponent(s: String): ComponentChild =
    s.asInstanceOf[ComponentChild]

  @inline implicit def numberComponent(d: Double): ComponentChild =
    d.asInstanceOf[ComponentChild]

  @inline implicit def jsArrayComponent(array: js.Array[_]): ComponentChild =
    array.asInstanceOf[ComponentChild]

  @inline implicit def functionComponent(FC: FunctionComponent[_]): ComponentChild =
    FC.asInstanceOf[ComponentChild]
}

@js.native
trait VNode extends ComponentChild
