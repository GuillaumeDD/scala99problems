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
import scala.annotation.tailrec
package object arithmetic {

  implicit class Arithmetic(val n: Int) extends AnyVal {
    // P31-sol01
    def isPrime: Boolean = {
      require(n >= 0)
      n match {
        case 0 | 1 => false
        case n =>
          var i = 2
          var result = true
          while ((i * i <= n) && result) {
            if (n % i == 0) {
              result = false
            } else {
              i = i + 1
            }
          }
          result
      }
    }

    //P33-sol01
    def isCoprimeTo(b: Int): Boolean =
      {
        import arithmetic.P32.sol01._
        gcd(n, b) == 1
      }

    //P35-sol02
    def primeFactors(): List[Int] =
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
        primeFactorsHelper(n, arithmetic.Util.primes, List())
      }

    // P36-sol03
    def primeFactorMultiplicity(): List[(Int, Int)] =
      {
        require(n > 1)
        @tailrec
        def primeFactorsMultiplicityHelper(a: Int, primes: Stream[Int], acc: List[(Int, Int)]): List[(Int, Int)] =
          a match {
            case 1 => acc.reverse
            case a =>
              if (a % primes.head == 0) {
                // Case: a is divisible by the prime number
                // Merging with next result
                acc match {
                  case List() =>
                    primeFactorsMultiplicityHelper(a / primes.head, primes, List((primes.head, 1)))
                  case head :: tail =>
                    head match {
                      case (p, n) if p == primes.head =>
                        primeFactorsMultiplicityHelper(a / primes.head, primes, (p, n + 1) :: tail)
                      case _ =>
                        primeFactorsMultiplicityHelper(a / primes.head, primes, (primes.head, 1) :: acc)
                    }
                }
              } else {
                primeFactorsMultiplicityHelper(a, primes.tail, acc)
              }
          }
        // Usage of a stream of prime numbers
        primeFactorsMultiplicityHelper(n, arithmetic.Util.primes, List())
      }

    // P37-sol01
    def totient(): Int =
      {
        require(n >= 0)
        n match {
          case 0 => 0
          case 1 => 1
          case a =>
            Arithmetic(a).primeFactorMultiplicity
              .foldLeft(1)(
                (result, primeMultiplicity) => primeMultiplicity match {
                  case (prime, mult) => result * (prime - 1) * Math.pow(prime, mult - 1).toInt
                })

        }
      }

    // P40-sol02
    def goldbach(): (Int, Int) =
      {
        require(n > 2 && n % 2 == 0)
        Util.listPrimesinRange(2 to n).find(p => Arithmetic(n - p).isPrime) match {
          case Some(prime) =>
            (prime, n - prime)
          case _ =>
            throw new Error(s"Goldbach decomposition impossible for $n")
        }
      }
  }
}
