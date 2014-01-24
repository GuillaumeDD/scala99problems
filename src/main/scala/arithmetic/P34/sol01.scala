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
package arithmetic.P34

import org.scalatest.Args
import arithmetic.P33.sol01._
class sol01 extends P34 {
  def totient(a: Int): Int = {
    require(a >= 0)
    (1 to a).filter(n => isCoprimeTo(n, a)).size
  }
}
