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
package binaryTree.P56

import util.ExerciseTemplate
import binaryTree.Tree
import binaryTree.End
import binaryTree.Node

trait P56 extends ExerciseTemplate {
  /*
	P56 (**) Symmetric binary trees.
    Let us call a binary tree symmetric if you can draw a vertical line through the root node and then the right subtree is the mirror image of the left subtree. 
    Add an isSymmetric method to the Tree class to check whether a given binary tree is symmetric. 
    Hint: Write an isMirrorOf method first to check whether one tree is the mirror image of another. 
    We are only interested in the structure, not in the contents of the nodes.

    scala> Node('a', Node('b'), Node('c')).isSymmetric
    res0: Boolean = true
   */
  val name = "P56 (Symmetric binary trees)"
  def isSymmetric[T](tree: Tree[T]): Boolean

  test("Invoking isSymmetric on an End should return true") {
    assert(isSymmetric(End))
  }

  test("Invoking isSymmetric on a Tree should return true if it is symmetric, else false") {
    val symmetricTree = Node("whatever", End, End)
    assert(isSymmetric(symmetricTree))

    /*
     * 		a
     *     / \
     *    b   c
     */
    val symmetricTree2 = Node("a", Node("b"), Node("c"))
    assert(isSymmetric(symmetricTree2))
    /*
     * 		a
     *     / \
     *    b   c
     *   /     \
     *  d       e
     */
    val symmetricTree3 = Node("a", Node("b", Node("d"), End), Node("c", End, Node("e")))
    assert(isSymmetric(symmetricTree3))
    /*
     * 		a
     *    /   \
     *   b     c
     *    \   /
     *     d e
     */
    val symmetricTree4 = Node("a", Node("b", End, Node("d")), Node("c", Node("e"), End))
    assert(isSymmetric(symmetricTree4))

    /*
     *      a
     *     / 
     *    b
     */
    val asymmetricTree1 = Node("a", Node("b"), End)
    assert(!isSymmetric(asymmetricTree1))
    /*
     *      a
     *     / \
     *    b   e
     *   / \
     *  c   d
     */
    val asymmetricTree2 = Node("a", Node("b", Node("c"), Node("d")), Node("e"))
    assert(!isSymmetric(asymmetricTree1))
  }
}
