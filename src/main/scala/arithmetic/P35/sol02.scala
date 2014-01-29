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
package arithmetic.P35

import org.scalatest.Args
import scala.collection.immutable.List
import scala.annotation.tailrec

class sol02 extends P35 {
  def primeFactors(n: Int): List[Int] =
    sol02.primeFactors(n)
}

object sol02 {
  def primeFactors(n: Int): List[Int] =
    {
      require(n > 1)
      @tailrec
      def primeFactorsHelper(a: Int, primes: Stream[Int], acc: List[Int]): List[Int] =
        a match {
          case 1 =>
            acc.reverse
          case a =>
            if (a % primes.head == 0) {
              primeFactorsHelper(a / primes.head, primes, primes.head :: acc)
            } else {
              primeFactorsHelper(a, primes.tail, acc)
            }
        }
      // Usage of a stream of prime numbers
      import arithmetic.P31.sol03._
      primeFactorsHelper(n, primes, List())
    }
}
