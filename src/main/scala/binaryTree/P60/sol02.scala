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
package binaryTree.P60

import binaryTree.Tree

class sol02 extends P60 {
  def minHbalNodes(height: Int): Int =
    Tree.minHbalNodes(height)

  def maxHbalHeight(n: Int): Int =
    Tree.maxHbalHeight(n)

  def minHbalHeight(n: Int): Int =
    Tree.minHbalHeight(n)

  def hbalTreesWithNodes[T](n: Int, elem: T): List[Tree[T]] =
    Tree.hbalTreesWithNodes(n, elem)
}
