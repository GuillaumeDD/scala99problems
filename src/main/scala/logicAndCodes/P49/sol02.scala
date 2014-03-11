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
package logicAndCodes.P49

import org.scalatest.Args
import scala.collection.immutable.List
import scala.collection.mutable

class sol02 extends P49 {
  def gray(l: Int): List[String] = {
    require(l >= 0)
    import sol02._
    l match {
      case 0 | 1 => memory(l)
      case n =>
        memory.getOrElse(n, {
          val previousGray = gray(n - 1)
          previousGray.map("0" + _) ++ previousGray.map("1" + _).reverse
        })
    }
  }
}

object sol02 {
  private val memory = mutable.Map(
    0 -> List[String](),
    1 -> List[String]("0", "1"))
}
