package org.optsol.project_scheduling.mrcpsp.cpm;

import java.util.Map;
import java.util.stream.Collectors;
import org.optsol.jdecor.ortools.OrtoolsSolver;
import org.optsol.jdecor.ortools.SolverEngine;
import org.optsol.project_scheduling.mrcpsp.cpm.constants.Cpm_Constants;
import org.optsol.project_scheduling.mrcpsp.cpm.constants.ICpmBwd_Constants;
import org.optsol.project_scheduling.mrcpsp.cpm.models.MrcpspModel_Cpm_Bwd;
import org.optsol.project_scheduling.mrcpsp.cpm.solution.ICpmBwd_Solution;

public class MrcpspSolverFactory_Cpm_Bwd {

  public static MrcpspSolver_Cpm_Bwd createMrcpspSolver_Cpm_Bwd(SolverEngine solverEngine) {
    return new MrcpspSolver_Cpm_Bwd(solverEngine);
  }

  public static Map<Integer, Integer> createMapFromSolution(
      ICpmBwd_Solution solution,
      Cpm_Constants constants) {

    return
        constants.get_V().stream()
            .mapToInt(i -> i)
            .boxed()
            .collect(Collectors.toMap(
                i -> i,
                i -> (int) Math.round(solution.get_c()[i]), (a, b) -> b));
  }

  public static class MrcpspSolver_Cpm_Bwd extends
      OrtoolsSolver<ICpmBwd_Constants, ICpmBwd_Solution> {

    private MrcpspSolver_Cpm_Bwd(SolverEngine solverEngine) {
      super(
          new MrcpspModel_Cpm_Bwd<>(solverEngine),
          ICpmBwd_Solution.class);
    }
  }
}
