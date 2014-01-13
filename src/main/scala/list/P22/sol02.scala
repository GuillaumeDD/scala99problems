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
package list.P22

import scala.annotation.tailrec

class sol02 extends P22 {
  def range(min: Int, max: Int): List[Int] =
    sol02.range(min, max)
}

object sol02 {
  def range(min: Int, max: Int): List[Int] =
    {
      require(min <= max)
      @tailrec
      def rangeHelper(bInf: Int, acc: List[Int]): List[Int] =
        if (bInf == max) {
          acc :+ bInf
        } else {
          rangeHelper(bInf + 1, acc :+ bInf)
        }

      rangeHelper(min, List())
    }
}
