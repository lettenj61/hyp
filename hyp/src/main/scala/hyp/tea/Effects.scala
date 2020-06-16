package hyp
package tea

import scala.concurrent.duration._

sealed abstract class Sub[+A] {
  self =>

  def map[B](f: A => B): Sub[B]
}

object Sub {

  trait Timer[T]

  def every[A, T](sendToSelf: T => A)(duration: Duration)(implicit timer: Timer[T]): Sub[A] =
    ???

  def once[A, V](sendToSelf: V => A)(ask: => V): Sub[A] =
    Once(sendToSelf, () => ask)

  case object Empty extends Sub[Nothing] {
    def map[B](f: Nothing => B): Sub[B] = this
  }

  case class Once[V, A](sendToSelf: V => A, ask: () => V) extends Sub[A] {
    def map[B](f: A => B): Sub[B] =
      copy(sendToSelf = sendToSelf andThen f)
  }

  def none[A]: Sub[A] = Empty
}

sealed abstract class Cmd[+A] {
  def map[B](f: A => B): Cmd[B]
}

object Cmd {

  case object Empty extends Cmd[Nothing] {
    def map[B](f: Nothing => B): Cmd[B] = this
  }

  case class Now[A](get: A) extends Cmd[A] {
    def map[B](f: A => B): Cmd[B] =
      Now(f(get))
  }

  case class Defer[A](resolve: () => A) extends Cmd[A] {
    def map[B](f: A => B): Cmd[B] =
      Defer(() => f(resolve()))
  }

  def none[A]: Cmd[A] = Empty
}
