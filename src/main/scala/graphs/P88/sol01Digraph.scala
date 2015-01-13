package graphs.P88

import fr.dubuissonduplessis.graph.impl.digraph

class sol01Digraph extends P88Digraph
  with digraph.DefaultDigraphs {
  def splitGraph(g: Digraph): List[Digraph] =
    g.connectedComponents
}