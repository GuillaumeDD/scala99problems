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
