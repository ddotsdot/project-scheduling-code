package org.optsol.project_scheduling.mrcpsp.ooe.objective;

import com.google.ortools.linearsolver.MPObjective;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.jdecor.ortools.AbstractOrtoolsObjectiveManager;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_A;
import org.optsol.project_scheduling.mrcpsp.ooe.variables.DecisionVars_Ooe;

public class OOE_a_MinimizeMakespan<CONST extends IConstants_A> extends
    AbstractOrtoolsObjectiveManager<CONST> {

  @Override
  protected void configureObjective(
      MPObjective objective,
      CONST constants,
      AbstractVariableManager<MPSolver, MPVariable> variables) throws Exception {
    objective.setMinimization();
    objective.setCoefficient(variables.getVar(DecisionVars_Ooe.s, constants.get_card_A()), 1);
  }
}
