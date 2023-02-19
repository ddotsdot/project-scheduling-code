package org.optsol.project_scheduling.mrcpsp.fct.constraints;

import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import java.util.Collection;
import java.util.stream.Collectors;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.jdecor.ortools.AbstractOrtoolsConstraintManager;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_H_G;
import org.optsol.project_scheduling.mrcpsp.fct.variables.DecisionVars_Fct;

public class FCT_k_FixTransitivePrecedences<CONST extends IConstants_H_G>
    extends AbstractOrtoolsConstraintManager<CONST> {

  public FCT_k_FixTransitivePrecedences() {
    super("i", "j");
  }

  @Override
  protected Collection<ConstraintKey> createKeys(CONST constants) {
    return
        constants.get_H_G()
            .stream()
            .map(prec -> new ConstraintKey(prec.get_i(), prec.get_j()))
            .collect(Collectors.toSet());
  }

  @Override
  protected void configureConstraint(
      MPConstraint constraint,
      CONST constants,
      AbstractVariableManager<MPSolver, MPVariable> variables,
      ConstraintKey index) throws Exception {

    int i = index.get("i");
    int j = index.get("j");

    variables.getVar(DecisionVars_Fct.y, i, j).setLb(1.);
    variables.getVar(DecisionVars_Fct.y, i, j).setUb(1.);
  }
}
