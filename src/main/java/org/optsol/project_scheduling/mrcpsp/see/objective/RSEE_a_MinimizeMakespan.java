package org.optsol.project_scheduling.mrcpsp.see.objective;

import com.google.ortools.linearsolver.MPObjective;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.jdecor.ortools.AbstractOrtoolsObjectiveManager;
import org.optsol.project_scheduling.mrcpsp.see.constants.ISeeModel_Constants;
import org.optsol.project_scheduling.mrcpsp.see.variables.DecisionVars_Rsee;

public class RSEE_a_MinimizeMakespan extends AbstractOrtoolsObjectiveManager<ISeeModel_Constants> {

  @Override
  protected void configureObjective(MPObjective objective,
      ISeeModel_Constants constants,
      AbstractVariableManager<MPSolver, MPVariable> variables) throws Exception {

    objective.setMinimization();
    objective.setCoefficient(variables.getVar(DecisionVars_Rsee.s, constants.get_card_A()), 1);
  }
}
