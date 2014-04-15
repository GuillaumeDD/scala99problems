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
package binaryTree.P60

import util.ExerciseTemplate
import binaryTree.Tree
import binaryTree.End
import binaryTree.Node

trait P60 extends ExerciseTemplate {
  /*
    P60 (**) Construct height-balanced binary trees with a given number of nodes.
    Consider a height-balanced binary tree of height H. What is the maximum number of nodes it can contain? 
    Clearly, MaxN = 2^H - 1. 
    However, what is the minimum number MinN? 
    This question is more difficult. 
    Try to find a recursive statement and turn it into a function minHbalNodes that takes a height and returns MinN.

    scala> minHbalNodes(3)
    res0: Int = 4

    On the other hand, we might ask: what is the maximum height H a height-balanced binary tree with N nodes can have? Write a maxHbalHeight function.

    scala> maxHbalHeight(4)
    res1: Int = 3

    Now, we can attack the main problem: construct all the height-balanced binary trees with a given number of nodes.

    scala> Tree.hbalTreesWithNodes(4, "x")
    res2: List[Node[String]] = List(T(x T(x T(x . .) .) T(x . .)), T(x T(x . T(x . .)) T(x . .)), ...

    Find out how many height-balanced trees exist for N = 15.
 */
  val name = "P60 (Construct height-balanced binary trees with a given number of nodes)"

  /**
   * Returns the maximum number of nodes in a height-balanced binary tree of height 'height'
   */
  def maxHbalNodes(height: Int): Int =
    scala.math.pow(2, height).toInt - 1
  /**
   * Returns the minimum number of nodes in a height-balanced binary tree of height 'height'
   */
  def minHbalNodes(height: Int): Int

  /**
   * Returns the maximum height a height-balanced binary tree with n nodes can have
   */
  def maxHbalHeight(n: Int): Int

  /**
   * Returns the minimum height a height-balanced binary tree with n nodes can have
   */
  def minHbalHeight(n: Int): Int

  def hbalTreesWithNodes[T](n: Int, elem: T): List[Tree[T]]

  test("Invoking maxHbalNodes should return the maximum number of nodes that is contained in a height-balanced binay tree of a given height") {
    assert(maxHbalNodes(0) == 0)
    assert(maxHbalNodes(1) == 1)
    assert(maxHbalNodes(2) == 3)
    assert(maxHbalNodes(3) == 7)
    assert(maxHbalNodes(4) == 15)
    assert(maxHbalNodes(5) == 31)
  }

  test("Invoking minHbalNodes should return the minimum number of nodes that is contained in a height-balanced binay tree of a given height") {
    assert(minHbalNodes(0) == 0)
    assert(minHbalNodes(1) == 1)
    assert(minHbalNodes(2) == 2)
    assert(minHbalNodes(3) == 4)
    assert(minHbalNodes(4) == 7)
    assert(minHbalNodes(5) == 12)
  }

  test("Invoking maxHbalHeight should return the maximum height a height-balanced binary tree with n nodes can have") {
    assert(maxHbalHeight(0) == 0)
    assert(maxHbalHeight(1) == 1)
    assert(maxHbalHeight(2) == 2)
    assert(maxHbalHeight(3) == 2)
    assert(maxHbalHeight(4) == 3)
    assert(maxHbalHeight(5) == 3)
    assert(maxHbalHeight(6) == 3)
    assert(maxHbalHeight(7) == 4)
    assert(maxHbalHeight(8) == 4)
    assert(maxHbalHeight(9) == 4)
    assert(maxHbalHeight(10) == 4)
    assert(maxHbalHeight(11) == 4)
    assert(maxHbalHeight(12) == 5)
  }

  test("Invoking minHbalHeight should return the minimum height a height-balanced binary tree with n nodes can have") {
    assert(minHbalHeight(0) == 0)
    assert(minHbalHeight(1) == 1)
    assert(minHbalHeight(2) == 2)
    assert(minHbalHeight(3) == 2)
    assert(minHbalHeight(4) == 3)
    assert(minHbalHeight(5) == 3)
    assert(minHbalHeight(6) == 3)
    assert(minHbalHeight(7) == 3)
    assert(minHbalHeight(8) == 4)
    assert(minHbalHeight(9) == 4)
    assert(minHbalHeight(10) == 4)
    assert(minHbalHeight(11) == 4)
    assert(minHbalHeight(12) == 4)
    assert(minHbalHeight(13) == 4)
    assert(minHbalHeight(14) == 4)
    assert(minHbalHeight(15) == 4)
    assert(minHbalHeight(16) == 5)
  }

  test("Invoking hbalTreesWithNodes should return all the height-balanced trees with n nodes") {
    assert(hbalTreesWithNodes(0, 'a) == List(End))
    assert(hbalTreesWithNodes(1, 'a) == List(Node('a, End, End)))

    /*
     *     'a      'a
     *     /   OR    \
     *   'a          'a
     *         
     */
    assert(hbalTreesWithNodes(2, 'a) == List(Node('a, Node('a), End), Node('a, End, Node('a))))

    /*
     *     'a
     *     / \
     *   'a   'a
     *         
     */
    assert(hbalTreesWithNodes(3, 'a) == List(Node('a, Node('a), Node('a))))

    /*
     * Height: 3
     *      'a
     *     /   \
     *   'a     'a
     *   / \    /  \
     * 'a  'a 'a   'a
     * 
     * Height: 4
     *              'a                'a
     *             /   \             /   \
     *           'a     'a         'a     'a  }
     *           / \    /          / \    /   } right tree change           ...
     *         'a  'a 'a         'a  'a 'a    }
     *         /                   \               } 4 possibilities
     *       'a                     'a             } 
     *
     * ACCOUNT
     *    4 trees
     *  x 2 (right tree change)
     * ----
     *    8 trees
     *  x 2 (mirror effect between left and right tree)
     * ----
     *   16 
     *
     *  TOTAL = 1 + 16 = 17
     */
    assert(hbalTreesWithNodes(7, 'a).distinct.size == 17)
  }
}
