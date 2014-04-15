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
package binaryTree.P60

import binaryTree.Tree

class sol01 extends P60 {
  /*
   * case 0 => 0
   * case 1 => +
   * case 2 =>
   * 	+
   *   /
   *  +
   *  
   * case 3 =>
   *                       +    { Additional node
   *                      / \
   *                 }   +   +  { minHbalNodes(1)
   * minHbalNodes(2) }  /
   *                 } +
   * 
   * case 4 =>
   *                         +      { Additional node
   *                        / \
   *                 }     +   +    { 
   *                 }    / \   \   {  minHbalNodes(2)
   * minHbalNodes(2) }   +   +   +  { 
   *                 }  /
   *                 } +
   */
  def minHbalNodes(height: Int): Int =
    height match {
      case n if n <= 0 => 0
      case 1 => 1
      case n =>
        1 + minHbalNodes(n - 1) + minHbalNodes(n - 2)
    }

  def maxHbalHeight(n: Int): Int =
    {
      var i = 0
      // Select the maximum height such that the minimum number of nodes in a height-balanced tree of this height
      // is inferior or equals to n
      while (minHbalNodes(i) <= n) {
        i = i + 1
      }
      i - 1
    }

  def minHbalHeight(n: Int): Int =
    {
      var i = 0
      // Select the minimum height such that the maximum number of nodes in a height-balanced tree of this height
      // is superior or equals to n
      while (maxHbalNodes(i) < n) {
        i = i + 1
      }
      i
    }

  def hbalTreesWithNodes[T](n: Int, elem: T): List[Tree[T]] =
    {
      // Construction of a range of heights that includes the height-balanced trees of this height with n nodes
      val rangeOfHeights = (minHbalHeight(n) to maxHbalHeight(n)).toList

      // Build all height-balanced trees of those heights
      val trees =
        (for (h <- rangeOfHeights)
          yield (Tree.hbalTrees(h, elem)))
          .flatten

      // Only keep trees with correct number of nodes
      trees.filter(_.nodeNumber == n)
    }
}
