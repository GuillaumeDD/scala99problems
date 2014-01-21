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
package arithmetic.P31

import org.scalatest.Args
import util.ExerciseTemplate

class sol04 extends ExerciseTemplate with P31 {

  def isPrime(n: Int): Boolean = {
    require(n >= 0)
    n match {
      case 0 | 1 =>
        false
      case n =>
        sieve(n)
    }
  }

  /**
   *
   * Imperative implementation of Sieve of Eratosthenes
   */
  private def sieve(n: Int): Boolean = {
    require(n > 1)
    // Return the index of the primality value in array primes
    // e.g., primality of 2 is stored at indexTranslation(2) (i.e., 0)
    def indexTranslation(index: Int): Int = {
      index - 2
    }

    // Initialisation of an Array to store primality from 2 to n
    val primes = new Array[Boolean](n - 1)
    // Initialisation: everything is prime
    for (i <- 0 to (primes.length - 1)) {
      primes(i) = true
    }

    var i = 2
    while (i * i <= n) {
      if (primes(indexTranslation(i))) {
        // Start crossing out number at 'i * i'
        var j = i * i
        while (j <= n) {
          primes(indexTranslation(j)) = false
          // Go through multiples of i
          j = j + i
        }
      }

      i = i + 1
    }

    primes(indexTranslation(n))
  }
}
