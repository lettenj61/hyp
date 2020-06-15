package com.github.thisisvesca.facade.enzyme

import scala.scalajs.js
import scala.scalajs.js.|
import scala.scalajs.js.annotation._

import com.github.thisisvesca.facade.preact._

@js.native
@JSImport("enzyme", JSImport.Default)
object Enzyme extends js.Object {
  def shallow(node: VNode): ShallowWrapper = js.native
  def configure(options: AdapterOptions): Unit = js.native
}

trait AdapterOptions extends js.Object {
  val adapter: js.UndefOr[Adapter] = js.undefined
}

@js.native
trait Adapter extends js.Object

@js.native
@JSImport("enzyme-adapter-preact-pure", JSImport.Default)
class PreactAdapter() extends Adapter

@js.native
trait ShallowWrapper extends js.Object {
  type Self = ShallowWrapper

  def find(selector: String | FunctionComponent[_]): Self
  def findWhere(predicate: Self => Boolean): Self
  def filter(selector: String | FunctionComponent[_]): Self
  def filterWhere(predicate: Self => Boolean): Self
  def contains(node: VNode): Boolean
  def contains(nodes: js.Array[VNode]): Boolean
  def equals(node: VNode): Boolean
  def hasClass(selector: String): Boolean
  def is(selector: String | FunctionComponent[_]): Boolean
  def exists(selector: String | FunctionComponent[_] = js.native): Boolean
  def text(): String
  def html(): String
  def props(): js.Object
  @JSName("props")
  def propsAs[A](): A
  def prop(key: String): js.Any
  @JSName("prop")
  def propAs[A](key: String): A
  def key(): String
  def update(): this.type
  def debug(): String
}

