package org.optsol.project_scheduling.mrcpsp.fct.constraints.ozon;

import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_V;
import org.optsol.project_scheduling.mrcpsp.fct.constants.sub.IConstants_Ws_ij;
import org.optsol.project_scheduling.mrcpsp.fct.variables.DecisionVars_Fct;

public class FCT_c2_StartingTimesDef_Ws<CONST extends IConstants_V & IConstants_Ws_ij>
    extends FCT_c0_StartingTimesDefGroup<CONST> {

  @Override
  protected void configureConstraint(
      MPConstraint constraint,
      CONST constants,
      AbstractVariableManager<MPSolver, MPVariable> variables,
      ConstraintKey index) throws Exception {

    int i = index.get("i");
    int j = index.get("j");

    constraint.setUb(0);

    constraint.setCoefficient(variables.getVar(DecisionVars_Fct.delta, i, j), 1.);
    constraint.setCoefficient(
        variables.getVar(DecisionVars_Fct.y, i, j),
        -constants.get_Ws_ij(i, j));
  }
}
