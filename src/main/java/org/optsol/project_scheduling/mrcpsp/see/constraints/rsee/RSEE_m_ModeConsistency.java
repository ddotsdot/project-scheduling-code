package org.optsol.project_scheduling.mrcpsp.see.constraints.rsee;

import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.jdecor.ortools.AbstractOrtoolsConstraintManager;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_A;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_M_i;
import org.optsol.project_scheduling.mrcpsp.see.variables.DecisionVars_Rsee;

public class RSEE_m_ModeConsistency<CONST
    extends IConstants_A & IConstants_M_i>
    extends AbstractOrtoolsConstraintManager<CONST> {

  public RSEE_m_ModeConsistency() {
    super("i", "m");
  }

  @Override
  protected Collection<ConstraintKey> createKeys(CONST constants) {
    Set<ConstraintKey> keys = new HashSet<>();
    for (int i : constants.get_A()) {
      for (int m : constants.get_M_i(i)) {
        keys.add(new ConstraintKey(i, m));
      }
    }
    return keys;
  }

  @Override
  protected void configureConstraint(
      MPConstraint constraint,
      CONST constants,
      AbstractVariableManager<MPSolver, MPVariable> variables, ConstraintKey index)
      throws Exception {

    int i = index.get("i");
    int m = index.get("m");

    constraint.setUb(1);

    for (int mPrime : constants.get_M_i(i)) {
      if (mPrime != m) {
        constraint.setCoefficient(
            variables.getVar(DecisionVars_Rsee.y_tilde, i, mPrime, constants.get_card_A()),
            1);
      }
    }

    constraint.setCoefficient(
        variables.getVar(DecisionVars_Rsee.x_tilde, i, m, constants.get_card_A() - 1),
        1);
  }
}
