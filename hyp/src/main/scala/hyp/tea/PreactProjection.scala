package hyp.tea

import scala.scalajs.js

import hyp.internal.facade.preact._
import hyp.virtualdom._

import org.scalajs.dom

object PreactProjection extends Projector[FunctionComponent[Unit]] {

  def driver(program: Program): Driver[FunctionComponent[Unit]] =
    new PreactDriver(program)

  final class PreactDriver(val program: Program) extends Driver[FunctionComponent[Unit]] {
    override type Model = program.Model
    override type Msg   = program.Msg

    def run(node: dom.Element): Unit = {
      val app = component
      Preact.render(Preact.h(app), node)
    }

    lazy val component: FunctionComponent[Unit] =
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
      js.Any.fromFunction0 { () =>
        {
          val js.Tuple2(currentState, dispatch) = PreactHooks.useReducer[Model, Msg](
            reducer = reducer,
            model
          )

          val html = program.view(currentState)
          compileHelp(html, dispatch)
        }
      }
    }

    protected def compileHelp(html: Node[Msg], dispatch: js.Function1[Msg, Unit]): VNode = {
      import VirtualDom._

      val jsChildren = js.Array[js.Any]()
      val jsProps    = js.Dictionary[js.Any]()

      html match {
        case Text(s) =>
          Preact.h(Preact.Fragment, null, s)

        case TagSeq(items) =>
          jsChildren ++= items.map(child => compileHelp(child, dispatch))
          Preact.h(Preact.Fragment, null, jsChildren)

        case Tag(tagName, attributes, children) =>
          attributes.foreach {
            case Property(str, value) =>
              jsProps(str) = value.asInstanceOf[js.Any]

            case listener @ EventHandler(ty, f, capture) =>
              val handlerName = normalizeHandlerName(ty) + (if (capture) "Capture" else "")
              jsProps(handlerName) = js.Any.fromFunction1[dom.Event, Unit] { (e: dom.Event) =>
                {
                  val res: Msg = listener.asInstanceOf[EventHandler[Msg, dom.Event]].f(e)
                  dispatch(res)
                }
              }
          }

          children.foreach {
            case Text(s) =>
              jsChildren += s

            case TagSeq(items) =>
              jsChildren ++= items.map(child => compileHelp(child, dispatch))

            case tag: Tag[_] =>
              val Child = compileHelp(tag.asInstanceOf[Tag[Msg]], dispatch)
              jsChildren += Child
          }
          Preact.h(tagName, jsProps, jsChildren)
      }

    }

    private def normalizeHandlerName(ty: String): String = {
      val keys = ty.split("-")
      if (keys.length == 1) "on" + ty.capitalize
      else "on" + keys(0) + keys.tail.map(_.capitalize).mkString
    }
  }
}
