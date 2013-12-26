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
package P04

import scala.annotation.tailrec

object sol02 extends P04 {

  def length[T](l: List[T]): Int = {
    // Helper function
    @tailrec
    def lengthHelper[T](l: List[T], acc: Int): Int =
      l match {
        case List() => acc
        case head :: tail =>
          lengthHelper(tail, acc + 1)
      }
    lengthHelper(l, 0)
  }
}
