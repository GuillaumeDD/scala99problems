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
sealed abstract class Tree[+T] {
  def isSymmetric: Boolean
  def isMirrorOf[V](t: Tree[V]): Boolean

  /**
   * Adds an element to the binary search tree
   * @return a binary search tree with a the new element added
   */
  def addValue[U >: T <% Ordered[U]](x: U): Tree[U]

  /**
   * Computes the number of node in a tree
   * @return the number of node in a tree
   */
  def nodeNumber: Int

  /**
   * Computes the number of leaf in a tree
   * @return the number of leaf in a tree
   */
  def leafCount: Int

  /**
   * Collect the leaves of a binary tree in a list.
   */
  def leafList: List[T]
}

object Tree {

  /**
   * Returns a binary search tree from the list of elements
   *
   */
  def fromList[U <% Ordered[U]](l: List[U]): Tree[U] =
    l.foldLeft(End: Tree[U])((result, elt) => result.addValue(elt))

  /**
   * Returns a balanced tree with the same value on each node
   * @param nbNodes Number of nodes of the tree
   * @param value Default value
   *
   */
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

  /**
   * Construct height-balanced binary trees for a given height with a supplied value for the nodes
   */
  def hbalTrees[T](n: Int, elem: T): List[Tree[T]] =
    n match {
      case 0 => List(End)
      case 1 => List(Node(elem))
      case h =>
        val subtrees = hbalTrees(h - 1, elem)
        val smallerSubtrees = hbalTrees(h - 2, elem)
        (for {
          l <- subtrees
          r <- subtrees
        } yield (Node(elem, l, r))) ++ (
          for {
            l <- subtrees
            r <- smallerSubtrees
          } yield (List(Node(elem, l, r), Node(elem, r, l))))
          .flatten
    }

  /**
   * Returns the maximum number of nodes in a height-balanced binary tree of height 'height'
   */
  def maxHbalNodes(height: Int): Int =
    scala.math.pow(2, height).toInt - 1

  /**
   * Returns the minimum number of nodes in a height-balanced binary tree of height 'height'
   */
  def minHbalNodes(height: Int): Int =
    height match {
      case n if n <= 0 => 0
      case 1 => 1
      case n =>
        1 + minHbalNodes(n - 1) + minHbalNodes(n - 2)
    }

  /**
   * Returns the maximum height a height-balanced binary tree with n nodes can have
   */
  def maxHbalHeight(n: Int): Int =
    {
      var i = 0
      // Select the maximum height such that the minimum number of nodes in a height-balanced tree of this height
      // is inferior or equals to n
      while (minHbalNodes(i) <= n) {
        i = i + 1
      }
      i - 1
    }

  /**
   * Returns the minimum height a height-balanced binary tree with n nodes can have
   */
  def minHbalHeight(n: Int): Int =
    {
      var i = 0
      // Select the minimum height such that the maximum number of nodes in a height-balanced tree of this height
      // is superior or equals to n
      while (maxHbalNodes(i) < n) {
        i = i + 1
      }
      i
    }

  /**
   * Construct all the height-balanced binary trees with a given number of nodes.
   * @param n number of nodes of the tree
   * @param elem value of the nodes of the tree
   * @return all the height-balanced binary trees with a given number of nodes
   */
  def hbalTreesWithNodes[T](n: Int, elem: T): List[Tree[T]] =
    {
      // Construction of a range of heights that includes the height-balanced trees of this height with n nodes
      val rangeOfHeights = (minHbalHeight(n) to maxHbalHeight(n)).toList

      // Build all height-balanced trees of those heights
      val trees =
        (for (h <- rangeOfHeights)
          yield (Tree.hbalTrees(h, elem)))
          .flatten

      // Only keep trees with correct number of nodes
      trees.filter(_.nodeNumber == n)
    }
}

case class Node[+T](
  value: T,
  left: Tree[T],
  right: Tree[T]) extends Tree[T] {
  def isSymmetric: Boolean =
    left.isMirrorOf(right)

  def isMirrorOf[V](t: Tree[V]): Boolean =
    t match {
      case tree: Node[V] =>
        left.isMirrorOf(tree.right) && right.isMirrorOf(tree.left)
      case _ =>
        false
    }

  def addValue[U >: T <% Ordered[U]](x: U): Tree[U] =
    if (x > value) {
      Node(value, left, right.addValue(x))
    } else {
      Node(value, left.addValue(x), right)
    }

  def nodeNumber: Int =
    1 + left.nodeNumber + right.nodeNumber

  def leafCount: Int =
    (left, right) match {
      case (End, End) => 1
      case _ =>
        left.leafCount + right.leafCount
    }

  def leafList: List[T] =
    (left, right) match {
      case (End, End) => List(value)
      case _ =>
        left.leafList ::: right.leafList
    }

  override def toString = "T(" + value.toString + " " + left.toString + " " + right.toString + ")"
}

object Node {
  def apply[T](value: T): Node[T] = Node(value, End, End)
}

case object End extends Tree[Nothing] {
  def isSymmetric: Boolean = true

  def isMirrorOf[V](t: Tree[V]): Boolean =
    t == End

  def addValue[U <% Ordered[U]](x: U): Tree[U] =
    Node(x, End, End)

  def nodeNumber: Int =
    0

  def leafCount: Int =
    0

  def leafList: List[Nothing] = List()

  override def toString = "."
}
