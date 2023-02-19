package org.optsol.project_scheduling.utils.graph;

import java.util.List;
import java.util.Set;
import org.jgrapht.graph.DirectedAcyclicGraph;
import org.optsol.project_scheduling.utils.Precedence;

public class PrecedenceGraphBuilder {

  public static DirectedAcyclicGraph<Integer, PrecedenceEdge> buildDAG(
      List<Integer> nodes,
      Set<Precedence> arcs) {

    DirectedAcyclicGraph<Integer, PrecedenceEdge> graph =
        new DirectedAcyclicGraph<>(PrecedenceEdge.class);

    nodes.forEach(graph::addVertex);

    arcs.forEach(precedence -> graph.addEdge(precedence.get_i(), precedence.get_j()));

    return graph;
  }
}
