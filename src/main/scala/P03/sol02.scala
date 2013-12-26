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
package P03

import scala.annotation.tailrec

object sol02 extends P03 {
  @tailrec
  def nth[A](n: Int, ls: List[A]): A = {
    require(n >= 0)
    (n, ls) match {
      case (0, h :: _) => h
      case (n, _ :: tail) => nth(n - 1, tail)
      case (_, Nil) => throw new NoSuchElementException
    }
  }
}
