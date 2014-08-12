package fr.dubuissonduplessis.graph

import org.scalatest.FunSuite
import fr.dubuissonduplessis.graph.impl.EdgesAsPairs
import fr.dubuissonduplessis.graph.impl.SequentialGraphsImpl

class GraphsTest extends FunSuite {
  trait Basics {
    object SimpleGraphModel extends SequentialGraphsImpl with EdgesAsPairs {
      type Node = Char
    }

    /*
       *                 _________ 
       *                /         _\
       *       a  ->  b  ->  c  ->  d
       *       ^____________/      /
       *       ^__________________/
       */
    val graph = SimpleGraphModel.newGraph(Set('a', 'b', 'c', 'd'), Set(('a', 'b'), ('b', 'c'), ('b', 'd'), ('c', 'd'), ('c', 'a'), ('d', 'a')))
  }

  test("Invoking findsPaths on a graph should return all the path from a starting node to another") {
    new Basics {
      val paths = graph.findsPaths('a', 'd')
      assert(paths.size == 2)
      assert(paths.contains(List('a', 'b', 'd')))
      assert(paths.contains(List('a', 'b', 'c', 'd')))
    }
  }

  test("Invoking findsCycles on a graph should return all the cycles cycling on a node") {
    new Basics {
      val cycles = graph.findsCycles('b')
      assert(cycles.size == 3)
      assert(cycles.contains(List('b', 'd', 'a', 'b')))
      assert(cycles.contains(List('b', 'c', 'd', 'a', 'b')))
      assert(cycles.contains(List('b', 'c', 'a', 'b')))
    }
  }
}