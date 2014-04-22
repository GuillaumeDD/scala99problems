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
package binaryTree.P61

import binaryTree.Tree
import binaryTree.Node
import binaryTree.End

class sol01 extends P61 {

  def leafCount[T](t: Tree[T]): Int =
    t match {
      case End =>
        0
      case Node(_, End, End) =>
        1
      case Node(_, t1, t2) =>
        leafCount(t1) + leafCount(t2)
    }
}
