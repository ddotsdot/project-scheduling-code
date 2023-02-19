package org.optsol.project_scheduling.mrcpsp.fct.constraints.strong;

import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_R;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_V;
import org.optsol.project_scheduling.mrcpsp.fct.variables.DecisionVars_Fct;

public class FCT_f1_FlowUpperBound_Strong_beta<CONST extends IConstants_V & IConstants_R>
    extends FCT_f0_FlowUpperBoundGroup_Strong<CONST> {

  @Override
  protected void configureConstraint(
      MPConstraint constraint,
      CONST constants,
      AbstractVariableManager<MPSolver, MPVariable> variables,
      ConstraintKey index) throws Exception {

    int i = index.get("i");
    int j = index.get("j");
    int k = index.get("k");

    constraint.setUb(0.);

    constraint.setCoefficient(variables.getVar(DecisionVars_Fct.f, i, j, k), 1.);
    constraint.setCoefficient(variables.getVar(DecisionVars_Fct.beta, i, j, k), -1.);
  }
}
