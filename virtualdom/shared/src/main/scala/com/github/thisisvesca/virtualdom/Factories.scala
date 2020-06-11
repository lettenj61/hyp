package com.github.thisisvesca
package virtualdom

import VirtualDom._

trait TagFactory {

  class Builder(tag: String) {
    def apply[F](fragments: Fragment[F]*): Node[F] =
      makeNode(tag)(fragments: _*)
  }

  def text[F](value: String): Text[F] =
    Text(value)

  object node {
    def apply[F](tag: String)(fragments: Fragment[F]*): Node[F] =
      makeNode(tag)(fragments: _*)
  }

  def makeNode[F](tag: String)(fragments: Fragment[F]*): Tag[F] = {
    val (children, attributes) = fragments.partition {
      case Text(_)         => true
      case Tag(_, _, _)    => true
      case a: Attribute[_] => false
    }

    Tag(
      tag,
      attributes.toList.asInstanceOf[List[Attribute[F]]],
      children.toList.asInstanceOf[List[Node[F]]]
    )
  }

  protected def builder(name: String): Builder =
    new Builder(name)
}

trait AttributeProvider {
  type Value

  def attr[A](name: String)(implicit encode: A => Value): AttributeName[A] =
    new AttributeName[A](name)

  class AttributeName[A](name: String) {
    def create[F](value: A)(implicit encode: A => Value): Attribute[F] =
      new Property(name, encode(value))

    def :=[F](value: A)(implicit encode: A => Value): Attribute[F] =
      create(value)(encode)
  }
}

trait EventProvider {

  class EventName[E](name: String) {
    def bind[F](listener: E => F): EventHandler[F, E] =
      EventHandler(name, listener)

    def :=[F](listener: E => F): EventHandler[F, E] =
      bind(listener)
  }
}
