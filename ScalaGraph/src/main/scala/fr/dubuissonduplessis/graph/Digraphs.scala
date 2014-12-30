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
package fr.dubuissonduplessis.graph

import fr.dubuissonduplessis.graph.impl.digraph.EdgesAsPairs

trait Digraphs extends BaseGraphs {
  /**
   * Determines the starting node of an oriented edge.
   */
  def pred(e: Edge): Node
  /**
   * Determines the ending node of an oriented edge.
   */
  def succ(e: Edge): Node

  def nodesOf(e: Edge): (Node, Node) =
    (pred(e), succ(e))

  type Digraph <: DigraphSig

  trait DigraphSig extends BaseGraph {
    def sources: Set[Node]

    def adjacentNodes(n: Node): Set[Node] =
      outgoing(n).map(succ(_))

    def adjacentNodesWithEdge(n: Node): Set[(Node, Edge)] =
      outgoing(n).map(edge => (succ(edge), edge))

    def adjacentNodesWithCost(n: Node): Set[(Node, EdgeCost)] =
      outgoing(n).map(edge => (succ(edge), costOf(edge)))

    def connectedNodes(n: Node): Set[Node] =
      outgoing(n).map(succ(_)) ++
        incoming(n).map(pred(_))

    def edgeBetween(n1: Node, n2: Node): Option[Edge] =
      edges.find(edge => {
        val (n1_, n2_) = (pred(edge), succ(edge))
        (n1_ == n1 && n2_ == n2)
      })

    // Specific algorithm for Digraphs
    /**
     * Computes the topological sorting of nodes of an acyclic graph.
     */
    def topSort: Seq[Node]

    def subGraph(nodes: Set[Node]): Digraph =
      newGraph(nodes,
        edges filter (e =>
          (nodes contains pred(e)) &&
            (nodes contains succ(e))))

    protected def canEqual(other: BaseGraph): Boolean =
      other.isInstanceOf[DigraphSig]
  }

  def newGraph(nodes: Set[Node], edges: Set[Edge]): Digraph
}
