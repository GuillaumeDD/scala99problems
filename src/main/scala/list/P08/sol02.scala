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
package list.P08

import scala.annotation.tailrec

class sol02 extends P08 {
  def compress[T](l: List[T]): List[T] =
    {
      @tailrec
      def compressHelper[T](l: List[T], elt: T, result: List[T]): List[T] = {
        l match {
          case List() =>
            result.reverse
          case head :: tail =>
            if (head == elt) {
              compressHelper(tail, elt, result)
            } else {
              compressHelper(tail, head, head :: result)
            }
        }
      }

      l match {
        case List() =>
          l
        case head :: tail =>
          compressHelper(tail, head, head +: List())
      }
    }
}
