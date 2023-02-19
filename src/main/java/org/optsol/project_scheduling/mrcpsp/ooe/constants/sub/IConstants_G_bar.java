package org.optsol.project_scheduling.mrcpsp.ooe.constants.sub;

import java.util.Set;
import org.jgrapht.graph.DirectedAcyclicGraph;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_A;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_P_bar;
import org.optsol.project_scheduling.utils.graph.PrecedenceEdge;
import org.optsol.project_scheduling.utils.graph.PrecedenceGraphBuilder;

public interface IConstants_G_bar extends IConstants_A, IConstants_P_bar {

  default Set<Integer> get_A_bar(int i) {
    DirectedAcyclicGraph<Integer, PrecedenceEdge> dag =
        PrecedenceGraphBuilder.buildDAG(get_A(), get_P_bar());

    return dag.getAncestors(i);
  }

  default Set<Integer> get_D_bar(int i) {
    DirectedAcyclicGraph<Integer, PrecedenceEdge> dag =
        PrecedenceGraphBuilder.buildDAG(get_A(), get_P_bar());

    return dag.getDescendants(i);
  }
}