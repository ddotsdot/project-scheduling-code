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
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_B_k;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_M_i;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_R;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_b_imk;
import org.optsol.project_scheduling.mrcpsp.see.variables.DecisionVars_Rsee;

public class RSEE_k_RenewableResourceCapacity<CONST
    extends IConstants_A & IConstants_E & IConstants_M_i & IConstants_R & IConstants_B_k & IConstants_b_imk>
    extends AbstractOrtoolsConstraintManager<CONST> {

  public RSEE_k_RenewableResourceCapacity() {
    super("e", "k");
  }

  @Override
  protected Collection<ConstraintKey> createKeys(CONST constants) {
    Set<ConstraintKey> keys = new HashSet<>();
    for (int e : constants.get_E()) {
      if (e != constants.get_card_A()) {
        for (int k : constants.get_R()) {
          keys.add(new ConstraintKey(e, k));
        }
      }
    }
    return keys;
  }

  @Override
  protected void configureConstraint(MPConstraint constraint, CONST constants,
      AbstractVariableManager<MPSolver, MPVariable> variables, ConstraintKey index)
      throws Exception {

    int e = index.get("e");
    int k = index.get("k");

    constraint.setUb(constants.get_B_k(k));

    for (int i : constants.get_A()) {
      for (int m : constants.get_M_i(i)) {
        constraint.setCoefficient(
            variables.getVar(DecisionVars_Rsee.x_tilde, i, m, e),
            constants.get_b_imk(i, m, k));

        if (e != 0) {
          constraint.setCoefficient(
              variables.getVar(DecisionVars_Rsee.y_tilde, i, m, e),
              -constants.get_b_imk(i, m, k));
        }
      }
    }
  }
}