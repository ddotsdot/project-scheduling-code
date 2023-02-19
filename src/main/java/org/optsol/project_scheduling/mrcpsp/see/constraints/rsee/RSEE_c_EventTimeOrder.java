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
import org.optsol.project_scheduling.mrcpsp.see.variables.DecisionVars_Rsee;

public class RSEE_c_EventTimeOrder<CONST extends IConstants_A & IConstants_E>
    extends AbstractOrtoolsConstraintManager<CONST> {

  public RSEE_c_EventTimeOrder() {
    super("e");
  }

  @Override
  protected Collection<ConstraintKey> createKeys(CONST constants) {
    return
        constants.get_E().stream()
            .filter(e -> e != constants.get_card_A())
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

    int e = index.get("e");

    constraint.setUb(0);
    constraint.setCoefficient(variables.getVar(DecisionVars_Rsee.s, e), 1);
    constraint.setCoefficient(variables.getVar(DecisionVars_Rsee.s, e + 1), -1);
  }

}
