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
package graphs.P86

import util.ExerciseTemplate
import fr.dubuissonduplessis.graph.Graphs
import fr.dubuissonduplessis.graph.impl.graph

trait P86 extends ExerciseTemplate
  with Graphs
  with graph.EdgesAsPairs {
  /*
    P86 (**) Node degree and graph coloration.
    a) Write a method Node.degree that determines the degree of a given node.

    scala> Graph.fromString("[a-b, b-c, a-c, a-d]").nodes("a").degree
    res0: Int = 3

    b) Write a method that lists all nodes of a graph sorted according to decreasing degree.

    scala> Graph.fromString("[a-b, b-c, a-c, a-d]").nodesByDegree
    res1: List[Graph[String,Unit]#Node] = List(Node(a), Node(c), Node(b), Node(d))

    c) Use Welsh-Powell's algorithm to paint the nodes of a graph in such a way that adjacent nodes have different colors. Make a method colorNodes that returns a list of tuples, each of which contains a node and an integer representing its color.

    scala> Graph.fromString("[a-b, b-c, a-c, a-d]").colorNodes
    res2: List[(Graph[String,Unit]#Node,Int)] = List((Node(a),1), (Node(b),2), (Node(c), 3), (Node(d), 2))
  */
  val name = "P86 (Node degree and graph coloration)"
  def coloredNodes(g: Graph): List[(Node, Int)]

  type Node = Char

  test("Invoking coloredNodes on a graph should return a list of tuples") {
    def numberOfDifferentColor(l: List[(Char, Int)]): Int =
      l.map(_._2).toSet.size

    /*
       * Empty graph
       */
    val g1 = newGraph(Set(), Set())
    assert(numberOfDifferentColor(coloredNodes(g1)) == 0)

    /*
       * One-node graph
       */
    val g2 = newGraph(Set('a'), Set())
    assert(numberOfDifferentColor(coloredNodes(g2)) == 1)

    /*
       * Two-node graph
       *  a    b
       *  + -- +
       */
    val g3 = newGraph(
      Set('a', 'b'),
      Set(('a', 'b')))
    assert(numberOfDifferentColor(coloredNodes(g3)) == 2)

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
    assert(numberOfDifferentColor(coloredNodes(g4)) == 3)

    /*
       * Three-node graph
       *  a    b    c
       *  + -- + -- +
       *  
       */
    val g5 = newGraph(
      Set('a', 'b', 'c'),
      Set(('a', 'b'), ('b', 'c')))
    assert(numberOfDifferentColor(coloredNodes(g5)) == 2)

    /*
       *                 _________ 
       *                /          \
       *       a  --  b  --  c  --  d
       *       |____________/      /
       *       |__________________/
       */
    val graph = newGraph(Set('a', 'b', 'c', 'd'), Set(('a', 'b'), ('b', 'c'), ('b', 'd'), ('c', 'd'), ('c', 'a'), ('d', 'a')))

    assert(numberOfDifferentColor(coloredNodes(graph)) == 4)
  }
}
