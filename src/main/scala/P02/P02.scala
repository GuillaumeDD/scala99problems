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
package P02

import util.ExerciseTemplate

trait P02 extends ExerciseTemplate {
  /*
	P02 (*) Find the last but one element of a list.
    Example:

    scala> penultimate(List(1, 1, 2, 3, 5, 8))
    res0: Int = 5
   */
  val name = "P02 (Find the last but one element of a list)"
  def penultimate[T](l: List[T]): T

  test("Invoking penultimate on non-empty list should return the penultimate element") {
    assert(penultimate(List(1, 1, 2, 3, 5, 8)) == 5)
    assert(penultimate(List(2, 1)) == 2)
  }

  test("Invoking penultimate on an empty list should produce NoSuchElementException") {
    intercept[NoSuchElementException] {
      penultimate(List())
    }
  }

  test("Invoking penultimate on 1-element list should produce NoSuchElementException") {
    intercept[NoSuchElementException] {
      penultimate(List('a))
    }
  }
}
