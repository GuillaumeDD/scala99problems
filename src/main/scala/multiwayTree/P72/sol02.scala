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
package multiwayTree.P72

import multiwayTree.MTree
import org.scalatest.Args
import scala.collection.immutable.List

class sol02 extends P72 {
  def postorder[T](t: MTree[T]): List[T] =
    t.children.flatMap(postorder(_)) ::: List(t.value)
}
