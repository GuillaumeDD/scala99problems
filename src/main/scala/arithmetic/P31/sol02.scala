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

class sol02 extends P31 {
  def isPrime(n: Int): Boolean =
    sol02.isPrime(n)
}

object sol02 {
  def isPrime(n: Int): Boolean =
    {
      require(n >= 0)
      (n > 1) && ((2 to Math.sqrt(n).floor.toInt) forall (n % _ != 0))
    }
}
