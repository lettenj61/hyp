package hyp
package virtualdom

import scala.language.implicitConversions

sealed trait Fragment[+F]
sealed abstract class Node[+F]      extends Fragment[F]
object Node {
  implicit def seqToTags[F](xs: Seq[Node[F]]): VirtualDom.TagSeq[F] =
    VirtualDom.TagSeq(xs)
}
sealed abstract class Attribute[+F] extends Fragment[F]

object VirtualDom {

  class Property[+F](val name: String, val value: Any) extends Attribute[F]
  object Property {
    def unapply[F](self: Property[F]): Option[(String, Any)] = self match {
      case prop: Property[_] =>
        Some(prop.name -> prop.value)

      case _ =>
        None
    }
  }
  case class EventHandler[+F, A](ty: String, f: A => F, capture: Boolean) extends Attribute[F]
  case object NoAttribute                               extends Attribute[Nothing]

  case class Tag[+F](tag: String, attributes: List[Attribute[F]], children: List[Node[F]])
      extends Node[F]

  case class TagSeq[+F](items: Seq[Node[F]]) extends Node[F]

  case class Text[+F](value: String) extends Node[F]
}
