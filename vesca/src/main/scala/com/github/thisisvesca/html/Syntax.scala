package com.github.thisisvesca
package html

import scala.scalajs.js

import Html._

trait HtmlFactory {

  def text[F](value: String): Text[F] =
    Text(value)

  class Builder(tag: String) {
    def apply[F](fragments: Fragment[F]*): Html[F] =
      makeNode(tag)(fragments: _*)
  }

  object node {
    def apply[F](tag: String)(fragments: Fragment[F]*): Html[F] =
      makeNode(tag)(fragments: _*)
  }

  protected def makeNode[F](tag: String)(fragments: Fragment[F]*): Node[F] = {
    val (children, attributes) = fragments.partition {
      case Text(_)         => true
      case Node(_, _, _)   => true
      case a: Attribute[_] => false
    }
    Node(
      tag,
      attributes.toList.asInstanceOf[List[Attribute[F]]],
      children.toList.asInstanceOf[List[Html[F]]]
    )
  }
}
