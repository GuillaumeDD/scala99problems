/**
 * *****************************************************************************
 * Copyright (c) 2013 Guillaume DUBUISSON DUPLESSIS <guillaume.dubuisson_duplessis@insa-rouen.fr>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 *
 * Contributors:
 *     Guillaume DUBUISSON DUPLESSIS <guillaume.dubuisson_duplessis@insa-rouen.fr> - initial API and implementation
 * ****************************************************************************
 */
package object util {
  /**
   * Returns a pseudorandom, uniformly distributed int value between 0 (inclusive)
   * and the specified value (exclusive)
   * @param borneSup Specified value (excluded)
   */
  def randomInt(borneSup: Int): Int =
    scala.util.Random.nextInt(borneSup)
}
