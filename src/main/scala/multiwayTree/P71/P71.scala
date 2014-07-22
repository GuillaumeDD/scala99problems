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
package multiwayTree.P71

import util.ExerciseTemplate
import multiwayTree.MTree

trait P71 extends ExerciseTemplate {
  /*
    P71 (*) Determine the internal path length of a tree.
    We define the internal path length of a multiway tree as the total sum of the path lengths 
    from the root to all nodes of the tree. By this definition, the tree in the figure of 
    problem P70 has an internal path length of 9. Write a method internalPathLength to return that sum.

    scala> "afg^^c^bd^e^^^".internalPathLength
    res0: Int = 9
   */
  val name = "P71 (Determine the internal path length of a tree)"
  def internalPathLength[T](t: MTree[T]): Int

  test("Invoking internalPathLength should compute the internal path length of a tree") {
    /*
     *       a
     */
    assert(internalPathLength(MTree('a)) == 0)

    /*
     *        a
     *      / | \
     *     b  c  d
     */
    assert(internalPathLength(MTree('a, List(MTree('b), MTree('c), MTree('d)))) == 3)

    /*
     *        a
     *      / | \
     *     f  c  b
     *     |    / \
     *     g   d   e
     *
     */
    assert(internalPathLength(MTree('a', List(MTree('f', List(MTree('g'))), MTree('c'), MTree('b', List(MTree('d'), MTree('e')))))) == 9)

    /*
     *        a
     *        |
     *        b
     *        |
     *        c
     *        |
     *        d
     */
    assert(internalPathLength(MTree('a', List(MTree('b', List(MTree('c', List(MTree('d')))))))) == 6)

  }
}
