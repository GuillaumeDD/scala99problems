/**
 * *****************************************************************************
 * Copyright (c) 2014 Guillaume DUBUISSON DUPLESSIS <guillaume.dubuisson_duplessis@insa-rouen.fr>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 *
 * Contributors:
 *     Guillaume DUBUISSON DUPLESSIS <guillaume.dubuisson_duplessis@insa-rouen.fr> - initial API and implementation
 * ****************************************************************************
 */
package binaryTree

/**
 * @author Guillaume DUBUISSON DUPLESSIS <guillaume.dubuisson_duplessis@insa-rouen.fr>
 *
 * A binary tree is either empty or is composed of a root element and two successors,
 * which are binary tree themselves.
 */
sealed abstract class Tree[+T]

object Tree {
  def cBalanced[T](nbNodes: Int, value: T): List[Tree[T]] = {
    def isOdd(n: Int): Boolean =
      n % 2 == 1
    def isEven(n: Int): Boolean =
      n % 2 == 0
    nbNodes match {
      case n if n <= 0 =>
        List(End)

      case n if isOdd(n) =>
        val subtrees = cBalanced(n / 2, value)
        for {
          left <- subtrees
          right <- subtrees
        } yield (Node(value, left, right))

      case n if isEven(n) =>
        val lesserSubtrees = cBalanced((n - 1) / 2, value)
        val greaterSubtrees = cBalanced((n - 1) / 2 + 1, value)
        (for {
          left <- lesserSubtrees
          right <- greaterSubtrees
        } yield (List(Node(value, left, right), Node(value, right, left))))
          .flatten
    }
  }
}

case class Node[+T](
  value: T,
  left: Tree[T],
  right: Tree[T]) extends Tree[T] {
  override def toString = "T(" + value.toString + " " + left.toString + " " + right.toString + ")"
}

object Node {
  def apply[T](value: T): Node[T] = Node(value, End, End)
}

case object End extends Tree[Nothing] {
  override def toString = "."
}
