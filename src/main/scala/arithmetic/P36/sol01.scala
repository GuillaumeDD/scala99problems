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
package arithmetic.P36

import org.scalatest.Args
import scala.collection.immutable.List
import arithmetic.P35.sol02.primeFactors
import list.P10.sol01.encode

class sol01 extends P36 {
  // Solution using result of P35 (Determine the prime factors of a given positive integer)
  // and P10 (Run-length encoding of a list)
  def primeFactorMultiplicity(n: Int): List[(Int, Int)] =
    sol01.primeFactorMultiplicity(n)
}

object sol01 {
  // Solution using result of P35 (Determine the prime factors of a given positive integer)
  // and P10 (Run-length encoding of a list)
  def primeFactorMultiplicity(n: Int): List[(Int, Int)] =
    {
      require(n > 1)
      encode(primeFactors(n)).map(
        // Inversion of the result of the encode function (freq, prime) -> (prime, freq)
        _.swap)
    }
}
