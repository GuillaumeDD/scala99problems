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
package list.P19

class sol01 extends P19 {
  def rotate[T](n: Int, l: List[T]): List[T] =
    (n, l) match {
      case (0, _) => l
      case (_, List()) => List()
      case (n, head :: tail) if n >= 0 =>
        rotate(n - 1, tail :+ head)
      case (n, ls) if n < 0 =>
        rotate(n + 1, ls.last :: ls.init)
    }
}
