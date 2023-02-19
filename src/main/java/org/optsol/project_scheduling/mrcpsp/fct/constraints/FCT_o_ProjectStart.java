package org.optsol.project_scheduling.mrcpsp.fct.constraints;

import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.jdecor.ortools.AbstractOrtoolsConstraintManager;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_E_i;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_L_i;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_V;
import org.optsol.project_scheduling.mrcpsp.fct.variables.DecisionVars_Fct;

public class FCT_o_ProjectStart<CONST
    extends IConstants_V & IConstants_E_i & IConstants_L_i>
    extends AbstractOrtoolsConstraintManager<CONST> {

  @Override
  protected void configureConstraint(
      MPConstraint constraint,
      CONST constants,
      AbstractVariableManager<MPSolver, MPVariable> variables,
      ConstraintKey index) throws Exception {

    MPVariable var =
        variables.getVar(DecisionVars_Fct.s, constants.get_dummy_activity_project_start());
    var.setLb(0.);
    var.setUb(0.);
  }
}