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
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_R;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_b_imk;
import org.optsol.project_scheduling.mrcpsp.see.variables.DecisionVars_See;

public class SEE_j_ResourceConservation<CONST extends IConstants_A & IConstants_E & IConstants_M_i & IConstants_R & IConstants_b_imk>
    extends AbstractOrtoolsConstraintManager<CONST> {

  public SEE_j_ResourceConservation() {
    super("e", "k");
  }

  @Override
  protected Collection<ConstraintKey> createKeys(CONST constants) {
    HashSet<ConstraintKey> indexes = new HashSet<>();
    for (int e : constants.get_E()) {
      if (e != 0 && e != constants.get_card_A()) {
        for (int k : constants.get_R()) {
          indexes.add(new ConstraintKey(e, k));
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

    int e = index.get("e");
    int k = index.get("k");

    constraint.setLb(0);
    constraint.setUb(0);

    constraint.setCoefficient(variables.getVar("r", e - 1, k), 1);

    for (int i : constants.get_A()) {
      for (int m : constants.get_M_i(i)) {
        constraint.setCoefficient(
            variables.getVar(DecisionVars_See.x, i, m, e),
            constants.get_b_imk(i, m, k));
      }
    }

    for (int i : constants.get_A()) {
      for (int m : constants.get_M_i(i)) {
        constraint.setCoefficient(
            variables.getVar(DecisionVars_See.y, i, m, e),
            -constants.get_b_imk(i, m, k));
      }
    }

    constraint.setCoefficient(variables.getVar("r", e, k), -1);
  }
}