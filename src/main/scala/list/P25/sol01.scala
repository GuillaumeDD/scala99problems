/*******************************************************************************
 * Copyright (c) 2014 Guillaume DUBUISSON DUPLESSIS <guillaume.dubuisson_duplessis@insa-rouen.fr>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     Guillaume DUBUISSON DUPLESSIS <guillaume.dubuisson_duplessis@insa-rouen.fr> - initial API and implementation
 ******************************************************************************/
package list.P25

class sol01 extends P25 {

  import list.P23.sol02._

  def randomPermute[T](l: List[T]): List[T] =
    l match {
      case List() => List()
      case other =>
        // Usage of solution of P23
        // Random selection of the elements of the list l
        randomSelect(l.size, l)
    }
}
