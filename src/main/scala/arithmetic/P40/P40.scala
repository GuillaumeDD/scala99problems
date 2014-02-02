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
package arithmetic.P40

import util.ExerciseTemplate

trait P40 extends ExerciseTemplate {
  /*
	P40 (**) Goldbach's conjecture.
    Goldbach's conjecture says that every positive even number greater than 2 is the sum of two prime numbers. E.g. 28 = 5 + 23. It is one of the most famous facts in number theory that has not been proved to be correct in the general case. It has been numerically confirmed up to very large numbers (much larger than Scala's Int can represent). Write a function to find the two prime numbers that sum up to a given even integer.

    scala> 28.goldbach
    res0: (Int, Int) = (5,23)
  */
  val name = "P40 (Goldbach's conjecture)"
  def goldbach(n: Int): (Int, Int)

  test("Invoking goldbach on an integer <= 2 should throw an IllegalArgumentException") {
    intercept[IllegalArgumentException] {
      goldbach(2)
    }

    intercept[IllegalArgumentException] {
      goldbach(1)
    }

    intercept[IllegalArgumentException] {
      goldbach(0)
    }

    intercept[IllegalArgumentException] {
      goldbach(-42)
    }
  }

  test("Invoking goldbach on an odd integer should throw an IllegalArgumentException") {
    intercept[IllegalArgumentException] {
      goldbach(3)
    }

    intercept[IllegalArgumentException] {
      goldbach(91)
    }
  }

  test("Invoking goldbach on an integer > 2 should return a decomposition") {
    assert(goldbach(6) == (3, 3))
    assert(goldbach(8) == (3, 5))
    assert(goldbach(10) == (3, 7))
    assert(goldbach(28) == (5, 23))
    assert(goldbach(50) == (3, 47))
  }
}
