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
package binaryTree.P62B

import org.scalatest.Args
import scala.collection.immutable.List
import binaryTree.Tree
import binaryTree.End
import binaryTree.Node

class sol01 extends P62B {
  def atLevel[T](l: Int, t: Tree[T]): List[T] =
    {
      require(l > 0)
      l match {
        case 1 =>
          t match {
            case End => List()
            case Node(value, _, _) =>
              List(value)
          }
        case n =>
          t match {
            case End => List()
            case Node(_, left, right) =>
              atLevel(n - 1, left) ::: atLevel(n - 1, right)
          }

      }
    }
}
