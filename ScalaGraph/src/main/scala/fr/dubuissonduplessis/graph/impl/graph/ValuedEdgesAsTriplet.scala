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
package fr.dubuissonduplessis.graph.impl.graph

import fr.dubuissonduplessis.graph.Graphs
import scala.language.implicitConversions

trait ValuedEdgesAsTriplet extends Graphs {

  case class Edge(n1: Node, n2: Node, cost: EdgeCost) {
    override def equals(that: Any) = that match {
      case that: Edge => (that canEqual this) && (
        this.n1 == that.n1 && this.n2 == that.n2 ||
        this.n1 == that.n2 && this.n2 == that.n1)
      case _ => false
    }
    override def canEqual(that: Any) =
      that.isInstanceOf[Edge]
    override def hashCode = n1.hashCode + n2.hashCode
  }

  /**
   * Implicit conversion from a pair of nodes to an edge.
   */
  implicit def tripletToEdge(p: (Node, Node, EdgeCost)): Edge =
    Edge(p._1, p._2, p._3)

  def nodesOf(e: Edge): (Node, Node) =
    (e.n1, e.n2)

  def costOf(e: Edge): EdgeCost =
    e.cost
}
