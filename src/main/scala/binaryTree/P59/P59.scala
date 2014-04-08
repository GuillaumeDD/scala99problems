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
package binaryTree.P59

import util.ExerciseTemplate
import binaryTree.Tree
import binaryTree.End
import binaryTree.Node

trait P59 extends ExerciseTemplate {
  /*
	 P59 (**) Construct height-balanced binary trees.
    In a height-balanced binary tree, the following property holds for every node: 
    The height of its left subtree and the height of its right subtree are almost equal, 
    which means their difference is not greater than one.

    Write a method Tree.hbalTrees to construct height-balanced binary trees for a given height with a supplied value for the nodes. 
    The function should generate all solutions.

    scala> Tree.hbalTrees(3, "x")
    res0: List[Node[String]] = List(T(x T(x T(x . .) T(x . .)) T(x T(x . .) T(x . .))), T(x T(x T(x . .) T(x . .)) T(x T(x . .) .)), ...
	 */
  val name = "P59 (Construct height-balanced binary trees)"
  def hbalTrees[T](n: Int, elem: T): List[Tree[T]]

  test("Invoking hbalTrees on 0 should return an End") {
    assert(hbalTrees(0, 'a) == List(End))
  }

  test("Invoking hbalTrees on 1 should return a single Node") {
    assert(hbalTrees(1, 'a) == List(Node('a)))
  }

  test("Invoking hbalTrees on 2 should return solutions") {
    val solutions =
      List(
        /*
         *          'a
         *          / \
         *        'a  'a
         */
        Node('a, Node('a), Node('a)),
        /*
         *          'a
         *          / 
         *        'a  
         */
        Node('a, Node('a), End),
        /*
         *          'a
         *            \
         *            'a
         */
        Node('a, End, Node('a)))
    assert(hbalTrees(2, 'a).size == solutions.size)
    assert(hbalTrees(2, 'a).forall(solutions.contains(_)))
  }

  test("Invoking hbalTrees on 3 should return solutions") {
    val solutions =
      List(
        /*
         *          'a
         *         /   \
         *       'a    'a
         *       / \   / \
         *     'a  'a'a   'a
         */
        Node('a, Node('a, Node('a), Node('a)), Node('a, Node('a), Node('a))),
        /*
         *          'a
         *         /   \
         *       'a    'a
         *       /     / \
         *     'a    'a   'a
         */
        Node('a, Node('a, Node('a), End), Node('a, Node('a), Node('a))),
        /*
         *          'a
         *         /   \
         *       'a    'a
         *         \   / \
         *         'a'a   'a
         */
        Node('a, Node('a, End, Node('a)), Node('a, Node('a), Node('a))),
        /*
         *          'a
         *         /   \
         *       'a    'a
         *       / \   / 
         *     'a  'a'a
         */
        Node('a, Node('a, Node('a), Node('a)), Node('a, Node('a), End)),
        /*
         *          'a
         *         /   \
         *       'a    'a
         *       / \     \
         *     'a  'a     'a
         */
        Node('a, Node('a, Node('a), Node('a)), Node('a, End, Node('a))),
        /*
         *          'a
         *         /   \
         *       'a    'a
         *       /     /
         *     'a     'a
         */
        Node('a, Node('a, Node('a), End), Node('a, Node('a), End)),
        /*
         *          'a
         *         /   \
         *       'a    'a
         *       /       \
         *     'a        'a
         */
        Node('a, Node('a, Node('a), End), Node('a, End, Node('a))),
        /*
         *          'a
         *         /   \
         *       'a    'a
         *         \   /
         *          'a'a
         */
        Node('a, Node('a, End, Node('a)), Node('a, Node('a), End)),
        /*
         *          'a
         *         /   \
         *       'a    'a
         *         \     \
         *          'a   'a
         */
        Node('a, Node('a, End, Node('a)), Node('a, End, Node('a))),
        /*
         *          'a
         *         /   \
         *       'a    'a
         *       / \    
         *     'a  'a   
         */
        Node('a, Node('a, Node('a), Node('a)), Node('a, End, End)),
        /*
         *          'a
         *         /   \
         *       'a    'a
         *       /     
         *     'a     
         */
        Node('a, Node('a, Node('a), End), Node('a, End, End)),
        /*
         *          'a
         *         /   \
         *       'a    'a
         *         \     
         *         'a 
         */
        Node('a, Node('a, End, Node('a)), Node('a, End, End)),
        /*
         *          'a
         *         /   \
         *       'a    'a
         *             / \
         *           'a   'a
         */
        Node('a, Node('a, End, End), Node('a, Node('a), Node('a))),
        /*
         *          'a
         *         /   \
         *       'a    'a
         *             / 
         *           'a   
         */
        Node('a, Node('a, End, End), Node('a, Node('a), End)),
        /*
         *          'a
         *         /   \
         *       'a    'a
         *               \ 
         *               'a   
         */
        Node('a, Node('a, End, End), Node('a, End, Node('a))))
    assert(hbalTrees(3, 'a).size == solutions.size)
    assert(hbalTrees(3, 'a).forall(solutions.contains(_)))
  }
}
