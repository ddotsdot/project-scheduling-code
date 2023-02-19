package org.optsol.project_scheduling.mrcpsp.fct.constraints.weak;

import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.jdecor.ortools.AbstractOrtoolsConstraintManager;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_M_i;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_V;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_p_im;
import org.optsol.project_scheduling.mrcpsp.fct.constants.sub.IConstants_Ws_ij;
import org.optsol.project_scheduling.mrcpsp.fct.variables.DecisionVars_Fct;

public class FCT_cPrime_StartingTimesDef_Weak<CONST
    extends IConstants_V & IConstants_M_i & IConstants_p_im & IConstants_Ws_ij>
    extends AbstractOrtoolsConstraintManager<CONST> {

  public FCT_cPrime_StartingTimesDef_Weak() {
    super("i", "j");
  }

  @Override
  protected Collection<ConstraintKey> createKeys(CONST constants) {
    Set<ConstraintKey> indexes = new HashSet<>();
    for (int i : constants.get_V()) {
      for (int j : constants.get_V()) {
        if (i != j) {
          indexes.add(new ConstraintKey(i, j));
        }
      }
    }
    return indexes;
  }

  @Override
  protected void configureConstraint(
      MPConstraint constraint,
      CONST constants,
      AbstractVariableManager<MPSolver, MPVariable> variables,
      ConstraintKey index)
      throws Exception {

    int i = index.get("i");
    int j = index.get("j");

    constraint.setUb(constants.get_Ws_ij(i, j));

    constraint.setCoefficient(variables.getVar(DecisionVars_Fct.s, i), 1.);

    for (int m : constants.get_M_i(i)) {
      constraint.setCoefficient(variables.getVar(DecisionVars_Fct.x, i, m),
          constants.get_p_im(i, m));
    }

    constraint.setCoefficient(variables.getVar(DecisionVars_Fct.s, j), -1.);
    constraint.setCoefficient(
        variables.getVar(DecisionVars_Fct.y, i, j),
        constants.get_Ws_ij(i, j));

  }
}
