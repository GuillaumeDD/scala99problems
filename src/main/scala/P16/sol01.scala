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
package P16

object sol01 extends P16 {
  def drop[T](freq: Int, l: List[T]): List[T] =
    {
      def dropHelper[T](freqCompteur: Int, ls: List[T]): List[T] =
        (freqCompteur, ls) match {
          case (_, List()) => List()
          case (1, _ :: tail) =>
            dropHelper(freq, tail)
          case (_, head :: tail) =>
            head :: dropHelper(freqCompteur - 1, tail)
        }

      dropHelper(freq, l)
    }
}
