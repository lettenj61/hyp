package com.github.thisisvesca
package tea

sealed abstract class Sub[+A] {
  self =>

  def map[B](f: A => B): Sub[B]
}

object Sub {

  def once[A, V](onFulfilled: V => A)(ask: => V): Sub[A] =
    Once(onFulfilled, () => ask)

  case object Empty extends Sub[Nothing] {
    def map[B](f: Nothing => B): Sub[B] = this
  }

  case class Once[V, A](onFulfilled: V => A, ask: () => V) extends Sub[A] {
    def map[B](f: A => B): Sub[B] =
      copy(onFulfilled = onFulfilled andThen f)
  }

  def none: Sub[Nothing] = Empty
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

  val none: Cmd[Nothing] = Empty
}
