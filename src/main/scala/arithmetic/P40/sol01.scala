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

class sol01 extends P40 {
  def goldbach(n: Int): (Int, Int) =
    sol01.goldbach(n)
}

object sol01 {
  def goldbach(n: Int): (Int, Int) =
    {
      require(n > 2 && n % 2 == 0)
      val primes = listPrimesinRange(2 to n)
      val decompositions =
        for {
          a <- primes
          b <- primes
          if (a + b) == n
        } yield ((a, b))
      decompositions.head
    }
}
