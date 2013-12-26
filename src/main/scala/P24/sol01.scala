/*******************************************************************************
 * Copyright (c) 2013 Guillaume DUBUISSON DUPLESSIS <guillaume.dubuisson_duplessis@insa-rouen.fr>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     Guillaume DUBUISSON DUPLESSIS <guillaume.dubuisson_duplessis@insa-rouen.fr> - initial API and implementation
 ******************************************************************************/
package P24

object sol01 extends P24 {
  import P22.sol01._
  import P23.sol01._

  /**
   * Returns n different random numbers between 1 and max.
   * Due to randomSelect and range, the obtained list is always sorted with this
   * implementation.
   */
  def lotto(n: Int, max: Int): List[Int] =
    {
      require(n <= max + 1)
      randomSelect(n, range(1, max))
      // Shuffled version:
      // scala.util.Random.shuffle(randomSelect(n, range(1, max)))
    }
}
