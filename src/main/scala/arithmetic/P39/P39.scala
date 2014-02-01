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
package arithmetic.P39

import util.ExerciseTemplate

trait P39 extends ExerciseTemplate {
  /*
	P39 (*) A list of prime numbers.
    Given a range of integers by its lower and upper limit, construct a list of all prime numbers in that range.

    scala> listPrimesinRange(7 to 31)
    res0: List[Int] = List(7, 11, 13, 17, 19, 23, 29, 31)
   */
  val name = "P39 (A list of prime numbers)"
  def listPrimesinRange(range: Range): List[Int]

  test("Invoking listPrimesInRange should construct a list af all prime numbers in that range") {
    assert(listPrimesinRange(1 to 1) == List())
    assert(listPrimesinRange(7 to 31) == List(7, 11, 13, 17, 19, 23, 29, 31))
    assert(listPrimesinRange(0 to 10) == List(2, 3, 5, 7))
  }
}
