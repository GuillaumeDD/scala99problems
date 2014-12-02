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

import scala.annotation.tailrec
import scala.collection.mutable

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
     * Determines if a given graph is isomorphic to this graph.
     * @return true if it is isomorphic, else false
     */
    def isIsomorphicTo(oGraph: BaseGraph): Boolean =
      {
        /*
         * Helper function that computes a subset of all possible
         * mappings by pruning some invalid mappings thanks to a heuristic.
         */
        def generateMappings(): Iterator[Map[Node, Node]] =
          for {
            // Selection of a permutation of nodes of the other graph
            perm <- oGraph.nodes.toList.permutations
            // Building of a potential mapping
            potentialMapping = this.nodes.toList.zip(perm)
            // Checking of the potential mapping against the heuristics
            // (here, we only keep mappings which number of neighbors corresponds)
            if potentialMapping.forall({
              case (n1, n2) => this.adjacentNodes(n1).size == oGraph.adjacentNodes(n2).size
            })
          } yield (potentialMapping.toMap)

        /*
         * Checks whether a complete mapping is a isomorphic mapping. 
         */
        def isIsomorphicMapping(mapping: Map[Node, Node]): Boolean =
          this.nodes.forall(n =>
            this.adjacentNodes(n).map(mapping(_)).toSet == oGraph.adjacentNodes(mapping(n)).toSet)

        if (this.nodes.size == oGraph.nodes.size &&
          this.edges.size == oGraph.edges.size) {
          generateMappings().exists(isIsomorphicMapping(_))
        } else {
          // Case: graphs do not have the same number of
          // nodes or edges
          false
        }
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
