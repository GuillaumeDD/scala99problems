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
package arithmetic.P32

import org.scalatest.Args

class sol01 extends P32 {

  def gcd(a: Int, b: Int): Int =
    {
      require(a > 0 || b > 0)
      if (a < b) {
        gcd(b, a)
      } else {
        if (b == 0) {
          a
        } else {
          gcd(b, a % b)
        }
      }
    }
}
