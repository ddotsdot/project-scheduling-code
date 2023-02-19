package org.optsol.project_scheduling.mrcpsp.ooe.constraints;

import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import java.util.Collection;
import java.util.stream.Collectors;
import org.optsol.jdecor.core.AbstractConstraintManager;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.jdecor.ortools.AbstractOrtoolsConstraintManager;
import org.optsol.project_scheduling.mrcpsp.constants.shared.events.IConstants_E;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_A;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_M_i;
import org.optsol.project_scheduling.mrcpsp.ooe.variables.DecisionVars_Ooe;

public class OOE_e_AtLeastOneEvent<CONST extends
    IConstants_E & IConstants_A & IConstants_M_i>
    extends AbstractOrtoolsConstraintManager<CONST> {

  public OOE_e_AtLeastOneEvent() {
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
      AbstractConstraintManager<MPSolver, CONST, MPVariable, MPConstraint>.ConstraintKey constraintKey)
      throws Exception {

    int i = constraintKey.get("i");

    constraint.setLb(1.);

    for (int m : constants.get_M_i(i)) {
      for (int e : constants.get_E()) {
        if (e != constants.get_card_A()) {
          constraint.setCoefficient(variables.getVar(DecisionVars_Ooe.z, i, m, e), 1.);
        }
      }
    }
  }
}
