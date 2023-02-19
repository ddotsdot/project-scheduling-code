package org.optsol.project_scheduling.mrcpsp.constants.sub;

import java.util.HashMap;
import java.util.Map;
import org.optsol.jdecor.ortools.SolverEngine;
import org.optsol.project_scheduling.mrcpsp.constants.shared.IConstants_MakespanUpperBound;
import org.optsol.project_scheduling.mrcpsp.cpm.MrcpspSolverFactory_Cpm_Bwd;
import org.optsol.project_scheduling.mrcpsp.cpm.MrcpspSolverFactory_Cpm_Bwd.MrcpspSolver_Cpm_Bwd;
import org.optsol.project_scheduling.mrcpsp.cpm.constants.Cpm_Constants;
import org.optsol.project_scheduling.mrcpsp.cpm.solution.ICpmBwd_Solution;

public interface IConstants_L_i extends
    IConstants_V, IConstants_M_i, IConstants_P, IConstants_p_im, IConstants_MakespanUpperBound {

  Map<Integer, Integer> L_i = new HashMap<>();

  default Map<Integer, Integer> compute_L_i() {

    Cpm_Constants constants =
        new Cpm_Constants(
            get_card_A(),
            get_M_i(),
            get_P_bar(),
            get_p_im());

    MrcpspSolver_Cpm_Bwd solverCpmBwd =
        MrcpspSolverFactory_Cpm_Bwd.createMrcpspSolver_Cpm_Bwd(SolverEngine.GLOP);

    ICpmBwd_Solution solution = null;
    try {
      solution = solverCpmBwd.generateSolution(constants);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return MrcpspSolverFactory_Cpm_Bwd.createMapFromSolution(solution, constants);
  }

  default Map<Integer, Integer> get_L_i() {
    if (L_i.isEmpty()) {
      L_i.putAll(compute_L_i());
    }
    return L_i;
  }

  default double get_L_i(int i) {
    if (i == get_dummy_activity_project_start()) {
      return 0.;
    }

    if (!get_L_i().containsKey(i) || get_L_i().get(i) == null) {
      return get_makespan_upperBound();
    }

    return get_L_i().get(i);
  }
}
