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
package graphs.P87

import util.ExerciseTemplate
import fr.dubuissonduplessis.graph.Digraphs
import fr.dubuissonduplessis.graph.impl.digraph

trait P87Digraph extends ExerciseTemplate
  with Digraphs
  with digraph.EdgesAsPairs {
  /*
    P87 (**) Depth-first order graph traversal.
    Write a method that generates a depth-first order graph traversal sequence.
    The starting point should be specified, and the output should be a list of nodes 
    that are reachable from this starting point (in depth-first order).  
   */
  val name = "P87 (Depth-first order graph traversal)"
  def nodesByDepthFrom(g: Digraph, startingNode: Node): List[Node]

  type Node = Char

  test("Invoking nodesByDepthFrom should return a list of nodes") {
    /*
       * Empty graphs
       */
    val g1 = newGraph(Set(), Set())
    assert(nodesByDepthFrom(g1, '_') == List())

    /*
       *    a
       *    +
       *
       */
    val g2 = newGraph(Set('a'), Set())
    assert(nodesByDepthFrom(g2, 'a') == List('a'))

    /*
       *    a      b      c
       *    + ---> + ---> +
       *            \
       *             ---> +
       *                  d
       */
    val g3 = newGraph(Set('a', 'b', 'c', 'd'), Set(('a', 'b'), ('b', 'c'), ('b', 'd')))
    assert(nodesByDepthFrom(g3, 'a') == List('d', 'c', 'b', 'a') ||
      nodesByDepthFrom(g3, 'a') == List('c', 'd', 'b', 'a'))
  }
}
