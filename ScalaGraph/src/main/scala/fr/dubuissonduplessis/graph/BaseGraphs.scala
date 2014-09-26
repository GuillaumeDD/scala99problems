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

import scala.collection.mutable
import scala.annotation.tailrec
import scala.collection.immutable.Queue

trait BaseGraphs {
  type Node
  type Edge
  type Path = List[Node]

  /**
   * @see Default implementation [[fr.dubuissonduplessis.graph.impl.GraphsWithNoValue GraphsWithNoValue]]
   */
  type NodeValue
  /**
   * @see Default implementation [[fr.dubuissonduplessis.graph.impl.GraphsWithNoValue GraphsWithNoValue]]
   */
  def valueOf(n: Node): NodeValue

  /**
   * @see Default implementation [[fr.dubuissonduplessis.graph.impl.UniformEdgeCostGraphs UniformEdgeCostGraphs]]
   */
  type EdgeCost

  /**
   * @see Default implementation [[fr.dubuissonduplessis.graph.impl.UniformEdgeCostGraphs UniformEdgeCostGraphs]]
   */
  def costOf(e: Edge): EdgeCost
  def nullCost(): EdgeCost
  def addsUp(cost1: EdgeCost, cost2: EdgeCost): EdgeCost

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
    def adjacentNodesWithEdge(n: Node): Set[(Node, Edge)]
    def adjacentNodesWithCost(n: Node): Set[(Node, EdgeCost)]

    def edgeBetween(n1: Node, n2: Node): Option[Edge]

    def outgoing(n: Node): Set[Edge]
    def incoming(n: Node): Set[Edge]

    def cost(p: Path): EdgeCost =
      {
        def costOfHelper(previous: Node,
          nextPath: Path,
          currentCost: EdgeCost): EdgeCost =
          nextPath match {
            case List() => currentCost
            case headNode :: lastPath =>
              val edge = edgeBetween(previous, headNode).get
              costOfHelper(headNode, lastPath,
                addsUp(costOf(edge), currentCost))
          }

        p match {
          case List() => nullCost()
          case head :: last =>
            costOfHelper(head, last, nullCost())
        }
      }

