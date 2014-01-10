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
package list.P21

object sol01 extends P21 {
  def insertAt[T](elt: T, index: Int, l: List[T]): List[T] =
    (index, l) match {
      case (_, List()) => List(elt)
      case (0, l) => elt :: l
      case (n, head :: tail) =>
        head :: insertAt(elt, n - 1, tail)
    }
}
