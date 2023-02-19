package org.optsol.project_scheduling.mrcpsp.cpm.objectives;

import com.google.ortools.linearsolver.MPObjective;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.jdecor.ortools.AbstractOrtoolsObjectiveManager;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_V;
import org.optsol.project_scheduling.mrcpsp.cpm.variables.DecisionVars_Cpm_Fwd;

public class Cpm_Fwd_MinimizeTotalEarliestStartTimes<CONST extends IConstants_V>
    extends AbstractOrtoolsObjectiveManager<CONST> {

  @Override
  protected void configureObjective(
      MPObjective objective,
      CONST constants,
      AbstractVariableManager<MPSolver, MPVariable> variables) throws Exception {

    objective.setMinimization();
    for (int i : constants.get_V()) {
      objective.setCoefficient(variables.getVar(DecisionVars_Cpm_Fwd.s, i), 1.);
    }
  }
}
