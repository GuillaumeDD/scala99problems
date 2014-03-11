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
package logicAndCodes.P49

import util.ExerciseTemplate

trait P49 extends ExerciseTemplate {
  /*
	P49 (**) Gray code.
    An n-bit Gray code is a sequence of n-bit strings constructed according to certain rules. For example,
    n = 1: C(1) = ("0", "1").
    n = 2: C(2) = ("00", "01", "11", "10").
    n = 3: C(3) = ("000", "001", "011", "010", "110", "111", "101", "100").

    Find out the construction rules and write a function to generate Gray codes.

    scala> gray(3)
    res0 List[String] = List(000, 001, 011, 010, 110, 111, 101, 100)

    See if you can use memoization to make the function more efficient.
 */
  val name = "P49 (Gray code)"
  def gray(l: Int): List[String]

  test("Invoking gray with l < 0 should through an IllegalArgumentException") {
    intercept[IllegalArgumentException] {
      gray(-42)
    }

    intercept[IllegalArgumentException] {
      gray(-1)
    }
  }

  test("Invoking gray with l >= 0 should return the sequence of n-bit strings") {
    assert(gray(0) == List())
    assert(gray(1) == List("0", "1"))
    assert(gray(2) == List("00", "01", "11", "10"))
    assert(gray(3) == List("000", "001", "011", "010", "110", "111", "101", "100"))
  }
}
