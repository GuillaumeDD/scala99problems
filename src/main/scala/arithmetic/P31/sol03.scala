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
}
