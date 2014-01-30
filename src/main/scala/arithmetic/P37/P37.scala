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
package arithmetic.P37

import util.ExerciseTemplate

trait P37 extends ExerciseTemplate {
  /*
	P37 (**) Calculate Euler's totient function phi(m) (improved).
    See problem P34 for the definition of Euler's totient function. If the list of the prime factors of a number m is known in the form of problem P36 then the function phi(m>) can be efficiently calculated as follows: Let [[p1, m1], [p2, m2], [p3, m3], ...] be the list of prime factors (and their multiplicities) of a given number m. Then phi(m) can be calculated with the following formula:

    phi(m) = (p1-1)*p1(m1-1) * (p2-1)*p2(m2-1) * (p3-1)*p3(m3-1) * ...

    Note that ab stands for the bth power of a.
  */
  val name = "P37 (Calculate Euler's totient function phi(m) (improved))"
  def totient(a: Int): Int

  test("Invoking totient (improved) on a negative number should return an IllegalArgumentException") {
    intercept[IllegalArgumentException] {
      totient(-42)
    }
  }

  test("Invoking totient (improved) on 0 should return 0") {
    assert(totient(0) == 0)
  }

  test("Invoking totient (improved) on a positive number should return phi(m)") {
    assert(totient(1) == 1)
    assert(totient(2) == 1)
    assert(totient(8) == 4)
    assert(totient(10) == 4)
  }
}
