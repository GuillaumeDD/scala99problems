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
package list.P22

import util.ExerciseTemplate

trait P22 extends ExerciseTemplate {
  /*
	P22 (*) Create a list containing all integers within a given range.
    Example:

    scala> range(4, 9)
    res0: List[Int] = List(4, 5, 6, 7, 8, 9)
	*/
  val name = "P22 (Create a list containing all integers within a given range)"
  def range(min: Int, max: Int): List[Int]

  test("Invoking range with min=max gives a list with one element") {
    assert(range(42, 42) == List(42))
  }

  test("Invoking range with min > max gives an IllegalArgumentException") {
    intercept[IllegalArgumentException] {
      range(5, 0)
    }
  }

  test("Invoking range should produce an ordered list of element between min and max") {
    assert(range(4, 9) == List(4, 5, 6, 7, 8, 9))
  }

}
