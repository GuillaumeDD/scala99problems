package graphs.P82

import util.ExerciseTemplate
import fr.dubuissonduplessis.graph.Graphs
import fr.dubuissonduplessis.graph.impl.graph

trait P82Graph extends ExerciseTemplate with Graphs with graph.EdgesAsPairs {
  /*
    P82 (*) Cycle from a given node.
    Write a method named findCycles to find closed paths (cycles) starting at a given node in a graph. The method should return all cycles.

    scala> Graph.fromString("[b-c, f-c, g-h, d, f-b, k-f, h-g]").findCycles("f")
    res0: List[List[String]] = List(List(f, c, b, f), List(f, b, c, f))
 */
  val name = "P82 (Cycle from a given node)"
  def findsCycles(graph: Graph, start: Node, end: Node): List[List[Node]]

  type Node = Char
  test("Invoking findsCycles with the same start and end node should not return a cycle") {
    val graph = newGraph(Set('a'), Set())
    assert(findsCycles(graph, 'a', 'a') == List())
  }

  test("Invoking findsCycles should return all the cycles starting at a given node") {
    /*
       *                 _________ 
       *                /          \
       *       a  --  b  --  c  --  d
       *       |____________/      /
       *       |__________________/
       */
    val graph = newGraph(Set('a', 'b', 'c', 'd'), Set(('a', 'b'), ('b', 'c'), ('b', 'd'), ('c', 'd'), ('c', 'a'), ('d', 'a')))

    val cycles = findsCycles(graph, 'a', 'd')
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
