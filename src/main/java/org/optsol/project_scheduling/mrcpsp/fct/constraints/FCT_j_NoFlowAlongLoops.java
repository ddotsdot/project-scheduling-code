package org.optsol.project_scheduling.mrcpsp.fct.constraints;

import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import java.util.Collection;
import java.util.HashSet;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.jdecor.ortools.AbstractOrtoolsConstraintManager;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_M_i;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_R;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_V;
import org.optsol.project_scheduling.mrcpsp.fct.constants.sub.IConstants_b_tilde_imk;
import org.optsol.project_scheduling.mrcpsp.fct.variables.DecisionVars_Fct;

public class FCT_j_NoFlowAlongLoops<CONST
    extends IConstants_V & IConstants_R & IConstants_M_i & IConstants_b_tilde_imk>
    extends AbstractOrtoolsConstraintManager<CONST> {

  public FCT_j_NoFlowAlongLoops() {
    super("i", "k");
  }

  @Override
  protected Collection<ConstraintKey> createKeys(CONST constants) {
    HashSet<ConstraintKey> indexes = new HashSet<>();
    for (int i : constants.get_V()) {
      for (int k : constants.get_R()) {
        indexes.add(new ConstraintKey(i, k));
      }
    }
    return indexes;
  }

  @Override
  protected void configureConstraint(
      MPConstraint constraint,
      CONST constants,
      AbstractVariableManager<MPSolver, MPVariable> variables,
      ConstraintKey index) throws Exception {

    int i = index.get("i");
    int k = index.get("k");

    constraint.setUb(0.);
    constraint.setLb(0.);
    constraint.setCoefficient(variables.getVar(DecisionVars_Fct.f, i, i, k), 1.);
  }
}
