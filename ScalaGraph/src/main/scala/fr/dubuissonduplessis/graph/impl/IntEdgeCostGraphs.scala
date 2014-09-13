package fr.dubuissonduplessis.graph.impl

import scala.collection.immutable.Set
import fr.dubuissonduplessis.graph.BaseGraphs

trait IntEdgeCostGraphs extends BaseGraphs {
  type EdgeCost = Int

  def nullCost(): Int =
    0
  def addsUp(cost1: Int, cost2: Int): Int =
    cost1 + cost2
}
