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

import scala.collection.immutable.Set
import fr.dubuissonduplessis.graph.BaseGraphs

trait IntEdgeCostGraphs extends BaseGraphs {
  type EdgeCost = Int

  def nullCost(): Int =
    0
  def addsUp(cost1: Int, cost2: Int): Int =
    cost1 + cost2
}
