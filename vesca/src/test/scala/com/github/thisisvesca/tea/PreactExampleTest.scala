package com.github.thisisvesca
package tea

import scala.language.implicitConversions

import scala.scalajs.js
import org.scalajs.dom

import org.scalatest._
import org.scalatest.funspec._

// TODO: consider switching back to minitest
class PreactExampleTest extends AnyFunSpec {
  import com.github.thisisvesca.facade.preact._

  describe("preact facade") {
    implicit def stringComponent(text: String): ComponentChild =
      text.asInstanceOf[ComponentChild]

    // TODO: We may need full enzyme support
    it("render component") {
      val app  = Preact.h("p", null, "Hello")
      val html = PreactRenderToString.render(app)
      assert(html == "<p>Hello</p>")
    }
  }
}