    /**
     * Computes all acyclic paths from one node to another in a graph.
     *
     */
    def findPaths(start: Node, end: Node): List[Path] =
      {
        require(nodes.contains(start))
        require(nodes.contains(end))

        def findPathsHelper(newStart: Node, visitedNode: Set[Node]): List[List[Node]] = {
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
              subpath <- findPathsHelper(adjacentNode, visitedNode + adjacentNode)
              // For each subpath, addition of the start node
              path = newStart :: subpath
            } yield (path))
          }
        }

        findPathsHelper(start, Set(start))
      }

    /**
     * Computes a shortest path between two nodes, if it exists.
     * @note Implements Dijkstra's algorithm aka uniform-cost search space.
     *
     */
    def findShortestPath(start: Node, end: Node)(implicit ord: Ordering[EdgeCost]): Option[(Path, EdgeCost)] =
      {
        // Definition of a heuristic always returning 0
        val nullValue = nullCost()
        def heuristic(start: Node, end: Node): EdgeCost =
          nullValue
        // Call A* implementation with an empty heuristic <=> uniform graph search (Dijkstra's algorithm) 
        findHeuristicalShortestPath(heuristic)(start, end)
      }

    /**
     * Determines a shortest path by taking into account a heuristic.
     * @note Implements A* algorithm
     * @param heuristic Heuristic function that takes as first parameter the current node
     * and as second parameter the goal node.
     *
     */
    def findHeuristicalShortestPath(heuristic: (Node, Node) => EdgeCost)(start: Node, end: Node)(implicit ord: Ordering[EdgeCost]): Option[(Path, EdgeCost)] =
      {
        require(nodes.contains(start))
        require(nodes.contains(end))

        // Helper functions to clarify the algorithm
        def isGoal(n: Node): Boolean =
          n == end

        val node = start
        val initialPathCost = nullCost()

        // Frontier that is explored as a priority queue
        // TODO: Replace the the implementation of the PriorityQueue by a better implementation?
        var frontier = mutable.PriorityQueue((node, initialPathCost))(
          // Order the node/cost pair by cost
          // Note the ".reverse": the smallest the cost of the path, the higher its priority
          Ordering.by[(Node, EdgeCost), EdgeCost](_._2).reverse)
        // Helper functions to deal with the frontier
        def frontierContains(n: Node): Boolean =
          frontier.exists(_._1 == n)
        def updateFrontier(n: Node, cost: EdgeCost): Unit =
          {
            // TODO Find a cleaner way to update the priority queue.
            frontier = frontier.filter(_._1 != n)
            frontier.enqueue((n, cost))
          }

        // Mapping structure: node -> path cost from start to this node
        val costSoFar = mutable.Map[Node, EdgeCost]()
        costSoFar += (start -> initialPathCost)

        def hasBeenExplored(n: Node): Boolean =
          costSoFar.contains(n)

        // Mapping structure: node -> previousNode
        val cameFrom = mutable.Map[Node, Node]()
        cameFrom += (start -> start)
        // Builds the path from an end to the start, if it exists
        def buildPath: Option[(Path, EdgeCost)] = {
          @tailrec
          def buildPathTo(n: Node, p: Path): Path =
            {
              if (n == start) {
                start :: p
              } else {
                buildPathTo(cameFrom(n), n :: p)
              }
            }
          if (cameFrom.contains(end)) {
            Some(buildPathTo(end, List()) -> costSoFar(end))
          } else {
            None
          }
        }

        while (frontier.nonEmpty) {
          // Retrieval of the "cheaper" node on the frontier
          val (currentNode, _) = frontier.dequeue()
          val currentCost = costSoFar(currentNode)

          if (!isGoal(currentNode)) {
            /* Addition to the frontier of adjacent nodes.
             * If the node has already been visited, its reaching
             * cost may be updated if it is lower than the cost
             * originally stored so far.
             * 
             */
            for ((adjacentNode, costFromCurrentNodeToAdjacentNode) <- adjacentNodesWithCost(currentNode)) {
              val adjacentNodeIsInFrontier = frontierContains(adjacentNode)
              val costToReachAdjacentNode = addsUp(currentCost, costFromCurrentNodeToAdjacentNode)

              if (!hasBeenExplored(adjacentNode) && !adjacentNodeIsInFrontier) {
                // New node case
                cameFrom += (adjacentNode -> currentNode)
                costSoFar += (adjacentNode -> costToReachAdjacentNode)
                // Frontier update
                val solutionCostEstimate = addsUp(costToReachAdjacentNode, heuristic(adjacentNode, end))
                frontier.enqueue((adjacentNode, solutionCostEstimate))
              } else if (adjacentNodeIsInFrontier && ord.lt(costToReachAdjacentNode, costSoFar(adjacentNode))) {
                /*
                 * Already visited node case.
                 * Update of the node since its cost is lower than the
                 * one originally stored.
                 */
                cameFrom += (adjacentNode -> currentNode)
                costSoFar += (adjacentNode -> costToReachAdjacentNode)
                // Frontier update
                val solutionCostEstimate = addsUp(costToReachAdjacentNode, heuristic(adjacentNode, end))
                updateFrontier(adjacentNode, solutionCostEstimate)
              }
            }
          }
        }

        buildPath
      }

    /**
     * Computes all closed paths (cycles) starting at a given node.
     *
     */
    def findCycles(node: Node): List[Path] =
      for {
        // Consideration of each adjacent node
        adjacentNode <- adjacentNodes(node).toList
        subpath <- findPaths(adjacentNode, node)
        path = node :: subpath
      } yield (path)

    /**
     * Computes all spanning forests of a given graph.
     *
     */
    lazy val spanningForests: Set[BaseGraph] =
      {
        // Helper method to build all spanning trees
        def spanningForestsHelper(
          unvisitedNodes: Set[Node],
          visitedNodes: Set[Node],
          frontier: Queue[(Node, Edge)],
          selectedEdges: Set[Edge]): Set[BaseGraph] =
          if (frontier.isEmpty && unvisitedNodes.isEmpty) {
            // Case 0: no more node to explore -> graph generation
            Set(newGraph(visitedNodes, selectedEdges))
          } else if (frontier.isEmpty) {
            // Case 1: frontier is empty -> selection of an unvisited node
            // (useful when building spanning forest)
            for {
              currentNode <- unvisitedNodes
              newVisitedNodes = visitedNodes + currentNode
              neighbors = adjacentNodesWithEdge(currentNode)
                // Removal from frontier of the already visited nodes
                .filter({
                  case (node, _) => !newVisitedNodes.contains(node)
                })
              graph <- spanningForestsHelper(
                // Update the unvisited nodes
                unvisitedNodes - currentNode,
                // Update the visited node
                newVisitedNodes,
                // Add neighbors of the new current node to the frontier
                frontier enqueue neighbors,
                // Don't touch the selected edges
                selectedEdges)
            } yield (graph)
          } else {
            // Case 2: frontier is not empty -> explore frontier
            (for {
              // Select a frontier node
              (selectedNode, edge) <- frontier
              // Filter frontier nodes that are already visited
              if !visitedNodes.contains(selectedNode)
              newVisitedNodes = visitedNodes + selectedNode
              // Compute neighbors to be added to the frontier
              neighbors = adjacentNodesWithEdge(selectedNode)
              // Computation of the new frontier
              newFrontier = (frontier enqueue neighbors)
                // Removal of the already visited nodes
                .filter({
                  case (node, _) => {
                    !newVisitedNodes.contains(node)
                  }
                })
              newUnvisitedNodes = unvisitedNodes - selectedNode
              newEdges = selectedEdges + edge
              graph <- spanningForestsHelper(
                newUnvisitedNodes,
                newVisitedNodes,
                newFrontier,
                newEdges)
            } yield (graph)).toSet
          }

        spanningForestsHelper(
          nodes,
          Set(),
          Queue(),
          Set())
      }

    /**
     * Computes a new graph from a given set of nodes that consists of
     * these nodes and any edges of the original graph that connect them.
     */
    def subGraph(nodes: Set[Node]): BaseGraph

    // Technical methods
    protected def canEqual(other: BaseGraph): Boolean
    override def equals(other: Any): Boolean =
      other match {
        case that: BaseGraph =>
          (that canEqual this) &&
            that.nodes == this.nodes &&
            that.edges == this.edges
        case _ =>
          false
      }
    override def hashCode(): Int =
      41 * (41 + nodes.hashCode) + edges.hashCode
    override def toString(): String =
      s"""Nodes: ${nodes.mkString(", ")}
Edges: ${edges.mkString(", ")}"""
  }

  /**
   * Builds a new graph from a given set of nodes and a given set of edges.
   */
  def newGraph(nodes: Set[Node], edges: Set[Edge]): BaseGraph
}
