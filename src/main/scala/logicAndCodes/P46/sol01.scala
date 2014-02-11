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

import org.scalatest.Args

class sol01 extends P46 {
  def not(a: Boolean): Boolean =
    a match {
      case true => false
      case false => true
    }

  def and(a: Boolean, b: Boolean): Boolean =
    (a, b) match {
      case (true, true) => true
      case _ => false
    }

  def or(a: Boolean, b: Boolean): Boolean =
    not(and(not(a), not(b)))

  def nand(a: Boolean, b: Boolean): Boolean =
    not(and(a, b))

  def nor(a: Boolean, b: Boolean): Boolean =
    not(or(a, b))

  def xor(a: Boolean, b: Boolean): Boolean =
    not(equ(a, b))

  def impl(a: Boolean, b: Boolean): Boolean =
    or(not(a), b)

  def equ(a: Boolean, b: Boolean): Boolean =
    or(and(a, b), and(not(a), not(b)))
}

object Display extends App {
  def table2(f: (Boolean, Boolean) => Boolean) {
    println("A     B     result")
    for {
      a <- List(true, false)
      b <- List(true, false)
    } {
      printf("%-5s %-5s %-5s\n", a, b, f(a, b))
    }
  }

  table2((a: Boolean, b: Boolean) => (new sol01).and(a, (new sol01).or(a, b)))
}