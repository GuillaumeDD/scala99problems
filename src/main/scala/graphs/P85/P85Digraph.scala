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
package graphs.P85

import util.ExerciseTemplate
import fr.dubuissonduplessis.graph.Digraphs
import fr.dubuissonduplessis.graph.impl.digraph

trait P85Digraph extends ExerciseTemplate
  with Digraphs
  with digraph.EdgesAsPairs {
  /*
	P85 (**) Graph isomorphism.
    Two graphs G1(N1,E1) and G2(N2,E2) are isomorphic if there is a bijection f: N1 â†’ N2 such that for any nodes X,Y of N1, X and Y are adjacent if and only if f(X) and f(Y) are adjacent.

    Write a method that determines whether two graphs are isomorphic.

    scala> Graph.fromString("[a-b]").isIsomorphicTo(Graph.fromString("[5-7]"))
    res0: Boolean = true
  */
  val name = "P85 (Graph isomorphism)"
  def areIsomorphic(g1: Digraph, g2: Digraph): Boolean

  type Node = Char

  test("Invoking isIsomorphic on a digraph should return if the graphs are isomorphic") {
    /*
       * Empty graphs (isomorphic)
       */
    val g1_a = newGraph(Set(), Set())
    val g1_b = newGraph(Set(), Set())
    assert(areIsomorphic(g1_a, (g1_b)))
    assert(areIsomorphic(g1_b, (g1_a)))

    /*
       * One-node graphs (isomorphic)
       */
    val g2_a = newGraph(Set('a'), Set())
    val g2_b = newGraph(Set('b'), Set())
    assert(areIsomorphic(g2_a, (g2_b)))
    assert(areIsomorphic(g2_b, (g2_a)))

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
    assert(areIsomorphic(g3_a, (g3_b)))
    assert(areIsomorphic(g3_b, (g3_a)))

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
    assert(!areIsomorphic(g4_a, (g4_b)))
    assert(!areIsomorphic(g4_b, (g4_a)))

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
    assert(!areIsomorphic(g5_a, (g5_b)))
    assert(!areIsomorphic(g5_b, (g5_a)))

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
    assert(areIsomorphic(g6_a, (g6_b)))
    assert(areIsomorphic(g6_b, (g6_a)))
  }
}
