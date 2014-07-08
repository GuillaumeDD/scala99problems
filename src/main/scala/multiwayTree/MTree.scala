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
package multiwayTree

/**
 * A multiway tree is composed of a root element and a (possibly empty) set of successors
 * which are multiway trees themselves.
 * A multiway tree is never empty.
 * The set of successor trees is sometimes called a forest.
 * @author Guillaume DUBUISSON DUPLESSIS <guillaume.dubuisson_duplessis@insa-rouen.fr>
 *
 * @tparam type of the root element of the multiway tree
 * @param value root element of the multiway tree
 * @param children (possibly empty) set of successors
 */
case class MTree[+T](
  value: T,
  children: List[MTree[T]]) {

  /**
   * Number of nodes of the tree
   */
  def nodeCount: Int =
    children.foldLeft(1)((result, nextNode) => result + nextNode.nodeCount)

  override def toString: String =
    s"M($value ${children.map(_.toString).mkString(",", "{", "}")})"
}

object MTree {
  def apply[T](value: T) = new MTree(value, List())
}
