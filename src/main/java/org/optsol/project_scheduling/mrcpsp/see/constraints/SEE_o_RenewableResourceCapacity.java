package org.optsol.project_scheduling.mrcpsp.see.constraints;

import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import java.util.Collection;
import java.util.HashSet;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.jdecor.ortools.AbstractOrtoolsConstraintManager;
import org.optsol.project_scheduling.mrcpsp.constants.shared.events.IConstants_E;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_B_k;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_R;
import org.optsol.project_scheduling.mrcpsp.see.variables.DecisionVars_See;

public class SEE_o_RenewableResourceCapacity<CONST extends IConstants_E & IConstants_R & IConstants_B_k>
    extends AbstractOrtoolsConstraintManager<CONST> {

  public SEE_o_RenewableResourceCapacity() {
    super("e", "k");
  }

  @Override
  protected Collection<ConstraintKey> createKeys(CONST constants) {
    HashSet<ConstraintKey> indexes = new HashSet<>();
    for (int e : constants.get_E()) {
      for (int k : constants.get_R()) {
        indexes.add(new ConstraintKey(e, k));
      }
    }
    return indexes;
  }

  @Override
  protected void configureConstraint(MPConstraint constraint,
      CONST constants,
      AbstractVariableManager<MPSolver, MPVariable> variables,
      ConstraintKey index)
      throws Exception {

    int e = index.get("e");
    int k = index.get("k");

    constraint.setUb(constants.get_B_k(k));
    constraint.setCoefficient(variables.getVar(DecisionVars_See.r, e, k), 1);
  }
}
