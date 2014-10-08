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
package graphs.P82

import util.ExerciseTemplate
import fr.dubuissonduplessis.graph.Digraphs
import fr.dubuissonduplessis.graph.impl.digraph

trait P82Digraph extends ExerciseTemplate with Digraphs with digraph.EdgesAsPairs {
  /*
    P82 (*) Cycle from a given node.
    Write a method named findCycles to find closed paths (cycles) starting at a given node in a graph. The method should return all cycles.

    scala> Graph.fromString("[b-c, f-c, g-h, d, f-b, k-f, h-g]").findCycles("f")
    res0: List[List[String]] = List(List(f, c, b, f), List(f, b, c, f))
 */
  val name = "P82 (Cycle from a given node)"
  def findsCycles(graph: Digraph, start: Node): List[List[Node]]

  type Node = Char
  test("Invoking findsCycles with the same start and end node should not return a cycle") {
    val graph = newGraph(Set('a'), Set())
    assert(findsCycles(graph, 'a') == List())
  }

  test("Invoking findsCycles should return all the cycles starting at a given node") {
    /*
       *                 _________ 
       *                /         _\
       *       a  ->  b  ->  c  ->  d
       *       ^____________/      /
       *       ^__________________/
       */
    val graph = newGraph(Set('a', 'b', 'c', 'd'), Set(('a', 'b'), ('b', 'c'), ('b', 'd'), ('c', 'd'), ('c', 'a'), ('d', 'a')))

    val cycles = findsCycles(graph, 'b')
    assert(cycles.size == 3)
    assert(cycles.contains(List('b', 'd', 'a', 'b')))
    assert(cycles.contains(List('b', 'c', 'd', 'a', 'b')))
    assert(cycles.contains(List('b', 'c', 'a', 'b')))
  }
}
