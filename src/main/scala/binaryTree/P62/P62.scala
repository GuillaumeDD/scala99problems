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
package binaryTree.P62

import util.ExerciseTemplate
import binaryTree.Tree
import binaryTree.End
import binaryTree.Node

trait P62 extends ExerciseTemplate {
  /*
	 P62 (*) Collect the internal nodes of a binary tree in a list.
    An internal node of a binary tree has either one or two non-empty successors. Write a method internalList to collect them in a list.

    scala> Node('a', Node('b'), Node('c', Node('d'), Node('e'))).internalList
    res0: List[Char] = List(a, c)
  */
  val name = "Collect the internal nodes of a binary tree in a list (P62)"
  def internalList[T](t: Tree[T]): List[T]

  test("Invoking internalList should collect the internal nodes of a binary tree in a list") {
    assert(internalList(End).isEmpty)

    assert(internalList(Node('a)) == List())

    assert(internalList(Node('a, Node('b), End)) == List('a))
    assert(internalList(Node('a, End, Node('b))) == List('a))

    // Example test
    val result01 = internalList(Node('a', Node('b'), Node('c', Node('d'), Node('e'))))
    assert(result01.size == 2)
    assert(List('a', 'c').forall(result01.contains(_)))
  }
}
