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
package graphs.P81

import fr.dubuissonduplessis.graph.impl.digraph.DefaultDigraphs

class sol01Digraph extends P81Digraph with DefaultDigraphs {
  def findsPaths(graph: Digraph, start: Node, end: Node): List[List[Node]] =
    graph.findPaths(start, end)
}
