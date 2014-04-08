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
package binaryTree.P59

import binaryTree.Node
import binaryTree.End
import binaryTree.Tree

class sol01 extends P59 {
  def hbalTrees[T](n: Int, elem: T): List[Tree[T]] =
    n match {
      case 0 => List(End)
      case 1 => List(Node(elem))
      case h =>
        val subtrees = hbalTrees(h - 1, elem)
        val smallerSubtrees = hbalTrees(h - 2, elem)
        (for {
          l <- subtrees
          r <- subtrees
        } yield (Node(elem, l, r))) ++ (
          for {
            l <- subtrees
            r <- smallerSubtrees
          } yield (List(Node(elem, l, r), Node(elem, r, l))))
          .flatten
    }
}
