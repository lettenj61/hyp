package com.github.thisisvesca

import com.github.thisisvesca.tea._
import org.scalajs.dom

object Vesca {
  def run[V](program: Program, node: dom.Element)(implicit projector: Projector[V]): V = {
    val driver = projector.driver(program)
    driver.run(node)
    driver.component
  }
}
