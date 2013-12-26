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
package P18

object sol02 extends P18 {
  def slice[T](borneInf: Int, borneSup: Int, l: List[T]): List[T] =
    {
      require(borneInf <= borneSup)
      def sliceHelper[T](count: Int, lH: List[T], acc: List[T]): List[T] =
        (count, lH) match {
          case (_, List()) =>
            acc
          case (n, ls) if n >= borneSup =>
            acc
          case (n, head :: tail) if n >= borneInf =>
            sliceHelper(n + 1, tail, acc :+ head)
          case (n, _ :: tail) =>
            sliceHelper(n + 1, tail, acc)
        }
      sliceHelper(0, l, List())
    }
}
