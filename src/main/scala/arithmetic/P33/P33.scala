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
package arithmetic.P33

import util.ExerciseTemplate

trait P33 extends ExerciseTemplate {
  /*
    P33 (*) Determine whether two positive integer numbers are coprime.
    Two numbers are coprime if their greatest common divisor equals 1.

    scala> 35.isCoprimeTo(64)
    res0: Boolean = true
 */
  val name = "P33 (Determine whether two positive integer numbers are coprime)"
  def isCoprimeTo(a: Int, b: Int): Boolean

  test("Invoking isCoprimeTo with a=0 AND b=0 should return an IllegalArgumentException") {
    intercept[IllegalArgumentException] {
      isCoprimeTo(0, 0)
    }
  }

  test("Invoking isCoprimeTo with a=0 or b=0 should return false") {
    assert(!isCoprimeTo(42, 0))
    assert(!isCoprimeTo(0, 42))
  }

  test("Invoking isCoprime with a>0 and b>0 should return true if they are coprime, else false") {
    assert(isCoprimeTo(42, 1))
    assert(!isCoprimeTo(36, 63))
    assert(isCoprimeTo(35, 64))
  }
}
