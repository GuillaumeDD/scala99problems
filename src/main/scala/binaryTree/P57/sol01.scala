/**
 * *****************************************************************************
 * Copyright (c) 2014 Guillaume DUBUISSON DUPLESSIS <guillaume.dubuisson_duplessis@insa-rouen.fr>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 *
 * Contributors:
 *     Guillaume DUBUISSON DUPLESSIS <guillaume.dubuisson_duplessis@insa-rouen.fr> - initial API and implementation
 * ****************************************************************************
 */
package binaryTree.P57

import binaryTree.Tree

class sol01 extends P57 {

  def addValue[T, U >: T <% Ordered[U]](x: U, t: Tree[T]): Tree[U] =
    t.addValue(x)

  def fromList[U <% Ordered[U]](l: List[U]): Tree[U] =
    Tree.fromList(l)
}
