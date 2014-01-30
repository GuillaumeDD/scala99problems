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
package arithmetic.P31

import org.scalatest.Args

class sol03 extends P31 {
  def isPrime(n: Int): Boolean =
    {
      require(n >= 0)
      (n > 1) &&
        arithmetic.Util.primes.takeWhile(prime => prime * prime <= n)
        .forall(prime => n % prime != 0)
    }

  test("Taking 25 prime numbers should return the 25 first prime numbers") {
    assert(arithmetic.Util.primes.take(25).toList ==
      List(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97))
  }
}
