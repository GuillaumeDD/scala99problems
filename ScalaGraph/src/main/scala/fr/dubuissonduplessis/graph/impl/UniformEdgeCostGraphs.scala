package fr.dubuissonduplessis.graph.impl

import fr.dubuissonduplessis.graph.BaseGraphs

/**
 * @author Guillaume DUBUISSON DUPLESSIS <guillaume.dubuisson_duplessis@insa-rouen.fr>
 *
 */
trait UniformEdgeCostGraphs extends BaseGraphs {
  type EdgeCost = Int
  def costOf(e: Edge): EdgeCost = 1
  def nullCost(): EdgeCost = 0
  def addsUp(cost1: EdgeCost, cost2: EdgeCost): EdgeCost =
    cost1 + cost2
}
