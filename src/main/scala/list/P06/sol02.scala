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
package list.P06

object sol02 extends P06 {
  def isPalindrome[T](l: List[T]): Boolean = {
    def isPalindrome(start: Int, end: Int): Boolean =
      if (start >= end)
        true
      else {
        if (l(start) == l(end)) {
          isPalindrome(start + 1, end - 1)
        } else {
          false
        }
      }
    isPalindrome(0, l.size - 1)
  }
}
