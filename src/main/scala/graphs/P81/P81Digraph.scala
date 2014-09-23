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

import util.ExerciseTemplate
import fr.dubuissonduplessis.graph.Digraphs
import fr.dubuissonduplessis.graph.impl.digraph

trait P81Digraph extends ExerciseTemplate with Digraphs with digraph.EdgesAsPairs {
  /*
    P81 (**) Path from one node to another one.
    Write a method named findPaths to find acyclic paths from one node to another in a graph. The method should return all paths.

    scala> Digraph.fromStringLabel("[p>q/9, m>q/7, k, p>m/5]").findPaths("p", "q")
    res0: List[List[String]] = List(List(p, q), List(p, m, q))
        
    scala> Digraph.fromStringLabel("[p>q/9, m>q/7, k, p>m/5]").findPaths("p", "k")
    res1: List[List[String]] = List()
 */
  val name = "P81 (Path from one node to another one)"
  def findsPaths(graph: Digraph, start: Node, end: Node): List[List[Node]]

  type Node = Char
  test("Invoking findsPaths with the same start and end node should return one unique path") {
    val graph = newGraph(Set('a'), Set())
    assert(findsPaths(graph, 'a', 'a') == List(List('a')))
  }

  test("Invoking findsPaths should return all the paths from one node to another") {
    /*
       *                 _________ 
       *                /         _\
       *       a  ->  b  ->  c  ->  d
       *       ^____________/      /
       *       ^__________________/
       */
    val graph = newGraph(Set('a', 'b', 'c', 'd'), Set(('a', 'b'), ('b', 'c'), ('b', 'd'), ('c', 'd'), ('c', 'a'), ('d', 'a')))

    val paths = findsPaths(graph, 'a', 'd')
    assert(paths.size == 2)
    assert(paths.contains(List('a', 'b', 'd')))
    assert(paths.contains(List('a', 'b', 'c', 'd')))
  }

}
