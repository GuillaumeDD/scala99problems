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
package object logicAndCodes {
  def not(b: Boolean): Boolean =
    b match {
      case true => false
      case false => true
    }

  implicit class Logic(a: Boolean) {
    def and(b: Boolean): Boolean =
      (a, b) match {
        case (true, true) => true
        case _ => false
      }

    def or(b: Boolean): Boolean =
      not(not(a) and not(b))

    def nand(b: Boolean): Boolean =
      not(a and b)

    def nor(b: Boolean): Boolean =
      not(a or b)

    def xor(b: Boolean): Boolean =
      not(a equ b)

    def impl(b: Boolean): Boolean =
      not(a) or b

    def equ(b: Boolean): Boolean =
      (a and b) or or(not(a) and not(b))
  }
}
