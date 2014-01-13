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
package list.P03

import scala.annotation.tailrec

class sol01 extends P03 {
  def nth[T](index: Int, l: List[T]): T =
    sol01.nth(index, l)
}

object sol01 {
  @tailrec
  def nth[T](index: Int, l: List[T]): T = {
    require(index >= 0)
    l match {
      case List() =>
        throw new NoSuchElementException()
      case head :: tail =>
        if (index == 0) {
          head
        } else {
          nth(index - 1, tail)
        }
    }
  }
}