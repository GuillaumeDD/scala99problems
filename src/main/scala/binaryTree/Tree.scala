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

  /**
   * Collects the internal nodes of a binary tree in a list.
   */
  def internalList: List[T]

  /**
   * Collects the nodes at a given level in a list.
   */
  def atLevel(l: Int): List[T]

  /**
   * Computes the depth of the tree (starting from 1)
   */
  def depth: Int

  /**
   * Computes the maximum depth of the left subtree (starting from 1)
   */
  def maxLeftDepth: Int

  // TODO find a better way to separate Tree from PositionedNode
  /**
   * Layout a binary tree.
   * In this layout strategy, the position of a node v is obtained by the following two rules:
   *  - x(v) is equal to the position of the node v in the inorder sequence
   *  - y(v) is equal to the depth of the node v in the tree
   */
  def layoutBinaryTree: Tree[T] =
    this.helperLayoutBinaryTree(1, 1)._1

  /**
   * @see Problem 65
   */
  def layoutBinaryTree2: Tree[T] = {
    val maxDepth = this.depth
    val leftDepth = this.maxLeftDepth
    val xRoot = 1 + (for (i <- 2 to leftDepth) yield (Math.pow(2, maxDepth - i).toInt)).sum
    layoutBinaryTree2Helper(xRoot, 1, maxDepth - 2)
  }

  /**
   * Builds the preorder sequence of this binary tree.
   */
  def preorder: List[T]
  /**
   * Builds the inorder sequence of this binary tree.
   */
  def inorder: List[T]

  // TODO protect this helper method
  def layoutBinaryTree2Helper(x: Int, depth: Int, exp: Int): Tree[T]
  // TODO protect this helper method
  def helperLayoutBinaryTree(depth: Int, inorder: Int): (Tree[T], Int)
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

  /**
   * Constructs a complete binary tree.
   * It takes as parameters the number of nodes and the value to put in each node.
   */
  def completeBinaryTree[T](n: Int, elt: T): Tree[T] =
    {
      require(n >= 0)
      def generateTree(i: Int): Tree[T] =
        if (i > n) {
          End
        } else {
          Node(elt, generateTree(2 * i), generateTree(2 * i + 1))
        }

      generateTree(1)
    }
}

/**
 * Binary node of a tree.
 * @author Guillaume DUBUISSON DUPLESSIS <guillaume.dubuisson_duplessis@insa-rouen.fr>
 *
 */
sealed abstract class Node[+T] extends Tree[T] {
  def value: T
  def left: Tree[T]
  def right: Tree[T]

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

  def depth: Int =
    {
      val leftDepth = left.depth
      val rightDepth = right.depth
      1 + (leftDepth max rightDepth)
    }

  def maxLeftDepth: Int =
    1 + left.maxLeftDepth

  def internalList: List[T] =
    (left, right) match {
      case (End, End) => List()
      case _ =>
        value :: (left.internalList ::: right.internalList)
    }

  def atLevel(l: Int): List[T] =
    {
      require(l > 0)
      l match {
        case 1 => List(value)
        case n =>
          left.atLevel(n - 1) ::: right.atLevel(n - 1)
      }
    }

  def helperLayoutBinaryTree(depth: Int, inorder: Int): (Tree[T], Int) = {
    // Computes the left binary tree by incrementing the depth
    val (newLeft, newInorder) = left.helperLayoutBinaryTree(depth + 1, inorder)
    // Computes the right binary by incrementing the depth, and the inorder number
    val (newRight, nextInorder) = right.helperLayoutBinaryTree(depth + 1, newInorder + 1)
    // Returns a new positioned node with the next inorder number
    (PositionedNode(value, newLeft, newRight, newInorder, depth), nextInorder)
  }

  def layoutBinaryTree2Helper(x: Int, depth: Int, exp: Int): Tree[T] = {
    val xLeft = x - Math.pow(2, exp).toInt
    val xRight = x + Math.pow(2, exp).toInt
    PositionedNode(
      value,
      // New PositionedNode (left)
      left.layoutBinaryTree2Helper(
        xLeft,
        depth + 1,
        exp - 1),
      // New PositionedNode (right)
      right.layoutBinaryTree2Helper(
        xRight,
        depth + 1,
        exp - 1),
      x, depth)
  }

  def preorder: List[T] =
    value :: left.preorder ::: right.preorder

  def inorder: List[T] =
    left.inorder ::: (value :: right.inorder)

  override def toString = "T(" + value.toString + " " + left.toString + " " + right.toString + ")"
}

case class PositionedNode[+T](
  val value: T,
  val left: Tree[T],
  val right: Tree[T],
  x: Int,
  y: Int) extends Node[T] {

  override def toString = "T[" + x.toString + "," + y.toString + "](" + value.toString + " " + left.toString + " " + right.toString + ")"
}

object PositionedNode {
  def apply[T](value: T, x: Int, y: Int): PositionedNode[T] =
    PositionedNode(value, End, End, x, y)
}

/**
 * Factories and extractor for nodes.
 * @author Guillaume DUBUISSON DUPLESSIS <guillaume.dubuisson_duplessis@insa-rouen.fr>
 *
 */
object Node {
  def apply[T](value: T): Node[T] =
    NodeImpl(value, End, End)
  def apply[T](value: T, left: Tree[T], right: Tree[T]): Node[T] =
    NodeImpl(value, left, right)

  def unapply[T](t: Tree[T]): Option[(T, Tree[T], Tree[T])] =
    t match {
      case NodeImpl(value, left, right) =>
        Some((value, left, right))
      case PositionedNode(value, left, right, _, _) =>
        Some((value, left, right))
      case _ => None
    }

  private case class NodeImpl[+T](
    value: T,
    left: Tree[T],
    right: Tree[T]) extends Node[T]
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

  def internalList: List[Nothing] =
    List()

  def atLevel(l: Int): List[Nothing] =
    {
      require(l > 0)
      List()
    }

  def depth: Int =
    0

  def maxLeftDepth: Int =
    0

  def helperLayoutBinaryTree(depth: Int, inorder: Int): (Tree[Nothing], Int) =
    (End, inorder)

  def layoutBinaryTree2Helper(x: Int, depth: Int, exp: Int): Tree[Nothing] =
    End

  def preorder: List[Nothing] = List()

  def inorder: List[Nothing] = List()

  override def toString = "."
}
