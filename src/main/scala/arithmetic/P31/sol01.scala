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
package arithmetic.P31

import org.scalatest.Args

class sol01 extends P31 {
  // Imperative style solution
  def isPrime(n: Int): Boolean =
    {
      require(n >= 0)
      n match {
        case 0 | 1 => false
        case n =>
          var i = 2
          var result = true
          while ((i * i  <= n) && result) {
            if (n % i == 0) {
              result = false
            } else {
              i = i + 1
            }
          }
          result
      }
    }
}
