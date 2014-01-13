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
package list.P17

class sol01 extends P17 {
  def split[T](index: Int, l: List[T]): (List[T], List[T]) =
    {
      def splitHelper(curIndex: Int, curL: List[T]): (List[T], List[T]) =
        (curIndex, curL) match {
          case (_, List()) => (List(), List())
          case (1, head :: tail) =>
            (List(head), tail)
          case (_, head :: tail) =>
            val (pre, post) = splitHelper(curIndex - 1, tail)
            (head :: pre, post)
        }

      splitHelper(index, l)
    }
}
