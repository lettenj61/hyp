package com.github.thisisvesca

import scala.scalajs.js
import scala.scalajs.js.Dynamic.global

import com.github.thisisvesca.html._
import com.github.thisisvesca.tea._
import org.scalajs.dom

object ExampleApp {

  type Model = Int
  sealed trait Msg
  case object Increment extends Msg
  case object Decrement extends Msg

  def init = (0, Cmd.none)

  def update(msg: Msg, model: Model) = msg match {
    case Increment => (model + 1, Cmd.none)
    case Decrement => (model - 1, Cmd.none)
  }

  def view(model: Model) = {
    import Html._, Attributes._, Events._

    div(
      button(
        onClick := { _ => Increment },
        text("+")
      ),
      span(
        style ++= Seq(
          "backgroundColor" -> "pink",
          "margin" -> "1.25rem"
        ),
        text(s"$model")
      ),
      button(
        onClick := { _ => Decrement },
        text("-")
      )
    )
  }

  def subscriptions(model: Int): Sub[Msg] =
    Sub.none

  def main(args: Array[String]): Unit = {
    val driver = PreactProjection.newProgram(
      init = init,
      update = update,
      view = view,
      subscriptions = subscriptions
    )
    val node   = dom.document.getElementById("example")
    driver.run(node)
  }
}
