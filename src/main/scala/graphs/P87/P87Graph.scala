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
import fr.dubuissonduplessis.graph.Graphs
import fr.dubuissonduplessis.graph.impl.graph

trait P87Graph extends ExerciseTemplate
  with Graphs
  with graph.EdgesAsPairs {
  /*
    P87 (**) Depth-first order graph traversal.
    Write a method that generates a depth-first order graph traversal sequence.
    The starting point should be specified, and the output should be a list of nodes 
    that are reachable from this starting point (in depth-first order).  
   */
  val name = "P87 (Depth-first order graph traversal)"
  def nodesByDepthFrom(g: Graph, startingNode: Node): List[Node]

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
       *    a
       *    +
       *   / \
       *  + - +
       *  b   c
       *  
       */
    val g3 = newGraph(Set('a', 'b', 'c'), Set(('a', 'b'), ('a', 'c'), ('b', 'c')))
    assert(nodesByDepthFrom(g3, 'a') == List('c', 'b', 'a') ||
      nodesByDepthFrom(g3, 'a') == List('b', 'c', 'a'))
  }
}
