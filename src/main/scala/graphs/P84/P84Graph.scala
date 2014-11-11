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
package graphs.P84

import fr.dubuissonduplessis.graph.impl.graph.ValuedEdgesAsTriplet
import util.ExerciseTemplate
import fr.dubuissonduplessis.graph.Graphs

trait P84Graph extends ExerciseTemplate with Graphs with ValuedEdgesAsTriplet {
  /*
   P84 (**) Construct the minimal spanning tree.
   Write a method minimalSpanningTree to construct the minimal spanning tree of a given labeled graph. 
   Hint: Use Prim's Algorithm. 
 */
  val name = "P84 (Construct the minimal spanning tree)"
  def spanningTree(g: Graph): Graph
  type Node = Char

  // For tests, see ScalaGraph library.
}
