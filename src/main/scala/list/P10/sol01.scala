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
package list.P10

class sol01 extends P10 {
  def encode[T](l: List[T]): List[(Int, T)] =
    sol01.encode(l)
}

object sol01 {
  import list.P09.sol01._
  def encode[T](l: List[T]): List[(Int, T)] =
    l match {
      case List() => List()
      case ll =>
        pack(ll).map(ls => (ls.size, ls.head))
    }
}
