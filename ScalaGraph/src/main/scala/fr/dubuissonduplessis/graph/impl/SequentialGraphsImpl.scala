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
package fr.dubuissonduplessis.graph.impl

import fr.dubuissonduplessis.graph.Graphs
import scala.collection.mutable

abstract class SequentialGraphsImpl extends Graphs {
  class Graph private[SequentialGraphsImpl] (
    val nodes: Set[Node],
    val edges: Set[Edge]) extends GraphSig {
    // Pre-processing to speed up graph operations
    private val outEdges, inEdges =
      new mutable.HashMap[Node, Set[Edge]] {
        override def default(key: Node) = Set()
      }
    for (e <- edges) {
      inEdges(succ(e)) += e
      outEdges(pred(e)) += e
    }

    def outgoing(n: Node) = outEdges(n)
    def incoming(n: Node) = inEdges(n)
    // Caching: Usage of lazy
    lazy val sources: Set[Node] =
      nodes filter (incoming(_).isEmpty)

    def topSort: Seq[Node] = {
      val indegree = new mutable.HashMap[Node, Int]
      val sorted = new mutable.ArrayBuffer[Node]

      // Initialization of indegree and of sorted
      for (x <- nodes) {
        indegree(x) = inEdges(x).size
        if (indegree(x) == 0) sorted += x
      }

      var frontier = 0
      while (frontier < sorted.length) {
        for (e <- outEdges(sorted(frontier))) {
          val x = succ(e)
          indegree(x) -= 1
          if (indegree(x) == 0) sorted += x
        }
        frontier += 1
      }

      sorted
    }
  }

  def newGraph(nodes: Set[Node], edges: Set[Edge]): Graph =
    new Graph(nodes, edges)
}
