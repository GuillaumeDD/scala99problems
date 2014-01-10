/*******************************************************************************
 * Copyright (c) 2013 Guillaume DUBUISSON DUPLESSIS <guillaume.dubuisson_duplessis@insa-rouen.fr>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     Guillaume DUBUISSON DUPLESSIS <guillaume.dubuisson_duplessis@insa-rouen.fr> - initial API and implementation
 ******************************************************************************/
package list.P24

import util.ExerciseTemplate

trait P24 extends ExerciseTemplate {
  /*
  P24 (*) Lotto: Draw N different random numbers from the set 1..M.
    Example:

    scala> lotto(6, 49)
    res0: List[Int] = List(23, 1, 17, 33, 21, 37)
  */
  val name = "P24 (Lotto: Draw N different random numbers from the set 1..M)"
  def lotto(n: Int, max: Int): List[Int]

  test("Invoking lotto should return a n-element list which element are between 1 and M") {
    val l = lotto(6, 49)
    assert(l.size == 6)
    assert(l.forall(elt => elt >= 1 && elt <= 49))
  }

  test("Invoking lotto on a k-element list (k < n) should produce an IllegalArgumentException") {
    intercept[IllegalArgumentException] {
      lotto(200, 5)
    }
  }
}
