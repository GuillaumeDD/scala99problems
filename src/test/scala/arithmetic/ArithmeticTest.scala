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
package arithmetic

import org.scalatest.FunSuite
import arithmetic._
import org.scalatest.junit.JUnitRunner

class ArithmeticTest extends FunSuite {
  test("Invoking isPrime on a negative number should throw an IllegalArgumentException") {
    intercept[IllegalArgumentException] {
      (-1).isPrime
    }
  }

  test("Invoking isPrime on 0 and 1 should return false") {
    assert(!0.isPrime)
    assert(!1.isPrime)
  }

  test("Invoking isPrime on a prime number should return true") {
    assert(2.isPrime)
    assert(3.isPrime)
    assert(5.isPrime)
    assert(7.isPrime)
    assert(11.isPrime)
    assert(97.isPrime)
    assert(15487019.isPrime)
  }

  test("Invoking isPrime on a non-prime number should return false") {
    assert(!4.isPrime)
    assert(!6.isPrime)
    assert(!8.isPrime)
    assert(!9.isPrime)
    assert(!10.isPrime)
    assert(!99.isPrime)
    assert(!121.isPrime)
  }

  test("Invoking isCoprimeTo with a=0 AND b=0 should return an IllegalArgumentException") {
    intercept[IllegalArgumentException] {
      0.isCoprimeTo(0)
    }
  }

  test("Invoking isCoprimeTo with a=0 or b=0 should return false") {
    assert(!42.isCoprimeTo(0))
    assert(!0.isCoprimeTo(42))
  }

  test("Invoking isCoprime with a>0 and b>0 should return true if they are coprime, else false") {
    assert(42.isCoprimeTo(1))
    assert(!36.isCoprimeTo(63))
    assert(35.isCoprimeTo(64))
  }

  test("Invoking primeFactors on an integer inferior or equal to 1 should trigger an IllegalArgumentException") {
    intercept[IllegalArgumentException] {
      1.primeFactors
    }

    intercept[IllegalArgumentException] {
      0.primeFactors
    }

    intercept[IllegalArgumentException] {
      -1.primeFactors
    }
  }

  test("Invoking primeFactors on a strictly positive integer should return the prime factors in ascending order") {
    assert(2.primeFactors == List(2))
    assert(16.primeFactors == List(2, 2, 2, 2))
    assert(30.primeFactors == List(2, 3, 5))
    assert(315.primeFactors == List(3, 3, 5, 7))
  }

  test("Invoking primeFactorMultiplicity on an integer inferior or equal to 1 should trigger an IllegalArgumentException") {
    intercept[IllegalArgumentException] {
      1.primeFactorMultiplicity
    }

    intercept[IllegalArgumentException] {
      0.primeFactorMultiplicity
    }

    intercept[IllegalArgumentException] {
      -1.primeFactorMultiplicity
    }
  }

  test("Invoking primeFactorMultiplicity on a strictly positive integer should return the prime factors in ascending order") {
    assert(2.primeFactorMultiplicity == List((2, 1)))
    assert(16.primeFactorMultiplicity == List((2, 4)))
    assert(30.primeFactorMultiplicity == List((2, 1), (3, 1), (5, 1)))
    assert(315.primeFactorMultiplicity == List((3, 2), (5, 1), (7, 1)))
  }

  test("Invoking totient (improved) on a negative number should return an IllegalArgumentException") {
    intercept[IllegalArgumentException] {
      -42.totient
    }
  }

  test("Invoking totient (improved) on 0 should return 0") {
    assert(0.totient == 0)
  }

  test("Invoking totient (improved) on a positive number should return phi(m)") {
    assert(1.totient == 1)
    assert(2.totient == 1)
    assert(8.totient == 4)
    assert(10.totient == 4)
  }

  test("Invoking goldbach on an integer <= 2 should throw an IllegalArgumentException") {
    intercept[IllegalArgumentException] {
      2.goldbach
    }

    intercept[IllegalArgumentException] {
      1.goldbach
    }

    intercept[IllegalArgumentException] {
      0.goldbach
    }

    intercept[IllegalArgumentException] {
      -42.goldbach
    }
  }

  test("Invoking goldbach on an odd integer should throw an IllegalArgumentException") {
    intercept[IllegalArgumentException] {
      3.goldbach
    }

    intercept[IllegalArgumentException] {
      91.goldbach
    }
  }

  test("Invoking goldbach on an integer > 2 should return a decomposition") {
    assert(6.goldbach == (3, 3))
    assert(8.goldbach == (3, 5))
    assert(10.goldbach == (3, 7))
    assert(28.goldbach == (5, 23))
    assert(50.goldbach == (3, 47))
  }
}
