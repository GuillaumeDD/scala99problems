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
import fr.dubuissonduplessis.graph.impl.StraightforwardDigraphs

trait StraightforwardGraphsImpl extends Graphs {
  class Graph private[StraightforwardGraphsImpl] (
    val nodes: Set[Node],
    val edges: Set[Edge]) extends GraphSig {

    def edgesOf(n: Node): Set[Edge] =
      {
        require(nodes.contains(n))
        edges.filter(edge =>
          {
            val (n1, n2) = nodesOf(edge)
            n1 == n || n2 == n
          })
      }

    def adjacentNodes(n: Node): Set[Node] =
      {
        require(nodes.contains(n))
        edgesOf(n).map(edge =>
          {
            val (n1, n2) = nodesOf(edge)
            if (n1 == n) {
              n2
            } else {
              n1
            }
          })
      }
  }

  def newGraph(nodes: Set[Node], edges: Set[Edge]): Graph =
    new Graph(nodes, edges)
}
