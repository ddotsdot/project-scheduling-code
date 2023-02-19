package org.optsol.project_scheduling.mrcpsp.ctab.objective;

import com.google.ortools.linearsolver.MPObjective;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.jdecor.ortools.AbstractOrtoolsObjectiveManager;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_V;
import org.optsol.project_scheduling.mrcpsp.see.variables.DecisionVars_See;

public class Ctab_MinimizeMakespan<CONST extends IConstants_V>
    extends AbstractOrtoolsObjectiveManager<CONST> {

  @Override
  protected void configureObjective(
      MPObjective objective,
      CONST constants,
      AbstractVariableManager<MPSolver, MPVariable> variables) throws Exception {
    objective.setMinimization();
    objective.setCoefficient(variables.getVar(DecisionVars_See.s, constants.get_A_bar()), 1);
  }
}
