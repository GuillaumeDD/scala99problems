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
package arithmetic

object Util {
  /**
   *
   * @return A stream of integer from n
   */
  def from(n: Int): Stream[Int] =
    n #:: from(n + 1)

  /**
   * Implements a modified, less efficient version of the sieve of Eratosthenes.
   * (See "The Genuine Sieve of Eratosthenes" by Melissa E. O'Neill)
   * @return The prime numbers from n (n should be prime)
   */
  private def sieve(s: Stream[Int]): Stream[Int] =
    s.head #:: sieve(s.tail.filter(_ % s.head != 0))

  /**
   * Prime numbers as a stream
   */
  val primes: Stream[Int] =
    sieve(from(2))

  // Solution through the stream of primes
  def listPrimesinRange(range: Range): List[Int] =
    primes.dropWhile(_ < range.start).takeWhile(_ <= range.end).toList
}
