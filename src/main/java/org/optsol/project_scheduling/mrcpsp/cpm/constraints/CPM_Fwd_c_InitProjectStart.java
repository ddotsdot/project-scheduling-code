package org.optsol.project_scheduling.mrcpsp.cpm.constraints;

import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import org.optsol.jdecor.core.AbstractConstraintManager;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.jdecor.ortools.AbstractOrtoolsConstraintManager;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_V;
import org.optsol.project_scheduling.mrcpsp.cpm.variables.DecisionVars_Cpm_Fwd;

public class CPM_Fwd_c_InitProjectStart<CONST extends IConstants_V>
    extends AbstractOrtoolsConstraintManager<CONST> {

  @Override
  protected void configureConstraint(
      MPConstraint constraint,
      CONST constants,
      AbstractVariableManager<MPSolver, MPVariable> variables,
      AbstractConstraintManager<MPSolver, CONST, MPVariable, MPConstraint>.ConstraintKey index)
      throws Exception {

    constraint.setLb(0.);
    constraint.setUb(0.);

    constraint.setCoefficient(
        variables.getVar(DecisionVars_Cpm_Fwd.s, constants.get_dummy_activity_project_start()),
        1.);
  }
}
