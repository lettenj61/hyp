package com.github.thisisvesca.tea

import org.scalatest.funspec._

import com.github.thisisvesca.html._
import com.github.thisisvesca.facade.preact._

object Test extends Program {
  type Model = Int
  sealed trait Msg
  case object NoOp extends Msg

  def init: (Model, Cmd[Msg]) =
    (0, Cmd.none)

  def update(msg: Msg, model: Model): (Model, Cmd[Msg]) =
    msg match {
      case _ =>
        (model, Cmd.none)
    }

  def view(model: Model): Html[Msg] = {
    import Html._, Attributes._

    div(
      id := "root",
      h3(text("It works!")),
      span(style ++= List("backgroundColor" -> "#f00"), text(s"$model"))
    )
  }

  def subscriptions(model: Model): Sub[Msg] =
    Sub.none
}

class PreactDriverTest extends AnyFunSpec {

  describe("preact driver") {
    try {
      val driver = new PreactTestBackend.PreactDriver(Test)
      val app = driver.createVNode()

      val a = PreactRenderToString.render(app)
      assert(a == "p")
    } catch {
      case ex: Exception =>
        ex.printStackTrace()
    }
  }
}
