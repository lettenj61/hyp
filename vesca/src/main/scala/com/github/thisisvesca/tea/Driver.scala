package com.github.thisisvesca
package tea

import scala.scalajs.js
import com.github.thisisvesca.facade.Vue

trait Driver {

  val program: Program

  type Msg = program.Msg
  type Model = program.Model

  trait State extends js.Object {
    var value: Model
  }

  val (initState, initCmd) = program.init()
  protected[this] val state: State =
    js.Dynamic.literal(value = initState).asInstanceOf[State]

  val projector: Vue

  private def step(msg: Msg): Unit = {
    val (newState, newCmd) = program.update(msg, state.value)
    state.value = newState

    run(newCmd, program.subscriptions(state.value), step)
  }
  def run(cmd: Cmd[Msg], sub: Sub[Msg], callback: Msg => Unit): Unit

  def renderFunction: Nothing
}
