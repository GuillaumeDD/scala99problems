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
import fr.dubuissonduplessis.graph.impl.digraph._

class DigraphsTest extends FunSuite {
  trait Basics {
    object SimpleDigraphModel extends DefaultDigraphs {
      type Node = Char
    }

    /*
       *                 _________ 
       *                /         _\
       *       a  ->  b  ->  c  ->  d
       *       ^____________/      /
       *       ^__________________/
       */
    val graph = SimpleDigraphModel.newGraph(Set('a', 'b', 'c', 'd'), Set(('a', 'b'), ('b', 'c'), ('b', 'd'), ('c', 'd'), ('c', 'a'), ('d', 'a')))
  }

  test("Invoking findPaths on a digraph should return all the path from a starting node to another") {
    new Basics {
      val paths = graph.findPaths('a', 'd')
      assert(paths.size == 2)
      assert(paths.contains(List('a', 'b', 'd')))
      assert(paths.contains(List('a', 'b', 'c', 'd')))
    }
  }

  test("Invoking findShortestPath on a digraph should return a shortest path from a starting node to another, if it exists") {
    new Basics {
      val paths1 = graph.findShortestPath('a', 'd')
      assert(paths1.isDefined)
      assert(paths1.get == (List('a', 'b', 'd'), 2))

      val paths2 = graph.findShortestPath('b', 'a')
      assert(paths2.isDefined)
      assert(paths2.get == (List('b', 'c', 'a'), 2) ||
        paths2.get == (List('b', 'd', 'a'), 2))
    }
  }

  test("Invoking findCycles on a digraph should return all the cycles cycling on a node") {
    new Basics {
      val cycles = graph.findCycles('b')
      assert(cycles.size == 3)
      assert(cycles.contains(List('b', 'd', 'a', 'b')))
      assert(cycles.contains(List('b', 'c', 'd', 'a', 'b')))
      assert(cycles.contains(List('b', 'c', 'a', 'b')))
    }
  }

  test("Invoking isIsomorphic on a digraph should return if the graphs are isomorphic") {
    new Basics {
      import SimpleDigraphModel._
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
       *  	+ --> +
       *    
       *    c     d
       *    + <-- +
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
       *  	+ --> +
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
       *  	+ --> +
       *    ^     ^
       *    |     |
       *    c     d
       *    +     +
       *
       * 
       *   	e     f
       *  	+ --> +
       *    ^     |
       *    |     |
       *    g     h
       *    +     + 
       */
      val g5_a = newGraph(
        Set('a', 'b', 'c', 'd'),
        Set(('a', 'b'), ('c', 'a'), ('d', 'b')))
      val g5_b = newGraph(
        Set('e', 'f', 'g', 'h'),
        Set(('g', 'e'), ('e', 'f'), ('f', 'h')))
      assert(!g5_a.isIsomorphicTo(g5_b))
      assert(!g5_b.isIsomorphicTo(g5_a))

      /*
       * 4-node graphs (isomorphic)
       * 
       * 	a     b
       *  	+ --> +
       *    ^     ^
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
       *    + <-- + 
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
}
