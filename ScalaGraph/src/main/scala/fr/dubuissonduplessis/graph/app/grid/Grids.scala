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
package fr.dubuissonduplessis.graph.app.grid

import fr.dubuissonduplessis.graph.Digraphs
import fr.dubuissonduplessis.graph.impl.digraph.SequentialDigraphsImpl
import fr.dubuissonduplessis.graph.impl.GraphsWithNoValue
import fr.dubuissonduplessis.graph.impl.IntEdgeCostGraphs
import scala.collection.mutable

object Grids extends Digraphs
  with GraphsWithNoValue
  with IntEdgeCostGraphs {

  type Node = (Int, Int)
  type Edge = (Node, Int, Node)

  /*
   * 
   * Allowed diagonal neighbors:
  implicit def neighbors(node: (Int, Int), nb_line: Int, nb_col: Int): List[(Int, Int)] =
    {
      val (i, j) = node
      (for {
        i_ <- i - 1 to i + 1
        j_ <- j - 1 to j + 1
        if i_ != i || j_ != j
        if i_ >= 0 && i_ < nb_line
        if j_ >= 0 && j_ < nb_col
        adjacentNode = (i_, j_)
      } yield (adjacentNode)).toList
    } 
    */

  /**
   * Determines the neighbors of a node in a grid given the coordinates of the node,
   * the number of lines of the grid and the number of columns of the grid. This function
   * computes the (at most) 4 Manhattan neighbors.
   */
  implicit def neighbors(node: (Int, Int), nb_line: Int, nb_col: Int): List[(Int, Int)] =
    {
      val (i, j) = node
      // Helper function to filter cells outside the grid
      def valid(node: (Int, Int)): Boolean = {
        val (i_, j_) = node
        (i_ != i || j_ != j) && // Should not be the grid
          i_ >= 0 && i_ < nb_line && // Should be on an existing line
          j_ >= 0 && j_ < nb_col // Should be on an existing column
      }
      // Possible neighbors...
      List((i + 1, j), (i - 1, j), (i, j - 1), (i, j + 1))
        .filter(valid(_)) // ... getting filtered for correctness
    }

  /**
   *
   * @author Guillaume DUBUISSON DUPLESSIS <guillaume.dubuisson_duplessis@insa-rouen.fr>
   * @param grid Grid representing the graph
   * @param neighbors Function that computes the adjacent nodes of a node in a grid, given its
   * coordinates, the number of lines of the grid and the number of columns of the grid.
   */
  class Digraph private[Grids] (
    private[Grids] val grid: Array[Array[Int]])(implicit neighbors: ((Int, Int), Int, Int) => List[(Int, Int)]) extends DigraphSig {

    require(grid.length > 0, s"Cannot process empty grid")

    private[Grids] val nb_line = grid.length
    private[Grids] val nb_col = grid(0).length
    private[Grids] def leavingCost(n: Node): Int =
      {
        val (i, j) = n
        grid(i)(j)
      }

    // Pre-processing to speed up graph operations
    lazy val nodes: Set[Node] =
      (for {
        i <- 0 until nb_line
        j <- 0 until nb_col
      } yield {
        (i, j)
      }).toSet

    val edges: Set[Edge] =
      for {
        currentNode <- nodes
        (i_, j_) <- neighbors(currentNode, nb_line, nb_col)
        adjacentNode = (i_, j_)
        (i, j) = currentNode
      } yield {
        (currentNode, leavingCost(i, j), adjacentNode)
      }

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

  def costOf(e: Edge): Int =
    e._2

  def pred(e: Edge): Node =
    e._1
  def succ(e: Edge): Node =
    e._3

  /**
   * Generates a graph representation of a grid, represented as
   * a 2D-array. Value of each cell represents the cost to leave
   * this cell for an adjacent cell.
   *
   */
  def newGridAsDigraph(grid: Array[Array[Int]])(implicit neighbors: ((Int, Int), Int, Int) => List[(Int, Int)]): Digraph =
    new Digraph(grid)

  /**
   * Unsupported operation.
   * @throws UnsupportedOperationException Forever
   * @see [[fr.dubuissonduplessis.graph.app.grid.Grids#newGridAsDigraph Method newGridAsDigraph]]
   */
  def newGraph(nodes: Set[Node], edges: Set[Edge]): Digraph =
    throw new UnsupportedOperationException("Use the method newGridAsDigraph instead")

  /**
   * Provides extension methods for a graph representing a grid.
   * @author Guillaume DUBUISSON DUPLESSIS <guillaume.dubuisson_duplessis@insa-rouen.fr>
   *
   */
  implicit class RichGraph(g: Digraph) {
    def mkString: String = {
      val beforeLine = "_" + "_" * 4 * g.nb_col + "\n"
      val betweenLine = "|" + ("-" * 3 + "|") * g.nb_col + "\n"
      val afterLine = "‾" + "‾" * 4 * g.nb_col + "\n"
      val lines =
        (for {
          line <- g.grid
        } yield {
          // Line Layout
          (for {
            e <- line
          } yield (f"$e%3d")).mkString("|", "|", "|\n")
        }) // Grid Layout
          .mkString("", betweenLine, "")

      beforeLine + lines + afterLine
    }
  }
}
