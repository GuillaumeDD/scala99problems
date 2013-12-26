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
package P13

object sol01 extends P13 {
  def encodeDirect[T](l: List[T]): List[(Int, T)] =
    l match {
      case List() => List()
      case l =>
        val (packed, next) = l span (_ == l.head)
        (packed.size, packed.head) :: encodeDirect(next)
    }
}
