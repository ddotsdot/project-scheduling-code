package org.optsol.project_scheduling.utils.graph;

import lombok.RequiredArgsConstructor;
import org.jgrapht.graph.DefaultEdge;
import org.optsol.project_scheduling.utils.Precedence;

@RequiredArgsConstructor
public class PrecedenceEdge extends DefaultEdge {

  public Precedence getPrecedence() {
    return new Precedence((int) getSource(), (int) getTarget());
  }
}
