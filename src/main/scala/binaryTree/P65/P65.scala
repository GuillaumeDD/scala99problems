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
package binaryTree.P65

import util.ExerciseTemplate
import binaryTree.Tree
import binaryTree.End
import binaryTree.Node
import binaryTree.PositionedNode

trait P65 extends ExerciseTemplate {
  /*
    P65 (**) Layout a binary tree (2).
    An alternative layout method is depicted in the illustration opposite. 
    Find out the rules and write the corresponding method. 
    Hint: On a given level, the horizontal distance between neighboring nodes is constant.

    Use the same conventions as in problem P64.

    scala> Node('a', Node('b', End, Node('c')), Node('d')).layoutBinaryTree2
    res0: PositionedNode[Char] = T[3,1]('a T[1,2]('b . T[2,3]('c . .)) T[5,2]('d . .))

    The tree at right may be constructed with Tree.fromList(List('n','k','m','c','a','e','d','g','u','p','q')). Use it to check your code.
 */
  /*
   * For a node u:
   *  - x(u) = x(p) - 2^(maxDepth - d) for a left node or x(p) + 2^(maxDepth - d) for a right node, 
   *    where maxDepth represents the depth of the tree 
   *    and p represents the parent node.
   *  - y(u) = d, where d represents its depth (starting from 1)
   *  
   *  If r is the root of the tree: 
   *   x(r) = 1 + SUM(2^(maxDepth - i)) for i from 2 to maxLeftDepth
   */

  val name = "Layout a binary tree 2 (P65)"
  def layoutBinaryTree2[T](t: Tree[T]): Tree[T]

  test("Invoking layoutBinaryTree2 on an End should return an End") {
    assert(layoutBinaryTree2(End) == End)
  }

  test("Invoking layoutBinaryTree2 on a Node should return a PositionedNode") {
    assert(layoutBinaryTree2(Node('a)) == PositionedNode('a, 1, 1))
  }

  test("Invoking layoutBinaryTree2 on a non-empty Tree should return a tree of PositionedNodes") {
    val d = Node('d')
    val g = Node('g')
    val e = Node('e', d, g)
    val a = Node('a')
    val c = Node('c', a, e)
    val m = Node('m')
    val k = Node('k', c, m)

    val q = Node('q')
    val p = Node('p', End, q)
    val u = Node('u', p, End)

    val tree = Node('n', k, u)

    val result = layoutBinaryTree2(tree)

    val d_ = PositionedNode('d', 4, 5)
    val g_ = PositionedNode('g', 6, 5)
    val e_ = PositionedNode('e', d_, g_, 5, 4)
    val a_ = PositionedNode('a', 1, 4)
    val c_ = PositionedNode('c', a_, e_, 3, 3)
    val m_ = PositionedNode('m', 11, 3)
    val k_ = PositionedNode('k', c_, m_, 7, 2)

    val q_ = PositionedNode('q', 21, 4)
    val p_ = PositionedNode('p', End, q_, 19, 3)
    val u_ = PositionedNode('u', p_, End, 23, 2)

    val solution = PositionedNode('n', k_, u_, 15, 1)

    assert(result == solution)
  }
}
