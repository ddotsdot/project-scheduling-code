package org.optsol.project_scheduling.mrcpsp.constants.sub;

import java.util.HashMap;
import java.util.Map;
import org.optsol.jdecor.ortools.SolverEngine;
import org.optsol.project_scheduling.mrcpsp.cpm.MrcpspSolverFactory_Cpm_Fwd;
import org.optsol.project_scheduling.mrcpsp.cpm.MrcpspSolverFactory_Cpm_Fwd.MrcpspSolver_Cpm_Fwd;
import org.optsol.project_scheduling.mrcpsp.cpm.constants.Cpm_Constants;
import org.optsol.project_scheduling.mrcpsp.cpm.solution.ICpmFwd_Solution;

public interface IConstants_E_i extends
                                IConstants_V, IConstants_M_i, IConstants_P, IConstants_p_im {

  Map<Integer, Integer> E_i = new HashMap<>();

  default Map<Integer, Integer> compute_E_i() {

    Cpm_Constants constants =
        new Cpm_Constants(
            get_card_A(),
            get_M_i(),
            get_P_bar(),
            get_p_im());

    MrcpspSolver_Cpm_Fwd solverCpmFwd =
        MrcpspSolverFactory_Cpm_Fwd.createMrcpspSolver_Cpm_Fwd(SolverEngine.GLOP);

    ICpmFwd_Solution solution = null;
    try {
      solution = solverCpmFwd.generateSolution(constants);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return MrcpspSolverFactory_Cpm_Fwd.createMapFromSolution(solution, constants);
  }

  default Map<Integer, Integer> get_E_i() {
    if (E_i.isEmpty()) {
      E_i.putAll(compute_E_i());
    }
    return E_i;
  }

  default double get_E_i(int i) {
    if (i == get_dummy_activity_project_start()) {
      return 0.;
    }

    if (!get_E_i().containsKey(i) || get_E_i().get(i) == null) {
      return 0.;
    }
    return get_E_i().get(i);
  }
}
