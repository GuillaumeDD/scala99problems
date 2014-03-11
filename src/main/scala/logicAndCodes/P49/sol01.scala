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

class sol01 extends P49 {

  def gray(l: Int): List[String] = {
    require(l >= 0)
    l match {
      case 0 => List()
      case 1 => List("0", "1")
      case n =>
        val previousGray = gray(n - 1)
        val extension = previousGray.map("1" + _).reverse
        previousGray.map("0" + _) ++ extension
    }
  }
}
