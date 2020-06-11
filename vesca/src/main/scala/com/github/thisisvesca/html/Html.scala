package com.github.thisisvesca
package html

import scala.scalajs.js

sealed trait Fragment[+F]
sealed abstract class Html[+F]      extends Fragment[F]
sealed abstract class Attribute[+F] extends Fragment[F]

object Html {

  class Property[+F](val name: String, val value: js.Any) extends Attribute[F]
  object Property {
    def unapply[F](self: Property[F]): Option[(String, js.Any)] = self match {
      case prop: Property[_] =>
        Some(prop.name -> prop.value)

      case _ =>
        None
    }
  }
  case class ClassName[+F](token: String)               extends Attribute[F]
  case class EventHandler[+F, A](ty: String, f: A => F) extends Attribute[F]
  case object NoAttribute                               extends Attribute[Nothing]

  case class Node[+F](tag: String, attributes: List[Attribute[F]], children: List[Html[F]])
      extends Html[F]

  case class Text[+F](value: String) extends Html[F]

  class AttributeName[A](name: String) {
    def create[F](value: A)(implicit encode: A => js.Any): Attribute[F] =
      new Property(name, encode(value))

    def :=[F](value: A)(implicit encode: A => js.Any): Attribute[F] =
      create(value)(encode)
  }
}
