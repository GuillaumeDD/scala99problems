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
package graphs.P85

import fr.dubuissonduplessis.graph.impl._

class sol01Graph extends P85Graph with graph.DefaultGraphs {
  def areIsomorphic(g1: Graph, g2: Graph): Boolean =
    g1.isIsomorphicTo(g2)
}
