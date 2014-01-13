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
package list.P18

class sol01 extends P18 {
  def slice[T](borneInf: Int, borneSup: Int, l: List[T]): List[T] =
    {
      require(borneInf <= borneSup)
      (borneInf, borneSup, l) match {
        case (_, _, List()) => List()
        case (0, 0, l) => List()
        case (0, sup, head :: tail) =>
          head +: slice(0, sup - 1, tail)
        case (inf, sup, head :: tail) =>
          slice(inf - 1, sup - 1, tail)
      }
    }
}
