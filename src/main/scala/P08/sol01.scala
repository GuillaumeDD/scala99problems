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
package P08

object sol01 extends P08 {
  def compress[T](l: List[T]): List[T] =
    {
      // Helper function
      def compressHelper[T](l: List[T], elt: T): List[T] = {
        l match {
          case List() =>
            l
          case head :: tail =>
            if (head == elt) {
              compressHelper(tail, elt)
            } else {
              head +: compressHelper(tail, head)
            }
        }
      }

      l match {
        case List() =>
          l
        case head :: tail =>
          head +: compressHelper(tail, head)
      }
    }
}
