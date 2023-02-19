package org.optsol.project_scheduling.mrcpsp.constants.sub;

import java.util.Set;
import java.util.stream.Collectors;
import org.jgrapht.alg.TransitiveClosure;
import org.jgrapht.graph.DirectedAcyclicGraph;
import org.optsol.project_scheduling.utils.Precedence;
import org.optsol.project_scheduling.utils.graph.PrecedenceEdge;
import org.optsol.project_scheduling.utils.graph.PrecedenceGraphBuilder;

public interface IConstants_H_G extends IConstants_V, IConstants_P {

  default Set<Precedence> get_H_G() {
    DirectedAcyclicGraph<Integer, PrecedenceEdge> dag =
        PrecedenceGraphBuilder.buildDAG(get_V(), get_P());

    TransitiveClosure.INSTANCE.closeDirectedAcyclicGraph(dag);

    return
        dag.edgeSet()
            .stream()
            .map(PrecedenceEdge::getPrecedence)
            .collect(Collectors.toSet());
  }
}
