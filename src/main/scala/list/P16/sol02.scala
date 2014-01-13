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
package list.P16

import scala.annotation.tailrec

class sol02 extends P16 {
  def drop[T](freq: Int, l: List[T]): List[T] =
    {
      @tailrec
      def dropHelper[T](freqCompteur: Int, ls: List[T], acc: List[T]): List[T] =
        (freqCompteur, ls) match {
          case (_, List()) => acc
          case (1, _ :: tail) =>
            dropHelper(freq, tail, acc)
          case (_, head :: tail) =>
            dropHelper(freqCompteur - 1, tail, acc :+ head)
        }

      dropHelper(freq, l, List())
    }
}
