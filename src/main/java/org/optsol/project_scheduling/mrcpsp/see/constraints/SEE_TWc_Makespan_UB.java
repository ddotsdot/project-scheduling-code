package org.optsol.project_scheduling.mrcpsp.see.constraints;

import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.jdecor.ortools.AbstractOrtoolsConstraintManager;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_A;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_L_i;
import org.optsol.project_scheduling.mrcpsp.see.variables.DecisionVars_See;

public class SEE_TWc_Makespan_UB<CONST
    extends IConstants_A & IConstants_L_i>
    extends AbstractOrtoolsConstraintManager<CONST> {


  @Override
  protected void configureConstraint(
      MPConstraint constraint,
      CONST constants,
      AbstractVariableManager<MPSolver, MPVariable> variables,
      ConstraintKey index)
      throws Exception {

    constraint.setUb(constants.get_L_i(constants.get_A_bar()));
    constraint.setCoefficient(variables.getVar(DecisionVars_See.s, constants.get_card_A()), 1);
  }
}
