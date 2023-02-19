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
import org.optsol.project_scheduling.mrcpsp.see.variables.DecisionVars_Rsee;

public class RSEE_h_MonotoneEndVars<CONST extends IConstants_A & IConstants_E & IConstants_M_i>
    extends AbstractOrtoolsConstraintManager<CONST> {

  public RSEE_h_MonotoneEndVars() {
    super("i", "m", "e");
  }

  @Override
  protected Collection<ConstraintKey> createKeys(CONST constants) {
    Set<ConstraintKey> keys = new HashSet<>();
    for (Integer i : constants.get_A()) {
      for (Integer m : constants.get_M_i(i)) {
        for (Integer e : constants.get_E()) {
          if (e > 0 && e < constants.get_card_A()) {
            keys.add(new ConstraintKey(i, m, e));
          }
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
    int m = index.get("m");
    int e = index.get("e");

    constraint.setUb(0);

    constraint.setCoefficient(
        variables.getVar(DecisionVars_Rsee.y_tilde, i, m, e),
        1);
    constraint.setCoefficient(
        variables.getVar(DecisionVars_Rsee.y_tilde, i, m, e + 1),
        -1);
  }
}
