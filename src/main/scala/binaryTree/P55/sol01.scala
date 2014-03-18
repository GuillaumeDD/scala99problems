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
package binaryTree.P55

import org.scalatest.Args
import scala.collection.immutable.List
import binaryTree.Tree
import binaryTree.Node
import binaryTree.End

class sol01 extends P55 {
  def cBalanced[T](nbNodes: Int, value: T): List[Tree[T]] =
    Tree.cBalanced(nbNodes, value)
}
