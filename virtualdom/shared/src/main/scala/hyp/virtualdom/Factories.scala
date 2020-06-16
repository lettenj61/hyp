package hyp
package virtualdom

import VirtualDom._

trait TagFactory {

  class Builder(tag: String) {
    def apply[F](fragments: Fragment[F]*): Tag[F] =
      makeNode(tag)(fragments: _*)
  }

  def text[F](value: String): Text[F] =
    Text(value)

  object node {
    def apply[F](tag: String)(fragments: Fragment[F]*): Tag[F] =
      makeNode(tag)(fragments: _*)
  }

  def makeNode[F](tag: String)(fragments: Fragment[F]*): Tag[F] = {
    val (children, attributes) = fragments.partition {
      case Text(_)         => true
      case Tag(_, _, _)    => true
      case TagSeq(_)       => true
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

  def attr[A](name: String): AttributeName[A] =
    new AttributeName[A](name)

  class AttributeName[A](val name: String) {
    def create[F](value: A)(implicit encode: A => Value): Attribute[F] =
      new Property(name, encode(value))

    def :=[F](value: A)(implicit encode: A => Value): Attribute[F] =
      create(value)(encode)
  }
}

trait AttributeIntrinsic {
  self: AttributeProvider =>

  protected def makeStyleMap(values: Iterable[(String, String)]): Value

  object style extends AttributeName[String]("style") {
    def ++=[F](values: Iterable[(String, String)]): Attribute[F] =
      new Property(name, makeStyleMap(values))
  }

  protected def makeClassList(values: Iterable[(String, Boolean)]): Value

  object classList {
    def ++=[F](values: Iterable[(String, Boolean)]): Attribute[F] =
      new Property("class", makeClassList(values))
  }
}

trait EventProvider {

  def event[E](name: String): EventName[E] =
    new EventName[E](name)

  trait BaseEventName[E] {
    def name: String
    protected def useCapture: Boolean

    def bind[F](listener: E => F): EventHandler[F, E] =
      EventHandler(name, listener, useCapture)

    def :=[F](listener: E => F): EventHandler[F, E] =
      bind(listener)
  }

  class EventName[E](val name: String) extends BaseEventName[E] {
    protected val useCapture: Boolean = false
    lazy val capture: EventCapture[E] =
      new EventCapture(name)
  }

  class EventCapture[E](val name: String) extends BaseEventName[E] {
    protected val useCapture: Boolean = true
  }
}
