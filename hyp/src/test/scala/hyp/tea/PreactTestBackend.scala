package hyp.tea

import scala.scalajs.js
import hyp.internal.facade.preact._
import hyp.html._
import hyp.virtualdom.VirtualDom
import org.scalajs.dom

object PreactTestBackend0 {

  @js.native
  type FunctionComponent0 = FunctionComponent[Unit]

  class PreactDriver(val program: Program) {
    type Model = program.Model
    type Msg   = program.Msg

    val (initModel, initCmd): (Model, Cmd[Msg]) = program.init

    private var cmds: Cmd[Msg] = initCmd
    private var state: Model   = initModel

    private def reducer(model: Model, msg: Msg): Model = {
      val (newModel, newCmds) = program.update(msg, model)
      cmds = newCmds // TODO: how to merge commands?
      state = newModel
      newModel
    }

    def compileFunctionComponent(html: VirtualDom.Tag[Msg]): FunctionComponent0 = {
      import VirtualDom._

      val children = js.Array[js.Any]()
      val props    = js.Dictionary[js.Any]()

      js.Any.fromFunction0 { () =>
        {
          val js.Tuple2(_, dispatch) = PreactHooks.useReducer[Model, Msg](
            reducer = reducer,
            state
          )

          html.attributes.foreach {
            case Property(str, value) =>
              props(str) = value.asInstanceOf[js.Any]

            case listener @ EventHandler(ty, f, capture) =>
              val handlerName = if (capture) ty + "Capture" else ty
              props(handlerName) = js.Any.fromFunction1[dom.Event, Unit] { (e: dom.Event) =>
                {
                  val res: Msg = listener.asInstanceOf[EventHandler[Msg, dom.Event]].f(e)
                  dispatch(res)
                }
              }
          }

          html.children.foreach {
            case Text(s) =>
              children += s

            case tag: Tag[_] =>
              val Child = compileFunctionComponent(tag.asInstanceOf[Tag[Msg]])
              children += Preact.h(Child)
          }

          Preact.h(html.tag, props, children)
        }
      }
    }

    def createVNode(): VNode = {
      val html                  = program.view(state).asInstanceOf[VirtualDom.Tag[Msg]]
      val F: FunctionComponent0 = compileFunctionComponent(html)
      F.asInstanceOf[VNode]
    }

    def run(node: dom.Element): Unit = {
      val F = createVNode()
      Preact.render(F, node)
    }
  }
}
