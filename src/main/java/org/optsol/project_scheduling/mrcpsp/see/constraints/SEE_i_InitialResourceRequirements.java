package org.optsol.project_scheduling.mrcpsp.see.constraints;

import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import java.util.Collection;
import java.util.stream.Collectors;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.jdecor.ortools.AbstractOrtoolsConstraintManager;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_A;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_M_i;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_R;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_b_imk;
import org.optsol.project_scheduling.mrcpsp.see.variables.DecisionVars_See;

public class SEE_i_InitialResourceRequirements<CONST extends IConstants_A & IConstants_M_i & IConstants_R & IConstants_b_imk>
    extends AbstractOrtoolsConstraintManager<CONST> {

  public SEE_i_InitialResourceRequirements() {
    super("k");
  }

  @Override
  protected Collection<ConstraintKey> createKeys(CONST constants) {
    return constants.get_R().stream()
        .map(ConstraintKey::new)
        .collect(Collectors.toSet());
  }

  @Override
  protected void configureConstraint(
      MPConstraint constraint,
      CONST constants,
      AbstractVariableManager<MPSolver, MPVariable> variables,
      ConstraintKey index)
      throws Exception {

    int k = index.get("k");

    constraint.setLb(0);
    constraint.setUb(0);

    for (int i : constants.get_A()) {
      for (int m : constants.get_M_i(i)) {
        constraint.setCoefficient(variables.getVar(DecisionVars_See.x, i, m, 0),
            constants.get_b_imk(i, m, k));
      }
    }

    constraint.setCoefficient(variables.getVar("r", 0, k), -1);
  }
}
