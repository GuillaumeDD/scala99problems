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

trait BaseGraphs {
  type Node
  type Edge

  /**
   * Determines the unordered pair of nodes with respect
   * to a given edge.
   *
   */
  def nodesOf(e: Edge): (Node, Node)
  /**
   * Computes the other node of an edge, given the edge
   * and a node.
   *
   */
  def otherNode(e: Edge, n: Node): Node = {
    val (n1, n2) = nodesOf(e)
    require(n == n1 || n == n2, s"$n is not part of edge $e")

    if (n == n1) {
      n2
    } else {
      n1
    }
  }

  trait BaseGraph {
    def nodes: Set[Node]
    def edges: Set[Edge]

    def adjacentNodes(n: Node): Set[Node]

    def sources: Set[Node]

    /**
     * Computes all acyclic paths from one node to another in a graph.
     *
     */
    def findsPaths(start: Node, end: Node): List[List[Node]] =
      {
        require(nodes.contains(start))
        require(nodes.contains(end))

        def findsPathsHelper(newStart: Node, visitedNode: Set[Node]): List[List[Node]] = {
          if (newStart == end) {
            // If the starting node is the end, then return the end
            List(List(end))
          } else {
            (for {
              // Consideration of each adjacent node
              adjacentNode <- adjacentNodes(newStart).toList
              // Avoidation of cyclic path
              if (!visitedNode.contains(adjacentNode))
              // Builds up the subpaths from the adjacent node to the end node
              subpath <- findsPathsHelper(adjacentNode, visitedNode + adjacentNode)
              // For each subpath, addition of the start node
              path = newStart :: subpath
            } yield (path))
          }
        }

        findsPathsHelper(start, Set(start))
      }

    /**
     * Computes all closed paths (cycles) starting at a given node.
     *
     */
    def findsCycles(node: Node): List[List[Node]] =
      for {
        // Consideration of each adjacent node
        adjacentNode <- adjacentNodes(node).toList
        subpath <- findsPaths(adjacentNode, node)
        path = node :: subpath
      } yield (path)

    /**
     * Computes a new graph from a given set of nodes that consists of
     * these nodes and any edges of the original graph that connect them.
     */
    def subGraph(nodes: Set[Node]): BaseGraph
  }

  /**
   * Builds a new graph from a given set of nodes and a given set of edges.
   */
  def newGraph(nodes: Set[Node], edges: Set[Edge]): BaseGraph
}
