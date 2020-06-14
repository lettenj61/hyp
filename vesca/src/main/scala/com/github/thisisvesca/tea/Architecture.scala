package com.github.thisisvesca
package tea

import com.github.thisisvesca.html.Html

trait Architecture {
  self =>

  type Model
  type Msg

  def update(msg: Msg, model: Model): (Model, Cmd[Msg])

  def view(model: Model): Html[Msg]

  def subscriptions(model: Model): Sub[Msg]
}

abstract class Program extends Architecture {
  def init: (Model, Cmd[Msg])
}
