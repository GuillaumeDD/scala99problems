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
package binaryTree.P63

import binaryTree.Tree
import binaryTree.End
import binaryTree.Node

class sol01 extends P63 {
  def completeBinaryTree[T](n: Int, elt: T): Tree[T] = {
    require(n >= 0)
    def generateTree(i: Int): Tree[T] =
      if (i > n) {
        End
      } else {
        Node(elt, generateTree(2 * i), generateTree(2 * i + 1))
      }

    generateTree(1)
  }
}
