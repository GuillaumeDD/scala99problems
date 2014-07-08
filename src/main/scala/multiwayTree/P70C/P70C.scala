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
package multiwayTree.P70C

import util.ExerciseTemplate
import multiwayTree.MTree

trait P70C extends ExerciseTemplate {
  /*
    P70C (*) Count the nodes of a multiway tree.
    Write a method nodeCount which counts the nodes of a given multiway tree.

    scala> MTree('a', List(MTree('f'))).nodeCount
    res0: Int = 2
  */
  val name = "P70C (Count the nodes of a multiway tree)"
  def nodeCount[T](t: MTree[T]): Int

  test("Invoking nodeCount on a MTree should return the node count") {
    assert(nodeCount(MTree('a')) == 1)
    assert(nodeCount(MTree('a', List(MTree('f')))) == 2)
    assert(nodeCount(MTree('a', List(MTree('f', List(MTree('b'), MTree('c')))))) == 4)
  }
}
