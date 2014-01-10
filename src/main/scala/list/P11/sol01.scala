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
package list.P11

object sol01 extends P11 {

  import list.P09.sol01._
  // Note the Either type associated with Left and Right
  def encodeModified[T](l: List[T]): List[Either[T, (Int, T)]] =
    l match {
      case List() => List()
      case ll =>
        pack(ll).map {
          l =>
            if (l.size == 1)
              Left(l.head)
            else
              Right((l.size, l.head))
        }
    }
}
