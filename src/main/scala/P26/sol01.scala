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
package P26

object sol01 extends P26 {
  def combinations[T](size: Int, l: List[T]): List[List[T]] = {
    (size, l) match {
      case (0, _) =>
        // There is only one way to take 0 element from a list
        // It is to take the empty list.
        List(List())
      case (_, List()) => 
        // There is no way to take element from an empty list
        // (except by 0, dealt by the above case)
        List()
      case (s, head :: tail) =>
        combinations(s - 1, tail).map(ll => head :: ll) ++
          combinations(s, tail)
    }
  }
}
