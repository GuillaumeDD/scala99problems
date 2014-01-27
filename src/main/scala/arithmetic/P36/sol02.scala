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
package arithmetic.P36

import org.scalatest.Args
import scala.collection.immutable.List

class sol02 extends P36 {
  def primeFactorMultiplicity(n: Int): List[(Int, Int)] =
    {
      require(n > 1)
      def primeFactorsMultiplicityHelper(a: Int, primes: Stream[Int]): List[(Int, Int)] =
        a match {
          case 1 => List()
          case a =>
            if (a % primes.head == 0) {
              // Case: a is divisible by the prime number
              val nextPrimeFactors = primeFactorsMultiplicityHelper(a / primes.head, primes)
              // Merging with next result
              nextPrimeFactors match {
                case List() =>
                  (primes.head, 1) :: nextPrimeFactors
                case head :: tail =>
                  head match {
                    case (p, n) if p == primes.head =>
                      (p, n + 1) :: tail
                    case _ =>
                      (primes.head, 1) :: head :: tail
                  }
              }
            } else {
              primeFactorsMultiplicityHelper(a, primes.tail)
            }
        }
      // Usage of a stream of prime numbers
      import arithmetic.P31.sol03._
      primeFactorsMultiplicityHelper(n, primes)
    }
}
