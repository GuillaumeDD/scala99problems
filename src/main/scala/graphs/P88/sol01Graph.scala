package graphs.P88

import fr.dubuissonduplessis.graph.impl.graph

class sol01Graph extends P88Graph
  with graph.DefaultGraphs {
  def splitGraph(g: Graph): List[Graph] =
    g.connectedComponents
}