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
package arithmetic.P36

import org.scalatest.Args
import util.ExerciseTemplate

trait P36 extends ExerciseTemplate {
  /*
	P36 (**) Determine the prime factors of a given positive integer (2).
    Construct a list containing the prime factors and their multiplicity.

    scala> 315.primeFactorMultiplicity
    res0: List[(Int, Int)] = List((3,2), (5,1), (7,1))
  */
  val name = "P36 (Determine the prime factors of a given positive integer (2))"
  def primeFactorMultiplicity(n: Int): List[(Int, Int)]

  test("Invoking primeFactorMultiplicity on an integer inferior or equal to 1 should trigger an IllegalArgumentException") {
    intercept[IllegalArgumentException] {
      primeFactorMultiplicity(1)
    }

    intercept[IllegalArgumentException] {
      primeFactorMultiplicity(0)
    }

    intercept[IllegalArgumentException] {
      primeFactorMultiplicity(-1)
    }
  }

  test("Invoking primeFactorMultiplicity on a strictly positive integer should return the prime factors in ascending order") {
    assert(primeFactorMultiplicity(2) == List((2, 1)))
    assert(primeFactorMultiplicity(16) == List((2, 4)))
    assert(primeFactorMultiplicity(30) == List((2, 1), (3, 1), (5, 1)))
    assert(primeFactorMultiplicity(315) == List((3, 2), (5, 1), (7, 1)))
  }
}
