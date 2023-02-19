package org.optsol.project_scheduling.mrcpsp.see.constraints.rsee;

import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.jdecor.ortools.AbstractOrtoolsConstraintManager;
import org.optsol.project_scheduling.mrcpsp.constants.shared.events.IConstants_E;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_A;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_M_i;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_P_bar;
import org.optsol.project_scheduling.mrcpsp.see.variables.DecisionVars_Rsee;
import org.optsol.project_scheduling.utils.Precedence;

public class RSEE_j_Precedences<CONST extends IConstants_A & IConstants_E & IConstants_M_i & IConstants_P_bar>
    extends AbstractOrtoolsConstraintManager<CONST> {

  public RSEE_j_Precedences() {
    super("i", "j", "e");
  }

  @Override
  protected Collection<ConstraintKey> createKeys(CONST constants) {
    Set<ConstraintKey> keys = new HashSet<>();
    for (Precedence precedence : constants.get_P_bar()) {
      for (Integer e : constants.get_E()) {
        if (e != constants.get_card_A()) {
          keys.add(new ConstraintKey(precedence.get_i(), precedence.get_j(), e));
        }
      }
    }
    return keys;
  }

  @Override
  protected void configureConstraint(MPConstraint constraint, CONST constants,
      AbstractVariableManager<MPSolver, MPVariable> variables, ConstraintKey index)
      throws Exception {

    int i = index.get("i");
    int j = index.get("j");
    int e = index.get("e");

    constraint.setUb(0);

    for (int m : constants.get_M_i(j)) {
      constraint.setCoefficient(
          variables.getVar(DecisionVars_Rsee.x_tilde, j, m, e),
          1);
    }

    for (int m : constants.get_M_i(i)) {
      if (e != 0) {
        constraint.setCoefficient(
            variables.getVar(DecisionVars_Rsee.y_tilde, i, m, e),
            -1);
      }
    }
  }
}
