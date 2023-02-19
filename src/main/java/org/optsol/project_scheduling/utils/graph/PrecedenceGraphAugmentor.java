package org.optsol.project_scheduling.utils.graph;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.jgrapht.graph.DirectedAcyclicGraph;
import org.optsol.project_scheduling.utils.Precedence;

public class PrecedenceGraphAugmentor {

  public static Set<Precedence> augmentDummyArcs(
      List<Integer> nonDummyActivities,
      Set<Precedence> precedenceRelations,
      int dummySource,
      int dummySink) {

    Set<Precedence> precedences = new HashSet<>(precedenceRelations);

    DirectedAcyclicGraph<Integer, PrecedenceEdge> dag =
        PrecedenceGraphBuilder.buildDAG(nonDummyActivities, precedenceRelations);

    List<Integer> nodesWithoutPredecessors = dag.vertexSet()
        .stream()
        .filter(i -> dag.getAncestors(i).isEmpty())
        .collect(Collectors.toList());

    nodesWithoutPredecessors
        .forEach(i -> precedences.add(new Precedence(dummySource, i)));

    List<Integer> nodesWithoutSuccessors = dag.vertexSet()
        .stream()
        .filter(i -> dag.getDescendants(i).isEmpty())
        .collect(Collectors.toList());

    nodesWithoutSuccessors
        .forEach(i -> precedences.add(new Precedence(i, dummySink)));

    return precedences;
  }
}
