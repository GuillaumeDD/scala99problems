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
package binaryTree.P64

import util.ExerciseTemplate
import binaryTree.Tree
import binaryTree.End
import binaryTree.Node
import binaryTree.PositionedNode

trait P64 extends ExerciseTemplate {
  /*
   P64 (**) Layout a binary tree (1).
    As a preparation for drawing a tree, a layout algorithm is required to determine the position of each node in a rectangular grid. 
    Several layout methods are conceivable, one of them is shown in the illustration on the right.

    In this layout strategy, the position of a node v is obtained by the following two rules:

        x(v) is equal to the position of the node v in the inorder sequence
        y(v) is equal to the depth of the node v in the tree

    In order to store the position of the nodes, we add a new class with the additional information.

    case class PositionedNode[+T](override val value: T, override val left: Tree[T], override val right: Tree[T], x: Int, y: Int) extends Node[T](value, left, right) {
      override def toString = "T[" + x.toString + "," + y.toString + "](" + value.toString + " " + left.toString + " " + right.toString + ")"
    }

    Write a method layoutBinaryTree that turns a tree of normal Nodes into a tree of PositionedNodes.

    scala> Node('a', Node('b', End, Node('c')), Node('d')).layoutBinaryTree
    res0: PositionedNode[Char] = T[3,1](a T[1,2](b . T[2,3](c . .)) T[4,2](d . .))
  */
  val name = "Layout a binary tree 1 (P64)"
  def layoutBinaryTree[T](t: Tree[T]): Tree[T]

  test("Invoking layoutBinaryTree on an End should return an End") {
    assert(layoutBinaryTree(End) == End)
  }

  test("Invoking layoutBinaryTree on a Node should return a PositionedNode") {
    assert(layoutBinaryTree(Node('a)) == PositionedNode('a, 1, 1))
  }

  test("Invoking layoutBinaryTree on a non-empty Tree should return a tree of PositionedNodes") {
    val result = layoutBinaryTree(Node('a', Node('b', End, Node('c')), Node('d')))
    val c = PositionedNode('c', 2, 3)
    val b = PositionedNode('b', End, c, 1, 2)
    val d = PositionedNode('d', 4, 2)
    val solution = PositionedNode('a', b, d, 3, 1)

    assert(result == solution)
  }
}
