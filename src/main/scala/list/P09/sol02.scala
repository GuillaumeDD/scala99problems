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
package list.P09

class sol02 extends P09 {
  def pack[T](l: List[T]): List[List[T]] =
    if (l.isEmpty) List(List())
    else {
      // span: a pair consisting of the longest prefix of this list whose elements all satisfy p,
      //       and the rest of this list.
      val (packed, next) = l span { _ == l.head }
      if (next == Nil)
        List(packed)
      else
        packed :: pack(next)
    }
}
