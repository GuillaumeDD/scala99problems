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

import fr.dubuissonduplessis.benchmark.Benchmarking

object MainGrids extends App {
  val grid = Array.ofDim[Int](4, 4)
  for {
    i <- 0 until grid.length
    j <- 0 until grid(0).length
  } {
    grid(i)(j) = 1
  }

  for {
    i <- 1 to 2
    j <- 1 to 2
  } {
    grid(i)(j) = 42
  }
  grid(0)(0) = 11

  import Grids._
  val g = newGridAsDigraph(grid)
  println(g.mkString)
  val (path, cost) = g.findShortestPath((3, 0), (0, 3)).get
  println(path.mkString("->"))
  println(s"Cost: $cost")

  def heuristic(currentNode: Node, endNode: Node): Int =
    {
      val (x1, y1) = currentNode
      val (x2, y2) = endNode
      (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)
    }
  val (path2, cost2) = g.findHeuristicalShortestPath(heuristic)((3, 0), (0, 3)).get
  println(path2.mkString("->"))
  println(s"Cost: $cost2")
  
  Benchmarking.tictac("A*") {
    val (path, cost) = g.findShortestPath((3, 0), (0, 3)).get
  }
  Benchmarking.tictac("Uniform cost search") {
    val (path, cost) = g.findShortestPath((3, 0), (0, 3)).get
  }

}
