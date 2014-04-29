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
package binaryTree.P61A

import util.ExerciseTemplate
import binaryTree.Tree
import binaryTree.Node
import binaryTree.End

trait P61A extends ExerciseTemplate {
  /*
  	61A (*) Collect the leaves of a binary tree in a list.
    A leaf is a node with no successors. Write a method leafList to collect them in a list.

    scala> Node('a', Node('b'), Node('c', Node('d'), Node('e'))).leafList
    res0: List[Char] = List(b, d, e)
   */
  val name = "61A (Collect the leaves of a binary tree in a list)"

  def leafList[T](t: Tree[T]): List[T]

  test("Invoking leafList should return the list of leaves of a binary tree") {
    assert(leafList(End) == List())
    assert(leafList(Node('a)) == List('a))
    assert(leafList(Node('a, Node('b), End)) == List('b))
    assert(leafList(Node('a, End, Node('b))) == List('b))

    val result01 = leafList(Node('a, Node('b), Node('c)))
    assert(result01.size == 2)
    assert(List('b, 'c).forall(result01.contains(_)))

    /*
     *              'a
     *              / \
     *            'b   'c
     *            /
     *          'b1
     *          /
     *        'b2
     */
    val left = Node('b, Node('b1, Node('b2), End), End)
    val right = Node('c)
    val tree = Node('a, left, right)
    val result02 = leafList(tree)
    assert(result02.size == 2)
    assert(List('b2, 'c).forall(result02.contains(_)))

    /*
     *              'a
     *             /   \
     *           'b     'c
     *             \    /  \
     *             'b1 'c1  'c2
     *                      /
     *                    'c3
     *
     */
    val result03 = leafList(Node('a, Node('b, End, Node('b1)), Node('c, Node('c1), Node('c2, Node('c3), End))))
    assert(result03.size == 3)
    assert(List('b1, 'c1, 'c3).forall(result03.contains(_)))

    // Example test
    val result04 = leafList(Node('a', Node('b'), Node('c', Node('d'), Node('e'))))
    assert(result04.size == 3)
    assert(List('b', 'd', 'e').forall(result04.contains(_)))
  }
}
