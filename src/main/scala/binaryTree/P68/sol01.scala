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
package binaryTree.P68

import org.scalatest.Args
import scala.collection.immutable.List
import binaryTree.Tree
import binaryTree.End
import binaryTree.Node

class sol01 extends P68 {
  def preorder[T](t: Tree[T]): List[T] =
    t match {
      case End => List()
      case Node(value, left, right) =>
        val leftPreorder = preorder(left)
        val rightPreorder = preorder(right)
        value :: (leftPreorder ::: rightPreorder)
    }

  def inorder[T](t: Tree[T]): List[T] =
    t match {
      case End => List()
      case Node(value, left, right) =>
        val leftPreorder = inorder(left)
        val rightPreorder = inorder(right)
        leftPreorder ::: (value :: rightPreorder)
    }

  def preInTree(
    preorder: List[Char],
    inorder: List[Char]): Tree[Char] =
    preorder match {
      case List() => End
      case preFirst :: preTail =>
        // Split the inorder list
        // - leftIn represents the left children of 'preFirst' (order: inorder)
        // - rightIn  represents the right children of 'preFirst' (order: inorder)
        val (leftIn, rightInWithPreFirst) = inorder.span(_ != preFirst)
        val rightIn = rightInWithPreFirst.tail // drop 'preFirst'

        // Split the tail of the preorder list
        // - leftPreTail represents the left children of 'preFirst' (order: preorder)
        // - rightPreTail represents the right children of 'preFirst' (order: preorder)
        val leftPreTail = preTail.take(leftIn.size)
        val rightPreTail = preTail.drop(leftIn.size)

        Node(preFirst,
          preInTree(leftPreTail, leftIn),
          preInTree(rightPreTail, rightIn))
    }
}
