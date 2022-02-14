package org.optsol.project_scheduling.solver;

import org.optsol.jdecor.core.ISolution;

public interface Solution
    extends ISolution {
  Integer[] get_x();
}
