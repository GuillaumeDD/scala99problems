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
package list.P01

import scala.annotation.tailrec

class sol01 extends P01 {
  def last[T](l: List[T]): T =
    sol01.last(l)
}

object sol01 {
  @tailrec
  def last[T](l: List[T]): T = {
    l match {
      case List() =>
        throw new NoSuchElementException()
      case a :: Nil =>
        a
      case head :: tail =>
        last(tail)
    }
  }
}
