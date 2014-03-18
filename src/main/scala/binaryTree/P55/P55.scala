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
package binaryTree.P55

import util.ExerciseTemplate
import binaryTree.Node
import binaryTree.End
import binaryTree.Tree

trait P55 extends ExerciseTemplate {
  /*
	P55 (**) Construct completely balanced binary trees.
    In a completely balanced binary tree, the following property holds for every node: 
    The number of nodes in its left subtree and the number of nodes in its right subtree are almost equal, which means their difference is not greater than one.

    Define an object named Tree. Write a function Tree.cBalanced to construct completely balanced binary trees for a given number of nodes. 
    The function should generate all solutions. 
    The function should take as parameters the number of nodes and a single value to put in all of them.

    scala> Tree.cBalanced(4, "x")
    res0: List(Node[String]) = List(T(x T(x . .) T(x . T(x . .))), T(x T(x . .) T(x T(x . .) .)), ...
 */
  val name = "P55 (Construct completely balanced binary trees)"
  def cBalanced[T](nbNodes: Int, value: T): List[Tree[T]]

  test("Invoking cBalanced on 0 node should return an End") {
    assert(cBalanced(0, "whatever") == List(End))
  }

  test("Invoking cBalanced on 1 node should return only one Node") {
    assert(cBalanced(1, "whatever") == List(Node("whatever")))
  }

  test("Invoking cBalanced on 2 nodes should return two possibilities") {
    val result = cBalanced(2, "whatever")
    val goodResult = List(
      Node("whatever", Node("whatever"), End),
      Node("whatever", End, Node("whatever")))
    assert(result.size == goodResult.size)
    assert(goodResult.forall(result.contains(_)))
  }

  test("Invoking cBalanced on 3 nodes should return only one Node") {
    val result = cBalanced(3, "whatever")
    val goodResult = List(
      Node("whatever", Node("whatever"), Node("whatever")))
    assert(result.size == goodResult.size)
    assert(goodResult.forall(result.contains(_)))
  }

  test("Invoking cBalanced on 4 nodes should return only one Node") {
    val result = cBalanced(4, "whatever")
    val goodResult = List(
      Node("whatever", Node("whatever", Node("whatever"), End), Node("whatever")),
      Node("whatever", Node("whatever", End, Node("whatever")), Node("whatever")),
      Node("whatever", Node("whatever"), Node("whatever", Node("whatever"), End)),
      Node("whatever", Node("whatever"), Node("whatever", End, Node("whatever"))))
    assert(result.size == goodResult.size)
    assert(goodResult.forall(result.contains(_)))
  }
}
