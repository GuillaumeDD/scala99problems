/*******************************************************************************
 * Copyright (c) 2015 Guillaume DUBUISSON DUPLESSIS <guillaume.dubuisson_duplessis@insa-rouen.fr>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     Guillaume DUBUISSON DUPLESSIS <guillaume.dubuisson_duplessis@insa-rouen.fr> - initial API and implementation
 ******************************************************************************/
package graphs.P89

import org.scalatest.Args
import fr.dubuissonduplessis.graph.impl.graph.DefaultGraphs

class sol01Graph extends P89Graph with DefaultGraphs {
  def isBipartite(g: Graph): Boolean =
    g.isBipartite
}
