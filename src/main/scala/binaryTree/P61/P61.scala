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
package binaryTree.P61

import util.ExerciseTemplate
import binaryTree.Tree
import binaryTree.End
import binaryTree.Node

trait P61 extends ExerciseTemplate {
  /*
    P61 (*) Count the leaves of a binary tree.
    A leaf is a node with no successors. Write a method leafCount to count them.

    scala> Node('x', Node('x'), End).leafCount
    res0: Int = 1 
   */
  val name = "P61 (Count the leaves of a binary tree)"

  def leafCount[T](t: Tree[T]): Int

  test("Invoking leafCount should return the number of leaves of a binary tree") {
    assert(leafCount(End) == 0)
    assert(leafCount(Node('a)) == 1)
    assert(leafCount(Node('a, Node('b), End)) == 1)
    assert(leafCount(Node('a, End, Node('b))) == 1)
    assert(leafCount(Node('a, Node('b), Node('c))) == 2)
    /*
     *              'a
     *              / \
     *            'b   'c
     *            /
     *          'b1
     *          /
     *        'b2
     */
    assert(leafCount(Node('a, Node('b, Node('b1, Node('b2), End), End), Node('c))) == 2)

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
    assert(leafCount(Node('a, Node('b, End, Node('b1)), Node('c, Node('c1), Node('c2, Node('c3), End)))) == 3)
  }
}
