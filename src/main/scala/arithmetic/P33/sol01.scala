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
package arithmetic.P33

import org.scalatest.Args

class sol01 extends P33 {
  def isCoprimeTo(a: Int, b: Int): Boolean =
    sol01.isCoprimeTo(a, b)
}

object sol01 {
  def isCoprimeTo(a: Int, b: Int): Boolean =
    {
      import arithmetic.P32.sol01._
      gcd(a, b) == 1
    }
}
