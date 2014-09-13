package fr.dubuissonduplessis.graph.impl

import fr.dubuissonduplessis.graph.BaseGraphs

trait GraphsWithNoValue extends BaseGraphs {
  type NodeValue = Int
  def valueOf(n: Node): NodeValue = 0
}