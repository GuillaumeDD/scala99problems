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
package fr.dubuissonduplessis.graph.impl.graph

import fr.dubuissonduplessis.graph.Graphs
import scala.language.implicitConversions

/**
 * Provides definition of edge as a pair of nodes.
 * Edges are not implemented directly as Scala pair. They are
 * implemented with a home-made class which override the equal
 * method to ignore order of nodes in equality test. Hence, an
 * edge like (node1, node2) is the same as (node2, node1).
 *
 * It provides an implicit conversion from pair to the edge
 * implementation.
 * @author Guillaume DUBUISSON DUPLESSIS <guillaume.dubuisson_duplessis@insa-rouen.fr>
 *
 */
trait EdgesAsPairs extends Graphs {
  case class Edge(n1: Node, n2: Node) {
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
  implicit def pairToEdge(p: (Node, Node)): Edge =
    Edge(p._1, p._2)

  def nodesOf(e: Edge): (Node, Node) =
    (e.n1, e.n2)
}
