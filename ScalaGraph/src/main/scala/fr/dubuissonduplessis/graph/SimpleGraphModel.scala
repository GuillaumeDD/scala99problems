package fr.dubuissonduplessis.graph

import fr.dubuissonduplessis.graph.impl.SequentialGraphsImpl
import fr.dubuissonduplessis.graph.impl.EdgesAsPairs

object SimpleGraphModel extends SequentialGraphsImpl with EdgesAsPairs with App {
  type Node = Char

  /*
   *                 _________ 
   *                /         _\
   *       a  ->  b  ->  c  ->  d
   *       ^____________/      /
   *       ^__________________/
   */
  val g = newGraph(Set('a', 'b', 'c', 'd'), Set(('a', 'b'), ('b', 'c'), ('b', 'd'), ('c', 'd'), ('c', 'a'), ('d', 'a')))
  println("Computation of paths from 'a' to 'd'")
  for (path <- g.findsPaths('a', 'd')) {
    println(path.mkString(" -> "))
  }
  println("End.")

  println("Computation of cycle around 'b'")
  for (path <- g.findsCycles('b')) {
    println(path.mkString(" -> "))
  }
  println("End.")
}