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
package arithmetic.P32

import util.ExerciseTemplate

trait P32 extends ExerciseTemplate {
  /*
    P32 (**) Determine the greatest common divisor of two positive integer numbers.
    Use Euclid's algorithm.

    scala> gcd(36, 63)
    res0: Int = 9
   */
  def gcd(a: Int, b: Int): Int
  val name = "P32 (Determine the greatest common divisor of two positive integer numbers)"

  test("Invoking gcd with a=0 AND b=0 should return an IllegalArgumentException") {
    intercept[IllegalArgumentException] {
      gcd(0, 0)
    }
  }

  test("Invoking gcd with a=0 or b=0 should return max(a,b)") {
    assert(gcd(42, 0) == 42)
    assert(gcd(0, 42) == 42)
  }

  test("Invoking gcd with a>0 and b>0 should return the greatest common divisor") {
    assert(gcd(42, 1) == 1)
    assert(gcd(36, 63) == 9)
  }
}
