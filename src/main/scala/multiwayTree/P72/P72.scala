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
package multiwayTree.P72

import util.ExerciseTemplate
import multiwayTree.MTree

trait P72 extends ExerciseTemplate {
  /*
    P72 (*) Construct the postorder sequence of the tree nodes.
    Write a method postorder which constructs the postorder sequence of the nodes of a multiway tree. The result should be a List.

    scala> "afg^^c^bd^e^^^".postorder
    res0: List[Char] = List(g, f, c, d, e, b, a)
   */
  val name = "P72 (Construct the postorder sequence of the tree nodes)"
  def postorder[T](t: MTree[T]): List[T]

  test("Invoking postorder should return the post-order list of values of a Tree") {
    /*
     *       a
     */
    assert(postorder(MTree('a)) == List('a))

    /*
     *        a
     *      / | \
     *     b  c  d
     */
    assert(postorder(MTree('a, List(MTree('b), MTree('c), MTree('d)))) == List('b, 'c, 'd, 'a))

    /*
     *        a
     *      / | \
     *     f  c  b
     *     |    / \
     *     g   d   e
     *
     */
    assert(postorder(MTree('a, List(MTree('f, List(MTree('g))), MTree('c), MTree('b, List(MTree('d), MTree('e)))))) ==
      List('g, 'f, 'c, 'd, 'e, 'b, 'a))

    /*
     *        a
     *        |
     *        b
     *        |
     *        c
     *        |
     *        d
     */
    assert(postorder(MTree('a, List(MTree('b, List(MTree('c, List(MTree('d)))))))) == List('d, 'c, 'b, 'a))
  }
}
