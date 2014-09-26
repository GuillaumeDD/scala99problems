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
package fr.dubuissonduplessis.graph

import org.scalatest.FunSuite
import fr.dubuissonduplessis.graph.impl.graph.DefaultGraphs

class GraphsTest extends FunSuite {
  trait Basics {
    object SimpleGraphModel extends DefaultGraphs {
      type Node = Char
    }

    /*
       *                 _________ 
       *                /          \
       *       a  --  b  --  c  --  d
       *       |____________/      /
       *       |__________________/
       */
    val graph = SimpleGraphModel.newGraph(Set('a', 'b', 'c', 'd'), Set(('a', 'b'), ('b', 'c'), ('b', 'd'), ('c', 'd'), ('c', 'a'), ('d', 'a')))
  }

  test("Invoking findPaths on a graph should return all the path from a starting node to another") {
    new Basics {
      val paths = graph.findPaths('a', 'd')
      assert(paths.size == 5)
      assert(paths.contains(List('a', 'd')))
      assert(paths.contains(List('a', 'c', 'd')))
      assert(paths.contains(List('a', 'c', 'b', 'd')))
      assert(paths.contains(List('a', 'b', 'd')))
      assert(paths.contains(List('a', 'b', 'c', 'd')))
    }
  }

  test("Invoking findShortestPaths on a graph should return a shortest path from a starting node to another, if it exists") {
    new Basics {
      val paths1 = graph.findShortestPath('a', 'd')
      assert(paths1.isDefined)
      assert(paths1.get == (List('a', 'd'), 1))

      val paths2 = graph.findShortestPath('b', 'a')
      assert(paths2.isDefined)
      assert(paths2.get == (List('b', 'a'), 1))
    }
  }

  test("Invoking findCycles on a graph should return all the cycles cycling on a node") {
    new Basics {
      val cycles = graph.findCycles('b')
      assert(cycles.size == 15)
      assert(cycles.contains(List('b', 'a', 'c', 'd', 'b')))
      assert(cycles.contains(List('b', 'd', 'c', 'a', 'b')))
      assert(cycles.contains(List('b', 'a', 'c', 'b')))
      assert(cycles.contains(List('b', 'c', 'a', 'b')))
      assert(cycles.contains(List('b', 'a', 'b')))
      assert(cycles.contains(List('b', 'c', 'b')))
      assert(cycles.contains(List('b', 'd', 'b')))
      assert(cycles.contains(List('b', 'a', 'd', 'c', 'b')))
      assert(cycles.contains(List('b', 'c', 'd', 'a', 'b')))
      assert(cycles.contains(List('b', 'a', 'd', 'b')))
      assert(cycles.contains(List('b', 'd', 'a', 'b')))
      assert(cycles.contains(List('b', 'c', 'a', 'd', 'b')))
      assert(cycles.contains(List('b', 'd', 'a', 'c', 'b')))
      assert(cycles.contains(List('b', 'd', 'c', 'b')))
      assert(cycles.contains(List('b', 'c', 'd', 'b')))
    }
  }

  test("Invoking spanningForests on a graph should return all the spanning trees of a graph") {
    new Basics {
      import SimpleGraphModel._
      /*
       * TEST 01
       *  a    b    c
       */
      val graph01 = newGraph(Set('a', 'b', 'c'), Set())

      val spanningForests01 = graph01.spanningForests
      assert(spanningForests01.size == 1)
      assert(spanningForests01.contains(newGraph(Set('a', 'b', 'c'), Set())))

      /*
       * TEST 02
       *  a -- b -- c
       */
      val graph02 = newGraph(Set('a', 'b', 'c'), Set(('a', 'b'), ('b', 'c')))
      val solution02 = newGraph(Set('a', 'b', 'c'), Set(('a', 'b'), ('b', 'c')))
      val spanningForests02 = graph02.spanningForests
      assert(spanningForests02.size == 1)
      assert(spanningForests02.contains(solution02))

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

    }
  }
}
