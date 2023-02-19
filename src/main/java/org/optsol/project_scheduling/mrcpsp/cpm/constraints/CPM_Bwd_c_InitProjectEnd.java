package org.optsol.project_scheduling.mrcpsp.cpm.constraints;

import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import org.optsol.jdecor.core.AbstractConstraintManager;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.jdecor.ortools.AbstractOrtoolsConstraintManager;
import org.optsol.project_scheduling.mrcpsp.constants.shared.IConstants_MakespanUpperBound;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_V;
import org.optsol.project_scheduling.mrcpsp.cpm.variables.DecisionVars_Cpm_Bwd;

public class CPM_Bwd_c_InitProjectEnd<CONST extends IConstants_V & IConstants_MakespanUpperBound>
    extends AbstractOrtoolsConstraintManager<CONST> {

  @Override
  protected void configureConstraint(
      MPConstraint constraint,
      CONST constants,
      AbstractVariableManager<MPSolver, MPVariable> variables,
      AbstractConstraintManager<MPSolver, CONST, MPVariable, MPConstraint>.ConstraintKey index)
      throws Exception {

    constraint.setLb(constants.get_makespan_upperBound());
    constraint.setUb(constants.get_makespan_upperBound());

    constraint.setCoefficient(
        variables.getVar(DecisionVars_Cpm_Bwd.c, constants.get_A_bar()),
        1.);
  }
}
