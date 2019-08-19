package com.github.thisisvesca
package html

sealed trait Node[+A] {
  def map[B](f: A => B): Node[B]
}

sealed trait Html[+A] extends Node[A] {
  def map[B](f: A => B): Html[B]
}
