package org.optsol.project_scheduling.mrcpsp.fct.constraints;

import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import java.util.Collection;
import java.util.stream.Collectors;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.jdecor.ortools.AbstractOrtoolsConstraintManager;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_A;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_M_i;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_N;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_W_k;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_w_imk;
import org.optsol.project_scheduling.mrcpsp.fct.variables.DecisionVars_Fct;

public class FCT_n_NonrenewableResourceCapacity<CONST
    extends IConstants_A & IConstants_N & IConstants_M_i & IConstants_W_k & IConstants_w_imk>
    extends AbstractOrtoolsConstraintManager<CONST> {

  public FCT_n_NonrenewableResourceCapacity() {
    super("k");
  }

  @Override
  protected Collection<ConstraintKey> createKeys(CONST constants) {
    return
        constants.get_N()
            .stream()
            .map(ConstraintKey::new)
            .collect(Collectors.toSet());
  }

  @Override
  protected void configureConstraint(
      MPConstraint constraint,
      CONST constants,
      AbstractVariableManager<MPSolver, MPVariable> variables,
      ConstraintKey index) throws Exception {

    int k = index.get("k");

    constraint.setUb(constants.get_W_k(k));

    for (int i : constants.get_A()) {
      for (int m : constants.get_M_i(i)) {
        constraint.setCoefficient(
            variables.getVar(DecisionVars_Fct.x, i, m),
            constants.get_w_imk(i, m, k));
      }
    }
  }
}