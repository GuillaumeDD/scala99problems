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
package arithmetic.P39

import org.scalatest.Args
import scala.collection.immutable.Range

class sol02 extends P39 {
  // Solution through the stream of primes
  def listPrimesinRange(range: Range): List[Int] =
    arithmetic.Util.primes.dropWhile(_ < range.start).takeWhile(_ <= range.end).toList
}
