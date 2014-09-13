package fr.dubuissonduplessis.graph.app.grid

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
}
