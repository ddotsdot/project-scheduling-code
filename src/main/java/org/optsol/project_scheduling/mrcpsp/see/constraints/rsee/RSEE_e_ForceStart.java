package org.optsol.project_scheduling.mrcpsp.see.constraints.rsee;

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
import org.optsol.project_scheduling.mrcpsp.see.variables.DecisionVars_Rsee;

public class RSEE_e_ForceStart<CONST extends IConstants_A & IConstants_E & IConstants_M_i>
    extends AbstractOrtoolsConstraintManager<CONST> {

  public RSEE_e_ForceStart() {
    super("i");
  }

  @Override
  protected Collection<ConstraintKey> createKeys(CONST constants) {
    return constants.get_A().stream().map(ConstraintKey::new).collect(Collectors.toSet());
  }

  @Override
  protected void configureConstraint(MPConstraint constraint, CONST constants,
      AbstractVariableManager<MPSolver, MPVariable> variables, ConstraintKey index)
      throws Exception {

    int i = index.get("i");

    constraint.setUb(1);
    constraint.setLb(1);

    for (int m : constants.get_M_i(i)) {
      constraint.setCoefficient(
          variables.getVar(DecisionVars_Rsee.x_tilde, i, m, constants.get_card_A() - 1),
          1);
    }
  }
}
