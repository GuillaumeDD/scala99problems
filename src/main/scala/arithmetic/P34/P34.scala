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
package arithmetic.P34

import util.ExerciseTemplate

trait P34 extends ExerciseTemplate {
  /*
	 P34 (**) Calculate Euler's totient function phi(m).
     Euler's so-called totient function phi(m) is defined as the number of positive integers r (1 <= r <= m) that are coprime to m.

     scala> 10.totient
     res0: Int = 4
   */
  val name = "P34 (Calculate Euler's totient function phi(m))"
  def totient(a: Int): Int

  test("Invoking totient on a negative number should return an IllegalArgumentException") {
    intercept[IllegalArgumentException] {
      totient(-42)
    }
  }

  test("Invoking totient on 0 should return 0") {
    assert(totient(0) == 0)
  }

  test("Invoking totient on a positive number should return phi(m)") {
    assert(totient(1) == 1)
    assert(totient(2) == 1)
    assert(totient(8) == 4)
    assert(totient(10) == 4)
  }
}
