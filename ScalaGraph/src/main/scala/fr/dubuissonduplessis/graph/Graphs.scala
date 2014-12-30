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
import scala.collection.immutable.Queue

trait Graphs extends BaseGraphs {
  type Graph <: GraphSig

  trait GraphSig extends BaseGraph {
	  
    def connectedNodes(n: Node): Set[Node] =
      adjacentNodes(n)
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

    /**
     * Computes a minimum spanning tree of this graph.
     *
     */
    def minimumSpanningTree(implicit ev: EdgeCost => Ordered[EdgeCost]): Graph =
      {
        def helperMinimumSpanningTree(
          selectedNodes: Set[Node],
          selectedEdges: Set[Edge]): Graph =
          if (selectedNodes == nodes) {
            newGraph(selectedNodes, selectedEdges)
          } else {
            // 1- Choose an edge such that its origin is in 'selectedNode' and its extremity is not in 'selectedNode'
            val candidateEdges = (edges -- selectedEdges).filter(
              edge =>
                {
                  val (node1, node2) = nodesOf(edge)
                  !selectedNodes.contains(node1) || !selectedNodes.contains(node2)
                })

            // 2- Sort candidate edges by ascending cost
            val sortedCandidateEdges = candidateEdges.toList.sortBy(edge => costOf(edge))

            // 3- Select the cheaper edge
            val selectedEdge = sortedCandidateEdges.head
            val correspondingNodes = List(nodesOf(selectedEdge)._1, nodesOf(selectedEdge)._2)
            require(selectedNodes.contains(nodesOf(selectedEdge)._1) ||
              selectedNodes.contains(nodesOf(selectedEdge)._2))

            helperMinimumSpanningTree(
              selectedNodes ++ correspondingNodes,
              selectedEdges + selectedEdge)
          }

        if (nodes.isEmpty) {
          newGraph(nodes, edges)
        } else {
          val firstNode = nodes.head
          helperMinimumSpanningTree(Set(firstNode), Set())
        }
      }

    /**
     * Computes all spanning forests of a given graph.
     *
     */
    lazy val spanningForests: Set[Graph] =
      {
        // Helper method to build all spanning trees
        def spanningForestsHelper(
          unvisitedNodes: Set[Node],
          visitedNodes: Set[Node],
          frontier: Queue[(Node, Edge)],
          selectedEdges: Set[Edge]): Set[Graph] =
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
     * Determines whether this graph is a forest (i.e., a collection of tree).
     */
    lazy val isForest: Boolean =
      spanningForests.size == 1
    /**
     * Determines if this graph is fully connected, i.e. if it exists a path between
     * each pair of nodes.
     */
    lazy val isConnected: Boolean =
      {
        def isConnectedHelper(
          // Nodes left to explore
          frontier: List[Node],
          // Nodes that are connected
          visitedNodes: Set[Node]): Boolean =
          frontier match {
            case List() =>
              // No more node to explore
              // Check that the set of connected nodes equals the set of nodes
              visitedNodes == nodes
            case currentNode :: leftNodes =>
              // Update visited nodes
              val newVisitedNodes = visitedNodes + currentNode

              // Computation of the new frontier
              val neighbors = adjacentNodes(currentNode)
              val newFrontier = (leftNodes ++ neighbors)
                // Clear frontier from already visited nodes
                .filter(!newVisitedNodes.contains(_))

              // Continue exploring the connected part
              isConnectedHelper(newFrontier, newVisitedNodes)
          }
        nodes.toList match {
          case List() =>
            true
          case head :: _ =>
            isConnectedHelper(List(head), Set())
        }
      }

    /**
     * Determines if this graph is a tree.
     */
    lazy val isTree: Boolean =
      isForest && isConnected

    /**
     * Computes the degree of a given node in this graph.
     * @note It does not take into account self-loop.
     * @note It is required that the graph contains the given node.
     */
    def degree(n: Node): Int = {
      require(nodes.contains(n), s"$n is not a node of this graph.")
      adjacentNodes(n).filter(_ != n).size
    }

    /**
     *  Lists all nodes of a graph sorted according to decreasing degree.
     */
    lazy val nodesByDegree: List[Node] =
      nodes.toList.sortBy(-degree(_))

    /**
     * Uses Welsh-Powell's algorithm to paint the nodes of a graph in such
     * a way that adjacent nodes have different colors.
     * @return list of tuples, each of which contains a node and an integer
     * representing its color.
     */
    def coloredNodes(): List[(Node, Int)] =
      {
        def coloredNodesHelper(color: Int, uncolored: List[Node], colored: List[(Node, Int)]): List[(Node, Int)] =
          uncolored match {
            case List() =>
              // All nodes are colored
              colored
            case nonEmptyList =>
              val newColored = applyColor(color, uncolored, colored, Set())
              coloredNodesHelper(
                // Generation of a new color
                color + 1,
                // Update of colored nodes
                {
                  val newColoredNodes = newColored.map(_._1)
                  uncolored.filterNot(newColoredNodes.contains(_))
                },
                // Communication of the colored nodes
                newColored)
          }

        def applyColor(color: Int, uncolored: List[Node], colored: List[(Node, Int)], adjacentNodes: Set[Node]): List[(Node, Int)] =
          uncolored match {
            case List() => colored
            /*
             * head: represents a node to be colored
             * tail: potential other nodes to color
             */
            case head :: tail =>
              // Store adjacent nodes
              val newAdjacentNodes = adjacentNodes ++ this.adjacentNodes(head)
              applyColor(color,
                // Research the next node which is not adjacent in the tail
                // (the next node to color with the same color)
                tail.dropWhile(newAdjacentNodes.contains(_)),
                // Color the head of this list
                (head, color) :: colored,
                newAdjacentNodes)
          }

        coloredNodesHelper(0, nodesByDegree, List())
      }

    protected def canEqual(other: BaseGraph): Boolean =
      other.isInstanceOf[GraphSig]
  }

  def newGraph(nodes: Set[Node], edges: Set[Edge]): Graph
}
