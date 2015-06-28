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

import util.ExerciseTemplate
import fr.dubuissonduplessis.graph.Digraphs
import fr.dubuissonduplessis.graph.impl.digraph.EdgesAsPairs

trait P89Digraph extends ExerciseTemplate
  with Digraphs
  with EdgesAsPairs {
  /*
    P89 (**) Bipartite graphs.
    Write a function that determines whether a given graph is bipartite. 
   */
  val name = "P89 (Bipartite graph)"
  def isBipartite(g: Digraph): Boolean

  type Node = Char
  test("Invoking isBipartite should determine if the graph is bipartite or not") {

    /*
       * Empty graph
       */
    val g1 = newGraph(Set(), Set())
    assert(isBipartite(g1))

    /*
       *   d    b
       *   + -> +
       *       /|
       *      /
       *     /
       *   + -> +
       *   a    c
       */
    val g2 = newGraph(Set('a', 'b', 'c', 'd'), Set(('a', 'b'), ('a', 'c'), ('d', 'b')))
    assert(isBipartite(g2))

    /*
       *   d    b
       *   +    +
       *       /|
       *      /
       *     /
       *   + -> +
       *   a    c
       */
    val g3 = newGraph(Set('a', 'b', 'c', 'd'), Set(('a', 'b'), ('a', 'c')))
    assert(isBipartite(g3))

    /*
       *  a    b    c   d
       *  + -> + <- +   +
       *  
       *  e    f    g   h
       *  + -> + <- +   +
       *   \_______/|
       */
    val g4 = newGraph(Set('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'),
      Set(('a', 'b'), ('c', 'd'), ('e', 'f'), ('g', 'f'), ('e', 'g')))
    assert(!isBipartite(g4))
  }
}
