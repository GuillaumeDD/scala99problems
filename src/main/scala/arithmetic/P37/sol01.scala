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

import org.scalatest.Args
import arithmetic.P36.sol01.primeFactorMultiplicity

class sol01 extends P37 {
  def totient(a: Int): Int =
    {
      require(a >= 0)
      a match {
        case 0 => 0
        case 1 => 1
        case n =>
          primeFactorMultiplicity(n)
            .foldLeft(1)(
              (result, primeMultiplicity) => primeMultiplicity match {
                case (prime, mult) => result * (prime - 1) * Math.pow(prime, mult - 1).toInt
              })

      }

    }
}
