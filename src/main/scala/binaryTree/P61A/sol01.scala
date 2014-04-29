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
package binaryTree.P61A

import org.scalatest.Args
import scala.collection.immutable.List
import binaryTree.Tree
import binaryTree.End
import binaryTree.Node

class sol01 extends P61A {
  def leafList[T](t: Tree[T]): List[T] =
    t match {
      case End => List()
      case Node(value, End, End) =>
        List(value)
      case Node(_, left, right) =>
        leafList(left) ::: leafList(right)
    }
}
