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
package arithmetic.P31

import util.ExerciseTemplate

trait P31 extends ExerciseTemplate {
  /*
    P31 (**) Determine whether a given integer number is prime.

    scala> 7.isPrime
    res0: Boolean = true
 */
  val name = "P31 (Determine whether a given integer number is prime)"
  def isPrime(n: Int): Boolean

  test("Invoking isPrime on a negative number should throw an IllegalArgumentException") {
    intercept[IllegalArgumentException] {
      isPrime(-1)
    }
  }

  test("Invoking isPrime on 0 and 1 should return false") {
    assert(!isPrime(0))
    assert(!isPrime(1))
  }

  test("Invoking isPrime on a prime number should return true") {
    assert(isPrime(2))
    assert(isPrime(3))
    assert(isPrime(5))
    assert(isPrime(7))
    assert(isPrime(11))
    assert(isPrime(97))
  }

  test("Invoking isPrime on a non-prime number should return false") {
    assert(!isPrime(4))
    assert(!isPrime(6))
    assert(!isPrime(8))
    assert(!isPrime(9))
    assert(!isPrime(10))
    assert(!isPrime(99))
    assert(!isPrime(121))
  }
}
