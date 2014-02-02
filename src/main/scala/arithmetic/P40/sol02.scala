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
package arithmetic.P40

import org.scalatest.Args
import arithmetic.P39.sol01.listPrimesinRange
import arithmetic.P31.sol02.isPrime

class sol02 extends P40 {
  def goldbach(n: Int): (Int, Int) =
    {
      require(n > 2 && n % 2 == 0)
      listPrimesinRange(2 to n).find(p => isPrime(n - p)) match {
        case Some(prime) =>
          (prime, n - prime)
        case _ =>
          throw new Error(s"Goldbach decomposition impossible for $n")
      }
    }
}
