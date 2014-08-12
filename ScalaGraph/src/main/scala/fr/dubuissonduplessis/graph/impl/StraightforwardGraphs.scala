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
package fr.dubuissonduplessis.graph.impl

import fr.dubuissonduplessis.graph.Graphs

abstract class StraightforwardGraphs extends Graphs {
  class Graph private[StraightforwardGraphs] (
    val nodes: Set[Node],
    val edges: Set[Edge]) extends GraphSig {
    def outgoing(n: Node): Set[Edge] =
      edges filter (e => pred(e) == n)
    def incoming(n: Node): Set[Edge] =
      edges filter (e => succ(e) == n)

    // Caching: Usage of lazy
    lazy val sources: Set[Node] =
      nodes filter (incoming(_).isEmpty)

    def topSort: Seq[Node] =
      if (nodes.isEmpty) {
        List()
      } else {
        require(sources.nonEmpty)
        sources.toList ++
          subGraph(nodes -- sources).topSort
      }
  }

  def newGraph(nodes: Set[Node], edges: Set[Edge]): Graph =
    new Graph(nodes, edges)
}
