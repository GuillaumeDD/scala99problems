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

trait Graphs extends BaseGraphs {
  type Graph <: GraphSig

  trait GraphSig extends BaseGraph {
    def edgesOf(n: Node): Set[Edge]

    def edgeBetween(n1: Node, n2: Node): Option[Edge] =
      edges.find(edge => {
        val (n1_, n2_) = nodesOf(edge)
        (n1_ == n1 && n2_ == n2) ||
          (n1_ == n2 && n2_ == n1)
      })

    def subGraph(nodes: Set[Node]): Graph = {
      // Helper function to filter edges
      def keepEdge(e: Edge): Boolean = {
        val (n1, n2) = nodesOf(e)
        nodes.contains(n1) && nodes.contains(n2)
      }
      newGraph(nodes,
        edges filter (keepEdge _))
    }
  }

  def newGraph(nodes: Set[Node], edges: Set[Edge]): Graph
}
