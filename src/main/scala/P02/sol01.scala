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
package P02

import scala.annotation.tailrec

object sol01 extends P02 {
  @tailrec
  def penultimate[T](l: List[T]): T =
    l match {
      case a :: b :: Nil =>
        a
      case head :: tail =>
        penultimate(tail)
      case _ =>
        throw new NoSuchElementException()
    }
}
