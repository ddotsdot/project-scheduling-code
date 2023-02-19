package org.optsol.project_scheduling.mrcpsp.ooe.constraints;

import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import org.optsol.jdecor.core.AbstractConstraintManager;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.jdecor.ortools.AbstractOrtoolsConstraintManager;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_E_i;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_V;
import org.optsol.project_scheduling.mrcpsp.ooe.variables.DecisionVars_Ooe;

public class OOE_TWc_Makespan_LB<CONST extends IConstants_V & IConstants_E_i>
    extends AbstractOrtoolsConstraintManager<CONST> {

  @Override
  protected void configureConstraint(
      MPConstraint constraint,
      CONST constants,
      AbstractVariableManager<MPSolver, MPVariable> variables,
      AbstractConstraintManager<MPSolver, CONST, MPVariable, MPConstraint>.ConstraintKey constraintKey)
      throws Exception {

    constraint.setLb(constants.get_E_i(constants.get_A_bar()));
    constraint.setCoefficient(variables.getVar(DecisionVars_Ooe.s, constants.get_card_A()), 1.);
  }
}
