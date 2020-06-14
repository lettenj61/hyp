package com.github.thisisvesca.tea

import scala.scalajs.js
import com.github.thisisvesca.facade.preact._
import com.github.thisisvesca.html._
import com.github.thisisvesca.virtualdom.VirtualDom
import org.scalajs.dom

object PreactTestBackend {

  @js.native
  private trait CreateElement extends js.Function {
    def apply(`type`: String, props: js.Any, children: js.Array[js.Any]): VNode
  }

  @js.native
  type FunctionComponent0 = js.Function0[VNode]

  class PreactDriver(val program: Program) {
    type Model = program.Model
    type Msg = program.Msg

    val (initModel, initCmd): (Model, Cmd[Msg]) = program.init

    private var cmds: Cmd[Msg] = initCmd
    private var state: Model = initModel

    val js.Tuple2(_, dispatch) = PreactHooks.useReducer[Model, Msg](
      reducer = reducer,
      state
    )

    private def reducer(model: Model, msg: Msg): Model = {
      val (newModel, newCmds) = program.update(msg, model)
      cmds = newCmds // TODO: how to merge commands?
      state = newModel
      newModel
    }

    def compileFunctionComponent(h: CreateElement, html: VirtualDom.Tag[Msg]): FunctionComponent0 = {
      import VirtualDom._

      val children = js.Array[js.Any]()
      val props = js.Dictionary[js.Any]()

      html.attributes.foreach {
        case Property(str, value) =>
          props(str) = value.asInstanceOf[js.Any]

        case listener @ EventHandler(ty, f, capture) =>
          val handlerName = if (capture) ty + "Capture" else ty
          props(handlerName) = js.Any.fromFunction1[dom.Event, Unit] {
            (e: dom.Event) => {
              val res: Msg = listener.asInstanceOf[EventHandler[Msg, dom.Event]].f(e)
              dispatch(res)
            }
          }
      }

      html.children.foreach {
        case Text(s) =>
          children += s

        case tag: Tag[_] =>
          val Child = compileFunctionComponent(h, tag.asInstanceOf[Tag[Msg]])
          children += Child
      }

      () => h(html.tag, props, children)
    }

    def createVNode(): VNode = {
      val html = program.view(state).asInstanceOf[VirtualDom.Tag[Msg]]
      val F: FunctionComponent0 = compileFunctionComponent((Preact.h _).asInstanceOf[CreateElement], html)
      F.asInstanceOf[VNode]
    }

    def run(node: dom.Element): Unit = {
      val F = createVNode()
      Preact.render(F.asInstanceOf[ComponentChild], node)
    }
  }
}
