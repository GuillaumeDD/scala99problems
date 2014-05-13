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
package binaryTree.P62B

import util.ExerciseTemplate
import binaryTree.Node
import binaryTree.Tree
import binaryTree.End

trait P62B extends ExerciseTemplate {
  /*
	 P62B (*) Collect the nodes at a given level in a list.
    A node of a binary tree is at level N if the path from the root to the node has length N-1. 
    The root node is at level 1. 
    Write a method atLevel to collect all nodes at a given level in a list.

    scala> Node('a', Node('b'), Node('c', Node('d'), Node('e'))).atLevel(2)
    res0: List[Char] = List(b, c)

    Using atLevel it is easy to construct a method levelOrder which creates the level-order sequence of the nodes. However, there are more efficient ways to do that.
   */
  val name = "Collect the nodes at a given level in a list (P62B)"
  def atLevel[T](l: Int, t: Tree[T]): List[T]

  test("Invoking atLevel with a level < 1 should throw an IllegalArgumentException") {
    intercept[IllegalArgumentException] {
      atLevel(0, End)
    }

    intercept[IllegalArgumentException] {
      atLevel(-42, End)
    }
  }

  test("Invoking atLevel on an End should never return nodes") {
    assert(atLevel(1, End) == List())
    assert(atLevel(2, End) == List())
    assert(atLevel(3, End) == List())
    assert(atLevel(4, End) == List())
    assert(atLevel(5, End) == List())
  }

  test("Invoking atLevel on a tree should return nodes at the given level") {
    /*
     *    'a
     *   /  \
     * 'c    'b
     * 	     /
     * 	   'd
     */
    val tree = Node('a, Node('c), Node('b, Node('d), End))
    // Level: 1
    assert(atLevel(1, tree) == List('a))

    // Level: 2
    val result01 = atLevel(2, tree)
    assert(result01.size == 2)
    assert(List('c, 'b).forall(result01.contains(_)))

    // Level: 3
    assert(atLevel(3, tree) == List('d))

    // Other levels
    assert(atLevel(4, tree) == List())
    assert(atLevel(5, tree) == List())

    val exampleTree = Node('a', Node('b'), Node('c', Node('d'), Node('e')))
    val result02 = atLevel(2, exampleTree)

    assert(result02.size == 2)
    assert(List('b', 'c').forall(result02.contains(_)))
  }
}
