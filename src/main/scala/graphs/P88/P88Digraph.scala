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
package graphs.P88

import util.ExerciseTemplate
import fr.dubuissonduplessis.graph.Digraphs
import fr.dubuissonduplessis.graph.impl.digraph

trait P88Digraph extends ExerciseTemplate
  with Digraphs
  with digraph.EdgesAsPairs {
  /*
	 P88 (**) Connected components.
     Write a function that splits a graph into its connected components. 
   */
  val name = "P88 (Connected components)"
  def splitGraph(g: Digraph): List[Digraph]

  type Node = Char
  test("Invoking splitGraph should return a list of components") {
    /*
       * Empty graphs
       */
    val g1 = newGraph(Set(), Set())
    assert(splitGraph(g1) == List())

    /*
       *    a
       *    +
       *
       */
    val g2 = newGraph(Set('a'), Set())
    val g2_solution = newGraph(Set('a'), Set())
    assert(splitGraph(g2) == List(g2_solution))

    /*
     *                 _________ 
     *                /         _\
     *       a  ->  b  ->  c  ->  d
     *       ^____________/      /
     *       ^__________________/
     */
    val graph = newGraph(Set('a', 'b', 'c', 'd'), Set(('a', 'b'), ('b', 'c'), ('b', 'd'), ('c', 'd'), ('c', 'a'), ('d', 'a')))
    assert(splitGraph(graph) == List(graph))

    /*
       *    a    b
       *    +    +
       *
       */
    val g3 = newGraph(Set('a', 'b'), Set())
    val g3_solution_a = newGraph(Set('a'), Set())
    val g3_solution_b = newGraph(Set('b'), Set())
    val solution3 = List(g3_solution_a, g3_solution_b)
    assert(solution3.size == splitGraph(g3).size)
    assert(splitGraph(g3).forall(solution3.contains(_)))

    /*
       *    a     d     g
       *    + --> + <-- +
       *    
       *    + <-- + --> +
       *    b     e     h
       *
       *    + --> + --> +
       *    c     f     i
       *
       */
    val g4 = newGraph(Set('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i'),
      Set(('a', 'd'), ('d', 'g'),
        ('e', 'b'), ('e', 'h'),
        ('c', 'f'), ('f', 'i')))
    val g4_solution_a = newGraph(Set('a', 'd', 'g'), Set(('a', 'd'), ('d', 'g')))
    val g4_solution_b = newGraph(Set('b', 'e', 'h'), Set(('e', 'b'), ('e', 'h')))
    val g4_solution_c = newGraph(Set('c', 'f', 'i'), Set(('c', 'f'), ('f', 'i')))
    val solution4 = List(g4_solution_a, g4_solution_b, g4_solution_c)
    assert(solution4.size == splitGraph(g4).size)
    assert(splitGraph(g4).forall(solution4.contains(_)))
  }
}
