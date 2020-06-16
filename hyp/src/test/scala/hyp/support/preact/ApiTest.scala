package hyp.support.preact

import org.scalatest.funspec._

import hyp.html._
import hyp.internal.facade.preact._

class ApiTest extends AnyFunSpec {
  describe("Hyp object") {
    it("render text") {
      val F = Hyp.compile(Html.text("it works"))
      val res = PreactRenderToString.render(Preact.h(F))
      assert(res == "it works")
    }

    it("accepts list of nodes") {
      val view: Html[Nothing] = for (i <- 1 to 4) yield Html.li(Html.text(s"$i"))
      val F = Hyp.compile(view)
      val res = PreactRenderToString.render(Preact.h(F))
      assert(res == "<li>1</li><li>2</li><li>3</li><li>4</li>")
    }
  }
}
