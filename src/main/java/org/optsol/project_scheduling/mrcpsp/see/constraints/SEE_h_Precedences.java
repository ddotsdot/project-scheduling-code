package org.optsol.project_scheduling.mrcpsp.see.constraints;

import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import java.util.Collection;
import java.util.HashSet;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.jdecor.ortools.AbstractOrtoolsConstraintManager;
import org.optsol.project_scheduling.mrcpsp.constants.shared.events.IConstants_E;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_A;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_M_i;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_P_bar;
import org.optsol.project_scheduling.mrcpsp.see.variables.DecisionVars_See;
import org.optsol.project_scheduling.utils.Precedence;

public class SEE_h_Precedences<CONST
    extends IConstants_A & IConstants_M_i & IConstants_E & IConstants_P_bar>
    extends AbstractOrtoolsConstraintManager<CONST> {

  public SEE_h_Precedences() {
    super("i", "j", "e");
  }

  @Override
  protected Collection<ConstraintKey> createKeys(CONST constants) {
    HashSet<ConstraintKey> indexes = new HashSet<>();
    for (Precedence prec : constants.get_P_bar()) {
      for (int e : constants.get_E()) {
        if (e != 0) {
          indexes.add(new ConstraintKey(prec.get_i(), prec.get_j(), e));
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
    int e = index.get("e");

    constraint.setUb(1);

    for (int m : constants.get_M_i(i)) {
      for (int e2 = e; e2 <= constants.get_card_A(); e2++) {
        constraint.setCoefficient(variables.getVar(DecisionVars_See.y, i, m, e2), 1);
      }
    }

    for (int m : constants.get_M_i(j)) {
      for (int e2 = 0; e2 < e; e2++) {
        constraint.setCoefficient(variables.getVar(DecisionVars_See.x, j, m, e2), 1);
      }
    }
  }
}
