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
package binaryTree.P57

import util.ExerciseTemplate
import binaryTree.Tree
import binaryTree.End
import binaryTree.Node

trait P57 extends ExerciseTemplate {
  /*
    P57 (**) Binary search trees (dictionaries).
    Write a function to add an element to a binary search tree.

    scala> End.addValue(2)
    res0: Node[Int] = T(2 . .)

    scala> res0.addValue(3)
    res1: Node[Int] = T(2 . T(3 . .))

    scala> res1.addValue(0)
    res2: Node[Int] = T(2 T(0 . .) T(3 . .))

    Hint: The abstract definition of addValue in Tree should be def addValue[U >: T <% Ordered[U]](x: U): Tree[U]. 
    The >: T is because addValue's parameters need to be contravariant in T. 
    (Conceptually, we're adding nodes above existing nodes. 
    In order for the subnodes to be of type T or any subtype, the upper nodes must be of type T or any supertype.) 
    The <% Ordered[U] allows us to use the < operator on the values in the tree.

    Use that function to construct a binary tree from a list of integers.

    scala> Tree.fromList(List(3, 2, 5, 7, 1))
    res3: Node[Int] = T(3 T(2 T(1 . .) .) T(5 . T(7 . .)))

    Finally, use that function to test your solution to P56.

    scala> Tree.fromList(List(5, 3, 18, 1, 4, 12, 21)).isSymmetric
    res4: Boolean = true

    scala> Tree.fromList(List(3, 2, 5, 7, 4)).isSymmetric
    res5: Boolean = false
 */
  val name = "P57 (Binary search trees (dictionaries))"
  //def addValue[U >: T <% Ordered[U]](x: U, Tree[T]): Tree[U]
  def addValue[T, U >: T <% Ordered[U]](x: U, t: Tree[T]): Tree[U]
  def fromList[U <% Ordered[U]](x: List[U]): Tree[U]

  test("Invoking addValue on an End should return a Node") {
    assert(addValue(2, End) == Node(2))
  }

  test("Invoking addValue on a Node should return a binary search tree") {
    val t1 = addValue(2, End)
    assert(addValue(3, t1) == Node(2, End, Node(3)))

    val t2 = addValue(3, t1)
    assert(addValue(0, t2) == Node(2, Node(0), Node(3)))

    val t3 = addValue(0, t2)
    assert(addValue(1, t3) == Node(2, Node(0, End, Node(1)), Node(3)))
  }

  test("Invoking fromList shoud return a binary search tree") {
    assert(Tree.fromList(List(3, 2, 5, 7, 1)) == Node(3, Node(2, Node(1), End), Node(5, End, Node(7))))
    assert(Tree.fromList(List(3, 2, 5, 7, 1)).isSymmetric)

    assert(Tree.fromList(List(3, 2, 5, 7, 4)) == Node(3, Node(2), Node(5, Node(4), Node(7))))
    assert(!Tree.fromList(List(3, 2, 5, 7, 4)).isSymmetric)
  }

}
