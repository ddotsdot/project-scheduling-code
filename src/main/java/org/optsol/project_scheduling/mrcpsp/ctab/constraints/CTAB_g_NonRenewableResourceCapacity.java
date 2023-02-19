package org.optsol.project_scheduling.mrcpsp.ctab.constraints;

import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import java.util.Collection;
import java.util.stream.Collectors;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.jdecor.ortools.AbstractOrtoolsConstraintManager;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_M_i;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_N;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_V;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_W_k;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_w_imk;
import org.optsol.project_scheduling.mrcpsp.ctab.variables.DecisionVars_Ctab;

public class CTAB_g_NonRenewableResourceCapacity<CONST
    extends IConstants_V & IConstants_N & IConstants_M_i & IConstants_W_k & IConstants_w_imk> extends
    AbstractOrtoolsConstraintManager<CONST> {

  public CTAB_g_NonRenewableResourceCapacity() {
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
  protected void configureConstraint(MPConstraint constraint,
      CONST constants,
      AbstractVariableManager<MPSolver, MPVariable> variables,
      ConstraintKey index) throws Exception {

    int k = index.get("k");
    constraint.setUb(constants.get_W_k(k));

    for (int i : constants.get_V()) {
      for (int m : constants.get_M_i(i)) {
        constraint.setCoefficient(
            variables.getVar(DecisionVars_Ctab.x, i, m),
            constants.get_w_imk(i, m, k));
      }
    }
  }
}
