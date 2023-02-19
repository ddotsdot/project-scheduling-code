package org.optsol.project_scheduling.mrcpsp.ooe.constraints;

import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.optsol.jdecor.core.AbstractConstraintManager;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.jdecor.ortools.AbstractOrtoolsConstraintManager;
import org.optsol.project_scheduling.mrcpsp.constants.shared.events.IConstants_E;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_A;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_B_k;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_M_i;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_R;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_b_imk;
import org.optsol.project_scheduling.mrcpsp.ooe.variables.DecisionVars_Ooe;

public class OOE_i_MaxResourceCapacity<CONST extends
    IConstants_E & IConstants_A & IConstants_R & IConstants_M_i & IConstants_b_imk & IConstants_B_k>
    extends AbstractOrtoolsConstraintManager<CONST> {

  public OOE_i_MaxResourceCapacity() {
    super("e", "k");
  }

  @Override
  protected Collection<ConstraintKey> createKeys(CONST constants) {
    Set<ConstraintKey> indexes = new HashSet<>();
    for (int e : constants.get_E()) {
      if (e != constants.get_card_A()) {
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
      AbstractConstraintManager<MPSolver, CONST, MPVariable, MPConstraint>.ConstraintKey constraintKey)
      throws Exception {

    int e = constraintKey.get("e");
    int k = constraintKey.get("k");

    constraint.setUb(constants.get_B_k(k));

    for (int i : constants.get_A()) {
      for (int m : constants.get_M_i(i)) {
        constraint.setCoefficient(
            variables.getVar(DecisionVars_Ooe.z, i, m, e),
            constants.get_b_imk(i, m, k));
      }
    }
  }
}
