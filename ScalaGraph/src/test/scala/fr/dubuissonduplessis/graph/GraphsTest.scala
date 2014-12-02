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
import fr.dubuissonduplessis.graph.impl.graph.DefaultValuedGraphs

class GraphsTest extends FunSuite {
  trait Basics {
    object SimpleGraphModel extends DefaultGraphs {
      type Node = Char
    }

    object SimpleValuedGraphModel extends DefaultValuedGraphs {
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

  test("Invoking spanningForests on a graph should return all the spanning trees of a graph ; isForest, isConnected and isTree should return the right value.") {
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

  test("Invoking minimumSpanningTree on a graph should return a minimum spanning tree of a graph.") {
    new Basics {
      import SimpleValuedGraphModel._
      val graph1 = newGraph(Set(), Set())
      val solution1 = newGraph(Set(), Set())
      assert(graph1.minimumSpanningTree == solution1)

      /*
       *     1
       *  a -- b
       * 2 \  / 4
       * 	c
       *  
       */
      val graph2 = newGraph(
        Set('a', 'b', 'c'),
        Set(('a', 'b', 1), ('a', 'c', 2), ('b', 'c', 4)))
      val solution2 = newGraph(
        Set('a', 'b', 'c'),
        Set(('a', 'b', 1), ('a', 'c', 2)))
      assert(graph2.minimumSpanningTree == solution2)
    }
  }

  test("Invoking isIsomorphic on a digraph should return if the graphs are isomorphic") {
    new Basics {
      import SimpleGraphModel._
      /*
       * Empty graphs (isomorphic)
       */
      val g1_a = newGraph(Set(), Set())
      val g1_b = newGraph(Set(), Set())
      assert(g1_a.isIsomorphicTo(g1_b))
      assert(g1_b.isIsomorphicTo(g1_a))

      /*
       * One-node graphs (isomorphic)
       */
      val g2_a = newGraph(Set('a'), Set())
      val g2_b = newGraph(Set('b'), Set())
      assert(g2_a.isIsomorphicTo(g2_b))
      assert(g2_b.isIsomorphicTo(g2_a))

      /*
       * Two-node graphs (isomorphic)
       * 
       * 	a     b
       *  	+ --- +
       *    
       *    c     d
       *    + --- +
       * 
       */
      val g3_a = newGraph(Set('a', 'b'), Set(('a', 'b')))
      val g3_b = newGraph(Set('c', 'd'), Set(('d', 'c')))
      assert(g3_a.isIsomorphicTo(g3_b))
      assert(g3_b.isIsomorphicTo(g3_a))

      /*
       * Two-node graphs (non-isomorphic)
       * 
       * 	a     b
       *  	+ --- +
       *    
       *    c     d
       *    +     +
       * 
       */
      val g4_a = newGraph(Set('a', 'b'), Set(('a', 'b')))
      val g4_b = newGraph(Set('c', 'd'), Set())
      assert(!g4_a.isIsomorphicTo(g4_b))
      assert(!g4_b.isIsomorphicTo(g4_a))

      /*
       * 4-node graphs (non-isomorphic)
       * 
       * 	a     b
       *  	+ --- +
       *    |     |
       *    |     |
       *    c     d
       *    +     +
       *
       * 
       *   	e     f
       *  	+ --- +
       *      
       *    g     h
       *    + --- + 
       */
      val g5_a = newGraph(
        Set('a', 'b', 'c', 'd'),
        Set(('a', 'b'), ('c', 'a'), ('d', 'b')))
      val g5_b = newGraph(
        Set('e', 'f', 'g', 'h'),
        Set(('g', 'h'), ('e', 'f')))
      assert(!g5_a.isIsomorphicTo(g5_b))
      assert(!g5_b.isIsomorphicTo(g5_a))

      /*
       * 4-node graphs (isomorphic)
       * 
       * 	a     b
       *  	+ --- +
       *    |     |
       *    |     |
       *    c     d
       *    +     +
       *
       * 
       *   	e     f
       *  	+     +
       *    |     |
       *    |     |
       *    g     h
       *    + --- + 
       */
      val g6_a = newGraph(
        Set('a', 'b', 'c', 'd'),
        Set(('a', 'b'), ('c', 'a'), ('d', 'b')))
      val g6_b = newGraph(
        Set('e', 'f', 'g', 'h'),
        Set(('e', 'g'), ('f', 'h'), ('h', 'g')))
      assert(g6_a.isIsomorphicTo(g6_b))
      assert(g6_b.isIsomorphicTo(g6_a))
    }
  }

  test("Invoking coloredNodes on a graph should return a list of tuples") {
    new Basics {
      import SimpleGraphModel._

      def numberOfDifferentColor(l: List[(Char, Int)]): Int =
        l.map(_._2).toSet.size

      /*
       * Empty graph
       */
      val g1 = newGraph(Set(), Set())
      assert(numberOfDifferentColor(g1.coloredNodes) == 0)

      /*
       * One-node graph
       */
      val g2 = newGraph(Set('a'), Set())
      assert(numberOfDifferentColor(g2.coloredNodes) == 1)

      /*
       * Two-node graph
       *  a    b
       *  + -- +
       */
      val g3 = newGraph(
        Set('a', 'b'),
        Set(('a', 'b')))
      assert(numberOfDifferentColor(g3.coloredNodes) == 2)

      /*
       * Three-node graph
       *  a    b
       *  + -- +
       *   \  /
       *    c
       */
      val g4 = newGraph(
        Set('a', 'b', 'c'),
        Set(('a', 'b'), ('a', 'c'), ('b', 'c')))
      assert(numberOfDifferentColor(g4.coloredNodes) == 3)

      /*
       * Three-node graph
       *  a    b    c
       *  + -- + -- +
       *  
       */
      val g5 = newGraph(
        Set('a', 'b', 'c'),
        Set(('a', 'b'), ('b', 'c')))
      assert(numberOfDifferentColor(g5.coloredNodes) == 2)

      /*
       * Standard example
       *  
       */
      assert(numberOfDifferentColor(graph.coloredNodes) == 4)

    }
  }
}
