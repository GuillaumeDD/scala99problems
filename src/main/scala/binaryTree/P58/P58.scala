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
package binaryTree.P58

import util.ExerciseTemplate
import binaryTree.Tree
import binaryTree.End
import binaryTree.Node

trait P58 extends ExerciseTemplate {
  /*
	P58 (**) Generate-and-test paradigm.
    Apply the generate-and-test paradigm to construct all symmetric, completely balanced binary trees with a given number of nodes.

    scala> Tree.symmetricBalancedTrees(5, "x")
    res0: List[Node[String]] = List(T(x T(x . T(x . .)) T(x T(x . .) .)), T(x T(x T(x . .) .) T(x . T(x . .))))
   */
  val name = "P58 (Generate-and-test paradigm)"
  def symmetricBalancedTrees[T](n: Int, elt: T): List[Tree[T]]

  test("Invoking symmetricBalancedTrees n=0 should return an End") {
    assert(symmetricBalancedTrees(0, 'a) == List(End))
  }

  test("Invoking symmetricBalancedTrees on 1 should return a Node") {
    assert(symmetricBalancedTrees(1, 'a) == List(Node('a)))
  }

  test("Invoking symmetricBalancedTrees on 2 should return an empty list") {
    assert(symmetricBalancedTrees(2, 'a) == List())
  }

  test("Invoking symmetricBalancedTrees on 3 should return only one Tree") {
    assert(symmetricBalancedTrees(3, 'a) == List(Node('a, Node('a), Node('a))))
  }
}
