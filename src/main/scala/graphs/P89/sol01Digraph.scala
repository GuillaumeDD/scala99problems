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
import fr.dubuissonduplessis.graph.impl.digraph.DefaultDigraphs

class sol01Digraph extends P89Digraph with DefaultDigraphs {
  def isBipartite(g: Digraph): Boolean =
    g.isBipartite
}
