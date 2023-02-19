package org.optsol.project_scheduling.mrcpsp.cpm;

import java.util.Map;
import java.util.stream.Collectors;
import org.optsol.jdecor.ortools.OrtoolsSolver;
import org.optsol.jdecor.ortools.SolverEngine;
import org.optsol.project_scheduling.mrcpsp.cpm.constants.Cpm_Constants;
import org.optsol.project_scheduling.mrcpsp.cpm.constants.ICpmFwd_Constants;
import org.optsol.project_scheduling.mrcpsp.cpm.models.MrcpspModel_Cpm_Fwd;
import org.optsol.project_scheduling.mrcpsp.cpm.solution.ICpmFwd_Solution;

public class MrcpspSolverFactory_Cpm_Fwd {

  public static MrcpspSolver_Cpm_Fwd createMrcpspSolver_Cpm_Fwd(SolverEngine solverEngine) {
    return new MrcpspSolver_Cpm_Fwd(solverEngine);
  }

  public static Map<Integer, Integer> createMapFromSolution(
      ICpmFwd_Solution solution,
      Cpm_Constants constants) {

    return constants.get_V().stream()
        .mapToInt(i -> i)
        .boxed()
        .collect(Collectors.toMap(
            i -> i,
            i -> (int) Math.round(solution.get_s()[i]), (a, b) -> b));
  }

  public static class MrcpspSolver_Cpm_Fwd extends
      OrtoolsSolver<ICpmFwd_Constants, ICpmFwd_Solution> {

    private MrcpspSolver_Cpm_Fwd(SolverEngine solverEngine) {
      super(
          new MrcpspModel_Cpm_Fwd<>(solverEngine),
          ICpmFwd_Solution.class);
    }
  }
}
