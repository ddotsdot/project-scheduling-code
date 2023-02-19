package org.optsol.project_scheduling.mrcpsp.cpm.constraints;

import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import java.util.Collection;
import java.util.stream.Collectors;
import org.optsol.jdecor.core.AbstractConstraintManager;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.jdecor.ortools.AbstractOrtoolsConstraintManager;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_P;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_p_im;
import org.optsol.project_scheduling.mrcpsp.cpm.variables.DecisionVars_Cpm_Bwd;

public class CPM_Bwd_b_Precedences<CONST extends IConstants_P & IConstants_p_im>
    extends AbstractOrtoolsConstraintManager<CONST> {

  public CPM_Bwd_b_Precedences() {
    super("i", "j");
  }

  @Override
  protected Collection<AbstractConstraintManager<
      MPSolver, CONST, MPVariable, MPConstraint>.ConstraintKey> createKeys(
      CONST constants) {

    return
        constants.get_P()
            .stream()
            .map(precedence -> new ConstraintKey(precedence.get_i(), precedence.get_j()))
            .collect(Collectors.toSet());
  }

  @Override
  protected void configureConstraint(
      MPConstraint constraint,
      CONST constants,
      AbstractVariableManager<MPSolver, MPVariable> variables,
      AbstractConstraintManager<MPSolver, CONST, MPVariable, MPConstraint>.ConstraintKey index)
      throws Exception {

    int i = index.get("i");
    int j = index.get("j");

    constraint.setLb(constants.get_p_i_min(j));

    constraint.setCoefficient(
        variables.getVar(DecisionVars_Cpm_Bwd.c, j),
        1.);

    constraint.setCoefficient(
        variables.getVar(DecisionVars_Cpm_Bwd.c, i),
        -1.);
  }
}
