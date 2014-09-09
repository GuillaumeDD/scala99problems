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

  test("Invoking findsPaths on a graph should return all the path from a starting node to another") {
    new Basics {
      val paths = graph.findsPaths('a', 'd')
      assert(paths.size == 5)
      assert(paths.contains(List('a', 'd')))
      assert(paths.contains(List('a', 'c', 'd')))
      assert(paths.contains(List('a', 'c', 'b', 'd')))
      assert(paths.contains(List('a', 'b', 'd')))
      assert(paths.contains(List('a', 'b', 'c', 'd')))
    }
  }

  test("Invoking findsCycles on a graph should return all the cycles cycling on a node") {
    new Basics {
      val cycles = graph.findsCycles('b')
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
}
