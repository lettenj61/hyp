package com.github.thisisvesca
package tea

import scala.scalajs.js

import com.github.thisisvesca.html._
import org.scalajs.dom

trait Driver[V] {

  val program: Program

  type Msg   = program.Msg
  type Model = program.Model

  def component: V

  def run(node: dom.Element): Unit
}

trait Projector[V] {

  def driver(program: Program): Driver[V]

  def newProgram[S, F](
      init: (S, Cmd[F]),
      update: (F, S) => (S, Cmd[F]),
      view: (S => Html[F]),
      subscriptions: (S => Sub[F])
    ): Driver[V] = {
    val (init0, update0, view0, subs0) = (init, update, view, subscriptions)
    val program: Program = new Program {

      type Model = S
      type Msg   = F

      def init: (Model, Cmd[Msg])                           = init0
      def update(msg: Msg, model: Model): (Model, Cmd[Msg]) = update0(msg, model)
      def view(model: Model): Html[Msg]                     = view0(model)
      def subscriptions(model: Model): Sub[Msg]             = subs0(model)
    }

    driver(program)
  }
}
