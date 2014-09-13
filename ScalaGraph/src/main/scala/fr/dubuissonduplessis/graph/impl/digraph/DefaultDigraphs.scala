/**
 * *****************************************************************************
 * Copyright (c) 2014 Guillaume DUBUISSON DUPLESSIS <guillaume.dubuisson_duplessis@insa-rouen.fr>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 *
 * Contributors:
 *     Guillaume DUBUISSON DUPLESSIS <guillaume.dubuisson_duplessis@insa-rouen.fr> - initial API and implementation
 * ****************************************************************************
 */
package fr.dubuissonduplessis.graph.impl.digraph

import fr.dubuissonduplessis.graph.impl.UniformEdgeCostGraphs
import fr.dubuissonduplessis.graph.impl.GraphsWithNoValue

/**
 * Default digraph module implementation.
 * @author Guillaume DUBUISSON DUPLESSIS <guillaume.dubuisson_duplessis@insa-rouen.fr>
 *
 */
trait DefaultDigraphs extends SequentialDigraphsImpl
  with EdgesAsPairs
  with UniformEdgeCostGraphs
  with GraphsWithNoValue
