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
package list.P20

import scala.annotation.tailrec

class sol01 extends P20 {
  def removeAt[T](index: Int, l: List[T]): (List[T], T) =
    sol01.removeAt(index, l)
}

object sol01 {
  def removeAt[T](index: Int, l: List[T]): (List[T], T) =
    {
      require(index >= 0)
      @tailrec
      def removeAtHelper[T](n: Int, pre: List[T], ls: List[T]): (List[T], T) =
        (n, ls) match {
          case (_, List()) => throw new NoSuchElementException()
          case (0, head :: tail) =>
            (pre ++ tail, head)
          case (_, head :: tail) =>
            removeAtHelper(n - 1, pre :+ head, tail)
        }

      removeAtHelper(index, List(), l)
    }
}
