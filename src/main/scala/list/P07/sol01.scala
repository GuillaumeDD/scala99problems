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
package list.P07

object sol01 extends P07 {
  def flatten(l: List[Any]): List[Any] = {
    // Helper function
    def flattenAny(elt: Any): List[Any] =
      elt match {
        case l: List[Any] =>
          flatten(l)
        case e =>
          List(e)
      }

    l match {
      case List() =>
        List()
      case head :: tail =>
        flattenAny(head) ++ flatten(tail)
    }
  }
}
