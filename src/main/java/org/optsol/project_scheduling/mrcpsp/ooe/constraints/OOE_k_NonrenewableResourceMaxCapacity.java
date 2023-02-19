package org.optsol.project_scheduling.mrcpsp.ooe.constraints;

import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import java.util.Collection;
import java.util.stream.Collectors;
import org.optsol.jdecor.core.AbstractConstraintManager;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.jdecor.ortools.AbstractOrtoolsConstraintManager;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_A;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_N;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_W_k;
import org.optsol.project_scheduling.mrcpsp.ooe.variables.DecisionVars_Ooe;

public class OOE_k_NonrenewableResourceMaxCapacity<CONST
    extends IConstants_A & IConstants_N & IConstants_W_k>
    extends AbstractOrtoolsConstraintManager<CONST> {

  public OOE_k_NonrenewableResourceMaxCapacity() {
    super("k");
  }

  @Override
  protected Collection<ConstraintKey> createKeys(CONST constants) {
    return
        constants.get_N()
            .stream()
            .mapToInt(k -> k)
            .mapToObj(ConstraintKey::new)
            .collect(Collectors.toSet());
  }

  @Override
  protected void configureConstraint(
      MPConstraint constraint,
      CONST constants,
      AbstractVariableManager<MPSolver, MPVariable> variables,
      AbstractConstraintManager<MPSolver, CONST, MPVariable, MPConstraint>.ConstraintKey constraintKey)
      throws Exception {

    int k = constraintKey.get("k");
    constraint.setUb(constants.get_W_k(k));

    for (int i : constants.get_A()) {
      constraint.setCoefficient(variables.getVar(DecisionVars_Ooe.r, i, k), 1.0);
    }
  }
}
