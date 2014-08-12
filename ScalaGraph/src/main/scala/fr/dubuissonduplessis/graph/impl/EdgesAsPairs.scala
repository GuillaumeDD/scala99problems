package fr.dubuissonduplessis.graph.impl

import fr.dubuissonduplessis.graph.Graphs

trait EdgesAsPairs extends Graphs {
  type Edge = (Node, Node)

  def succ(e: Edge) = {
    val (start, end) = e
    end
  }

  def pred(e: Edge) = {
    val (start, end) = e
    start
  }
}