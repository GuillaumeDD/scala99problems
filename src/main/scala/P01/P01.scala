/**
 * *****************************************************************************
 * Copyright (c) 2013 Guillaume DUBUISSON DUPLESSIS <guillaume.dubuisson_duplessis@insa-rouen.fr>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 *
 * Contributors:
 *     Guillaume DUBUISSON DUPLESSIS <guillaume.dubuisson_duplessis@insa-rouen.fr> - initial API and implementation
 * ****************************************************************************
 */
package P01

import org.scalatest.FunSuite
import util.ExerciseTemplate

trait P01 extends ExerciseTemplate {
  /*
	P01 (*) Find the last element of a list.
    Example:

    scala> last(List(1, 1, 2, 3, 5, 8))
    res0: Int = 8
  */
  val name = "P01 (Find the last element of a list)"
  def last[T](l: List[T]): T

  // Tests
  test("Invoking last on non-empty lists should return the last element") {
    assert(8 == last(List(1, 1, 2, 3, 5, 8)))
    assert(69 == last(List(69)))
  }

  test("Invoking last on an empty list should produce NoSuchElementException") {
    intercept[NoSuchElementException] {
      last(List())
    }
  }
}
