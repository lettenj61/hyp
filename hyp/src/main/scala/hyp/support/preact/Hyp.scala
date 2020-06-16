package hyp.support.preact

import hyp.tea._
import hyp.html._
import hyp.internal.facade.preact._
import org.scalajs.dom

object Hyp {

  def compile(program: Program): FunctionComponent[Unit] =
    initializeDrivers(program)._2

  def compile(view: Html[_]): FunctionComponent[Unit] =
    initializeDrivers(view)._2

  def run(program: Program, node: dom.Element): FunctionComponent[Unit] = {
    val (driver, component) = initializeDrivers(program)
    driver.run(node)
    component
  }

  def render(view: Html[_], node: dom.Element): FunctionComponent[Unit] = {
    val (driver, component) = initializeDrivers(view)
    driver.run(node)
    component
  }

  protected def initializeDrivers(
      view: Html[_]
    ): (Driver[FunctionComponent[Unit]], FunctionComponent[Unit]) = {
    val view0 = view
    val driver = PreactProjection.newProgram[Unit, Any](
      init = ((), Cmd.none),
      update = (_, _) => ((), Cmd.none),
      view = (_: Unit) => view0,
      subscriptions = (_: Unit) => Sub.none
    )
    (driver, driver.component)
  }

  protected def initializeDrivers(
      program: Program
    ): (Driver[FunctionComponent[Unit]], FunctionComponent[Unit]) = {
    val driver = PreactProjection.driver(program)
    (driver, driver.component)
  }
}
