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
package arithmetic.P35

import util.ExerciseTemplate

trait P35 extends ExerciseTemplate {
  /*
    P35 (**) Determine the prime factors of a given positive integer.
    Construct a flat list containing the prime factors in ascending order.

    scala> 315.primeFactors
    res0: List[Int] = List(3, 3, 5, 7)
 */
  val name = "P35 (Determine the prime factors of a given positive integer)"
  def primeFactors(n: Int): List[Int]

  test("Invoking primeFactors on an integer inferior or equal to 1 should trigger an IllegalArgumentException") {
    intercept[IllegalArgumentException] {
      primeFactors(1)
    }

    intercept[IllegalArgumentException] {
      primeFactors(0)
    }

    intercept[IllegalArgumentException] {
      primeFactors(-1)
    }
  }

  test("Invoking primeFactors on a strictly positive integer should return the prime factors in ascending order") {
    assert(primeFactors(2) == List(2))
    assert(primeFactors(16) == List(2, 2, 2, 2))
    assert(primeFactors(30) == List(2, 3, 5))
    assert(primeFactors(315) == List(3, 3, 5, 7))
  }
}
