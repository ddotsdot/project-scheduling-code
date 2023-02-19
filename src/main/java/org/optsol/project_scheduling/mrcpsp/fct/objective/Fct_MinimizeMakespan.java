package org.optsol.project_scheduling.mrcpsp.fct.objective;

import com.google.ortools.linearsolver.MPObjective;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.jdecor.ortools.AbstractOrtoolsObjectiveManager;
import org.optsol.project_scheduling.mrcpsp.fct.constants.IFctModel_Constants;
import org.optsol.project_scheduling.mrcpsp.fct.variables.DecisionVars_Fct;

public class Fct_MinimizeMakespan extends AbstractOrtoolsObjectiveManager<IFctModel_Constants> {

  @Override
  protected void configureObjective(MPObjective objective,
      IFctModel_Constants constants,
      AbstractVariableManager<MPSolver, MPVariable> variables) throws Exception {
    objective.setMinimization();
    objective.setCoefficient(variables.getVar(DecisionVars_Fct.s, constants.get_A_bar()), 1.);
  }
}
