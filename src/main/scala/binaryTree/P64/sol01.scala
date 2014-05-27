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
package binaryTree.P64

import org.scalatest.Args
import binaryTree.Tree
import binaryTree.End
import binaryTree.Node
import binaryTree.PositionedNode

class sol01 extends P64 {
  def layoutBinaryTree[T](t: Tree[T]): Tree[T] = {
    def helperLayoutBinaryTree[T](t: Tree[T], depth: Int, inorder: Int): (Tree[T], Int) =
      t match {
        case End =>
          (End, inorder)
        case Node(v, left, right) =>
          // Computes the left binary tree by incrementing the depth
          val (newLeft, newInorder) = helperLayoutBinaryTree(left, depth + 1, inorder)
          // Computes the right binary by incrementing the depth, and the inorder number
          val (newRight, nextInorder) = helperLayoutBinaryTree(right, depth + 1, newInorder + 1)
          // Returns a new positioned node with the next inorder number
          (PositionedNode(v, newLeft, newRight, newInorder, depth), nextInorder)
      }

    t match {
      case End => End
      case Node(v, left, right) =>
        helperLayoutBinaryTree(t, 1, 1)._1
    }
  }
}
