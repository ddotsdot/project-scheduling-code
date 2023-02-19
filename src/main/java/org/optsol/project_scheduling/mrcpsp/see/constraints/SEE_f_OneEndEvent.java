package org.optsol.project_scheduling.mrcpsp.see.constraints;

import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import java.util.Collection;
import java.util.stream.Collectors;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.jdecor.ortools.AbstractOrtoolsConstraintManager;
import org.optsol.project_scheduling.mrcpsp.constants.shared.events.IConstants_E;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_A;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_M_i;
import org.optsol.project_scheduling.mrcpsp.see.variables.DecisionVars_See;

public class SEE_f_OneEndEvent<CONST extends IConstants_A & IConstants_E & IConstants_M_i>
    extends AbstractOrtoolsConstraintManager<CONST> {

  public SEE_f_OneEndEvent() {
    super("i");
  }

  @Override
  protected Collection<ConstraintKey> createKeys(CONST constants) {
    return constants.get_A().stream()
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

    int i = index.get("i");

    constraint.setUb(1);
    constraint.setLb(1);

    for (int m : constants.get_M_i(i)) {
      for (int e : constants.get_E()) {
        if (e != 0) {
          constraint.setCoefficient(variables.getVar(DecisionVars_See.y, i, m, e), 1);
        }
      }
    }
  }
}
