package org.optsol.project_scheduling.mrcpsp.fct.constraints.ozon;

import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_M_i;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_V;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_p_im;
import org.optsol.project_scheduling.mrcpsp.fct.constants.sub.IConstants_W;
import org.optsol.project_scheduling.mrcpsp.fct.variables.DecisionVars_Fct;

public class FCT_c4_StartingTimesDef<CONST
    extends IConstants_V & IConstants_W & IConstants_M_i & IConstants_p_im>
    extends FCT_c0_StartingTimesDefGroup<CONST> {

  @Override
  protected void configureConstraint(
      MPConstraint constraint,
      CONST constants,
      AbstractVariableManager<MPSolver, MPVariable> variables,
      ConstraintKey index) throws Exception {

    int i = index.get("i");
    int j = index.get("j");

    constraint.setLb(-constants.get_W());

    constraint.setCoefficient(variables.getVar(DecisionVars_Fct.delta, i, j), 1.);
    constraint.setCoefficient(
        variables.getVar(DecisionVars_Fct.y, i, j),
        -constants.get_W());

    for (int m : constants.get_M_i(i)) {
      constraint.setCoefficient(
          variables.getVar(DecisionVars_Fct.x, i, m),
          -constants.get_p_im(i, m));
    }
  }
}
