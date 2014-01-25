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
package arithmetic.P35

import org.scalatest.Args
import scala.collection.immutable.List

class sol01 extends P35 {
  def primeFactors(n: Int): List[Int] =
    {
      require(n > 1)
      def primeFactorsHelper(a: Int, primes: Stream[Int]): List[Int] =
        a match {
          case 1 => List()
          case a =>
            if (a % primes.head == 0) {
              primes.head :: primeFactorsHelper(a / primes.head, primes)
            } else {
              primeFactorsHelper(a, primes.tail)
            }
        }
      // Usage of a stream of prime numbers
      import arithmetic.P31.sol03._
      primeFactorsHelper(n, primes)
    }
}
