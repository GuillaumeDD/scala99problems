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
package list.P19

object sol03 extends P19 {
  def rotate[T](n: Int, l: List[T]): List[T] = {
    val rotations = if (l.isEmpty) 0 else n % l.size
    if (rotations < 0) {
      rotate(rotations + l.size, l)
    } else {
      (l drop rotations) ++ (l take rotations)
    }
  }
}
