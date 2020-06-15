package com.github.thisisvesca.tea

import scala.scalajs.js

import org.scalatest.funspec._
import org.scalatest.BeforeAndAfterAll
import com.github.thisisvesca.html._
import com.github.thisisvesca.facade.enzyme._
import com.github.thisisvesca.facade.preact._

object Test extends Program {
  type Model = String
  sealed trait Msg
  case object NoOp                     extends Msg
  case class SetMessage(value: String) extends Msg

  def init: (Model, Cmd[Msg]) =
    ("hello", Cmd.none)

  def update(msg: Msg, model: Model): (Model, Cmd[Msg]) =
    msg match {
      case NoOp =>
        (model, Cmd.none)

      case SetMessage(value) =>
        (value, Cmd.none)
    }

  def view(model: Model): Html[Msg] = {
    import Html._, Attributes._

    div(
      id := "root",
      span(text(model))
    )
  }

  def subscriptions(model: Model): Sub[Msg] =
    Sub.none
}

class PreactDriverTest extends AnyFunSpec with BeforeAndAfterAll {

  override def beforeAll(): Unit = {
    super.beforeAll()
    Enzyme.configure(new AdapterOptions {
      override val adapter = new PreactAdapter
    })
  }

  describe("preact driver") {
    val driver        = PreactProjection.driver(Test)
    val TestComponent = driver.component

    it("renders to string") {
      val app = Preact.h(TestComponent)
      val a   = PreactRenderToString.render(app)
      assert(a == """<div id="root"><span>hello</span></div>""")
    }
  }
}
