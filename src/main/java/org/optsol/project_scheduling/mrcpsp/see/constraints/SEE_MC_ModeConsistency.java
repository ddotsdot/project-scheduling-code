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
import org.optsol.project_scheduling.mrcpsp.see.variables.DecisionVars_See;

public class SEE_MC_ModeConsistency<CONST extends IConstants_A & IConstants_M_i & IConstants_E>
    extends AbstractOrtoolsConstraintManager<CONST> {

  public SEE_MC_ModeConsistency() {
    super("i", "m", "e");
  }

  @Override
  protected Collection<ConstraintKey> createKeys(CONST constants) {
    HashSet<ConstraintKey> indexes = new HashSet<>();
    for (int i : constants.get_A()) {
      for (int m : constants.get_M_i(i)) {
        for (int e : constants.get_E()) {
          if (e != constants.get_card_A()) {
            indexes.add(new ConstraintKey(i, m, e));
          }
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
    int m = index.get("m");
    int e = index.get("e");

    constraint.setUb(1);

    constraint.setCoefficient(variables.getVar(DecisionVars_See.x, i, m, e), 1);

    for (int mPrime : constants.get_M_i(i)) {
      if (mPrime != m) {
        for (int f : constants.get_E()) {
          if (f != 0) {
            constraint.setCoefficient(variables.getVar(DecisionVars_See.y, i, mPrime, f), 1);
          }
        }
      }
    }
  }
}