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

import util.ExerciseTemplate
import fr.dubuissonduplessis.graph.Graphs
import fr.dubuissonduplessis.graph.impl.graph.EdgesAsPairs

trait P83Graph extends ExerciseTemplate with Graphs with EdgesAsPairs {
  /*
	P83 (**) Construct all spanning trees.
    Write a method spanningTrees to construct all spanning trees of a given graph. 
    With this method, find out how many spanning trees there are for the graph depicted to the right. The data of this example graph can be found below. When you have a correct solution for the spanningTrees method, use it to define two other useful methods: isTree and isConnected. 
    Both are five-minute tasks! 
  */
  val name = "P83 (Construct all spanning trees)"
  def spanningForests(g: Graph): Set[Graph]
  def isConnected(g: Graph): Boolean
  def isTree(g: Graph): Boolean

  type Node = Char
  test("Invoking spanningForests on a graph should return all the spanning trees of a graph ; isForest, isConnected and isTree should return the right value.") {
    /*
       * TEST 01
       *  a    b    c
       */
    val graph01 = newGraph(Set('a', 'b', 'c'), Set())

    val spanningForests01 = graph01.spanningForests
    assert(spanningForests01.size == 1)
    assert(spanningForests01.contains(newGraph(Set('a', 'b', 'c'), Set())))
    assert(graph01.isForest)
    assert(!graph01.isConnected)
    assert(!graph01.isTree)

    /*
       * TEST 02
       *  a -- b -- c
       */
    val graph02 = newGraph(Set('a', 'b', 'c'), Set(('a', 'b'), ('b', 'c')))
    val solution02 = newGraph(Set('a', 'b', 'c'), Set(('a', 'b'), ('b', 'c')))
    val spanningForests02 = graph02.spanningForests
    assert(spanningForests02.size == 1)
    assert(spanningForests02.contains(solution02))
    assert(graph02.isForest)
    assert(graph02.isConnected)
    assert(graph02.isTree)

    /*
       * TEST 03
       *  a -- b
       *   \  /
       *    c
       */
    val graph03 = newGraph(Set('a', 'b', 'c'), Set(('a', 'b'), ('b', 'c'), ('a', 'c')))
    /*
       *  a -- b
       *      /
       *    c
       */
    val solution03_a = newGraph(Set('a', 'b', 'c'), Set(('a', 'b'), ('b', 'c')))
    /*
       *  a -- b
       *   \   
       *    c
       */
    val solution03_b = newGraph(Set('a', 'b', 'c'), Set(('a', 'b'), ('a', 'c')))
    /*
       *  a    b
       *   \  /
       *    c
       */
    val solution03_c = newGraph(Set('a', 'b', 'c'), Set(('a', 'b'), ('c', 'b')))
    val spanningForests03 = graph03.spanningForests
    assert(spanningForests03.size == 3)
    assert(spanningForests03.contains(solution03_a))
    assert(spanningForests03.contains(solution03_b))
    assert(spanningForests03.contains(solution03_c))
    assert(!graph03.isForest)
    assert(graph03.isConnected)
    assert(!graph03.isTree)

    /*
       * TEST 04
       *  a -- b      d -- e
       *   \  /
       *    c
       */
    val graph04 = newGraph(
      Set('a', 'b', 'c', 'd', 'e'),
      Set(('a', 'b'), ('b', 'c'), ('a', 'c'), ('d', 'e')))
    /*
       *  a -- b      d -- e
       *      /
       *    c
       */
    val solution04_a = newGraph(
      Set('a', 'b', 'c', 'd', 'e'),
      Set(('a', 'b'), ('b', 'c'), ('d', 'e')))
    /*
       *  a -- b      d -- e
       *   \   
       *    c
       */
    val solution04_b = newGraph(
      Set('a', 'b', 'c', 'd', 'e'),
      Set(('a', 'b'), ('a', 'c'), ('d', 'e')))
    /*
       *  a    b      d -- e
       *   \  /
       *    c
       */
    val solution04_c = newGraph(
      Set('a', 'b', 'c', 'd', 'e'),
      Set(('a', 'b'), ('c', 'b'), ('d', 'e')))
    val spanningForests04 = graph04.spanningForests
    assert(spanningForests04.size == 3)
    assert(spanningForests04.contains(solution04_a))
    assert(spanningForests04.contains(solution04_b))
    assert(spanningForests04.contains(solution04_c))
    assert(!graph04.isForest)
    assert(!graph04.isConnected)
    assert(!graph04.isTree)
  }
}
