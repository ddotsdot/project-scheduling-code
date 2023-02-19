package org.optsol.project_scheduling.mrcpsp.fct.constraints.strong;

import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_M_i;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_R;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_V;
import org.optsol.project_scheduling.mrcpsp.fct.constants.sub.IConstants_b_tilde_imk;
import org.optsol.project_scheduling.mrcpsp.fct.variables.DecisionVars_Fct;

public class FCT_f3_FlowUpperBound_Strong_gamma<CONST
    extends IConstants_V & IConstants_R & IConstants_M_i & IConstants_b_tilde_imk>
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

    constraint.setCoefficient(variables.getVar(DecisionVars_Fct.gamma, i, j, k), 1.);

    for (int m : constants.get_M_i(j)) {
      constraint.setCoefficient(
          variables.getVar(DecisionVars_Fct.x, j, m),
          -constants.get_b_tilde_imk(j, m, k));
    }
  }
}
