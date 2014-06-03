/*******************************************************************************
 * Copyright (c) 2014 Guillaume DUBUISSON DUPLESSIS <guillaume.dubuisson_duplessis@insa-rouen.fr>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     Guillaume DUBUISSON DUPLESSIS <guillaume.dubuisson_duplessis@insa-rouen.fr> - initial API and implementation
 ******************************************************************************/
package binaryTree.P65

import org.scalatest.Args
import binaryTree.Tree
import binaryTree.Node
import binaryTree.End
import binaryTree.PositionedNode

class sol01 extends P65 {
  def layoutBinaryTree2[T](t: Tree[T]): Tree[T] = {
    val maxDepth = depth(t)
    val leftDepth = maxLeftDepth(t)
    val xRoot = 1 + (for (i <- 2 to leftDepth) yield (Math.pow(2, maxDepth - i).toInt)).sum
    layoutBinaryTree2Helper(t, xRoot, 1, maxDepth - 2)
  }

  def layoutBinaryTree2Helper[T](t: Tree[T], x: Int, depth: Int, exp: Int): Tree[T] =
    t match {
      case End => End
      case Node(value, left, right) =>
        val xLeft = x - Math.pow(2, exp).toInt
        val xRight = x + Math.pow(2, exp).toInt
        PositionedNode(
          value,
          // New PositionedNode (left)
          layoutBinaryTree2Helper(left,
            xLeft,
            depth + 1,
            exp - 1),
          // New PositionedNode (right)
          layoutBinaryTree2Helper(right,
            xRight,
            depth + 1,
            exp - 1),
          x, depth)
    }

  def maxLeftDepth[T](t: Tree[T]): Int =
    t match {
      case End => 0
      case Node(_, left, right) =>
        1 + maxLeftDepth(left)
    }

  def depth[T](t: Tree[T]): Int =
    t match {
      case End => 0
      case Node(_, left, right) =>
        val leftDepth = depth(left)
        val rightDepth = depth(right)
        1 + (leftDepth max rightDepth)
    }
}
