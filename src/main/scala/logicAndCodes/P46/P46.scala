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
package logicAndCodes.P46

import util.ExerciseTemplate

trait P46 extends ExerciseTemplate {
  /*
	P46 (**) Truth tables for logical expressions.
    Define functions and, or, nand, nor, xor, impl, and equ (for logical equivalence) which return true or false according to the result of their respective operations; e.g. and(A, B) is true if and only if both A and B are true.

    scala> and(true, true)
    res0: Boolean = true

    scala> xor(true. true)
    res1: Boolean = false

    A logical expression in two variables can then be written as an function of two variables, e.g: (a: Boolean, b: Boolean) => and(or(a, b), nand(a, b))

    Now, write a function called table2 which prints the truth table of a given logical expression in two variables.

    scala> table2((a: Boolean, b: Boolean) => and(a, or(a, b)))
    A     B     result
    true  true  true
    true  false true
    false true  false
    false false false
	 */
  val name = "P46 (Truth tables for logical expressions)"

  def not(a: Boolean): Boolean
  def and(a: Boolean, b: Boolean): Boolean
  def or(a: Boolean, b: Boolean): Boolean
  def nand(a: Boolean, b: Boolean): Boolean
  def nor(a: Boolean, b: Boolean): Boolean
  def xor(a: Boolean, b: Boolean): Boolean
  def impl(a: Boolean, b: Boolean): Boolean
  def equ(a: Boolean, b: Boolean): Boolean

  test("Invoking not should return the oppositive boolean") {
    assert(not(true) == false)
    assert(not(false) == true)
  }

  test("Invoking and should return the conjonction of boolean") {
    assert(and(true, true) == true)
    assert(and(true, false) == false)
    assert(and(false, true) == false)
    assert(and(false, false) == false)
  }

  test("Invoking or should return the disjonction of boolean") {
    assert(or(true, true) == true)
    assert(or(true, false) == true)
    assert(or(false, true) == true)
    assert(or(false, false) == false)
  }

  test("Invoking nand should return the opposite of conjonction of boolean") {
    assert(nand(true, true) == false)
    assert(nand(true, false) == true)
    assert(nand(false, true) == true)
    assert(nand(false, false) == true)
  }

  test("Invoking nor should return the oppositive of the disjonction of boolean") {
    assert(nor(true, true) == false)
    assert(nor(true, false) == false)
    assert(nor(false, true) == false)
    assert(nor(false, false) == true)
  }

  test("Invoking xor should return the exclusive disjonction of boolean") {
    assert(xor(true, true) == false)
    assert(xor(true, false) == true)
    assert(xor(false, true) == true)
    assert(xor(false, false) == false)
  }

  test("Invoking impl should return the implication") {
    assert(impl(true, true) == true)
    assert(impl(true, false) == false)
    assert(impl(false, true) == true)
    assert(impl(false, false) == true)
  }

  test("Invoking equ should return the equivalence") {
    assert(equ(true, true) == true)
    assert(equ(true, false) == false)
    assert(equ(false, true) == false)
    assert(equ(false, false) == true)
  }
}
