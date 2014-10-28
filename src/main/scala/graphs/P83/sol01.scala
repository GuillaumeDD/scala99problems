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
package graphs.P83

import fr.dubuissonduplessis.graph.impl._

class sol01 extends P83Graph with graph.DefaultGraphs {
  def spanningForests(g: Graph): Set[Graph] =
    g.spanningForests
  def isConnected(g: Graph): Boolean =
    g.isConnected
  def isTree(g: Graph): Boolean =
    g.isTree
}
