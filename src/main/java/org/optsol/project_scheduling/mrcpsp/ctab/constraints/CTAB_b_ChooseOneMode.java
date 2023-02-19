package org.optsol.project_scheduling.mrcpsp.ctab.constraints;

import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.jdecor.ortools.AbstractOrtoolsConstraintManager;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_M_i;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_V;
import org.optsol.project_scheduling.mrcpsp.ctab.variables.DecisionVars_Ctab;

public class CTAB_b_ChooseOneMode<CONST extends IConstants_V & IConstants_M_i>
    extends AbstractOrtoolsConstraintManager<CONST> {

  public CTAB_b_ChooseOneMode() {
    super("i");
  }

  @Override
  protected Collection<ConstraintKey> createKeys(CONST constants) {
    return
        constants.get_V()
            .stream()
            .mapToInt(i -> i)
            .mapToObj(ConstraintKey::new)
            .collect(Collectors.toCollection(HashSet::new));
  }

  @Override
  protected void configureConstraint(
      MPConstraint constraint,
      CONST constants,
      AbstractVariableManager<MPSolver, MPVariable> variables,
      ConstraintKey index) throws Exception {

    int i = index.get("i");

    constraint.setUb(1);
    constraint.setLb(1);

    for (int m : constants.get_M_i(i)) {
      constraint.setCoefficient(variables.getVar(DecisionVars_Ctab.x, i, m), 1);
    }
  }
}
