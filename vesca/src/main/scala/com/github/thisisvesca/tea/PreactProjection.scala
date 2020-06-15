package com.github.thisisvesca.tea

import scala.scalajs.js

import com.github.thisisvesca.facade.preact._
import com.github.thisisvesca.virtualdom._

import org.scalajs.dom

object PreactProjection extends Projector[FunctionComponent[_]] {

  def driver(program: Program): Driver[FunctionComponent[_]] =
    new PreactDriver(program)

  final class PreactDriver(val program: Program) extends Driver[FunctionComponent[_]] {
    override type Model = program.Model
    override type Msg   = program.Msg

    def run(node: dom.Element): Unit = {
      val app = component
      Preact.render(Preact.h(app), node)
    }

    lazy val component: FunctionComponent[_] =
      compileFunctionComponent(state)

    val (initModel, initCmd): (Model, Cmd[Msg]) = program.init

    private var cmds: Cmd[Msg] = initCmd
    private var state: Model   = initModel

    private def reducer(model: Model, msg: Msg): Model = {
      val (newModel, newCmds) = program.update(msg, model)
      cmds = newCmds // TODO: how to merge commands?
      state = newModel
      newModel
    }

    protected def compileFunctionComponent(model: Model): FunctionComponent[Unit] = {
      import VirtualDom._

      js.Any.fromFunction0 { () =>
        {
          val js.Tuple2(currentState, dispatch) = PreactHooks.useReducer[Model, Msg](
            reducer = reducer,
            model
          )

          val html = program.view(currentState).asInstanceOf[Tag[Msg]]
          compileHelp(html, dispatch)
        }
      }
    }

    protected def compileHelp(
        html: VirtualDom.Tag[Msg],
        dispatch: js.Function1[Msg, Unit]
      ): VNode = {
      import VirtualDom._

      val children = js.Array[js.Any]()
      val props    = js.Dictionary[js.Any]()

      html.attributes.foreach {
        case Property(str, value) =>
          props(str) = value.asInstanceOf[js.Any]

        case listener @ EventHandler(ty, f, capture) =>
          val handlerName = normalizeHandlerName(ty) + (if (capture) "Capture" else "")
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
          val Child = compileHelp(tag.asInstanceOf[Tag[Msg]], dispatch)
          children += Child
      }
      Preact.h(html.tag, props, children)
    }

    private def normalizeHandlerName(ty: String): String = {
      val keys = ty.split("-")
      if (keys.length == 1) "on" + ty.capitalize
      else "on" + keys(0) + keys.tail.map(_.capitalize).mkString
    }
  }
}
