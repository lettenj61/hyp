package com.github.thisisvesca
package tea

sealed abstract class Sub[+A] {
  self =>

  def map[B](f: A => B): Sub[B]
}

object Sub {

  case object Empty extends Sub[Nothing] {
    def map[B](f: Nothing => B): Sub[B] = this
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

  val none: Cmd[Nothing] = Empty
}
