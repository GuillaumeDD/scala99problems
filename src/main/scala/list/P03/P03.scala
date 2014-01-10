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
package list.P03

import util.ExerciseTemplate

trait P03 extends ExerciseTemplate {
  /*
	P03 (*) Find the Kth element of a list.
    By convention, the first element in the list is element 0.

    Example:

    scala> nth(2, List(1, 1, 2, 3, 5, 8))
    res0: Int = 2
   */
  val name = "P03 (Find the Kth element of a list)"
  def nth[A](n: Int, ls: List[A]): A

  test("Invoking nth on a non-empty list should return the nth element") {
    assert(nth(2, List(1, 1, 2, 3, 5, 8)) == 2)
    assert(nth(5, List(1, 1, 2, 3, 5, 8)) == 8)
  }

  test("Invoking nth on an empty list should produce NoSuchElementException") {
    intercept[NoSuchElementException] {
      nth(0, List())
    }
  }

  test("Invoking nth on a k-size list (k <= n) should produce NoSuchElementException") {
    intercept[NoSuchElementException] {
      nth(42, List(1, 2, 3, 4, 4))
    }
  }
}
