package org.optsol.project_scheduling.mrcpsp.ctab.constraints;

import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import java.util.Collection;
import java.util.HashSet;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.jdecor.ortools.AbstractOrtoolsConstraintManager;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_B_k;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_M_i;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_R;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_V;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_b_imk;
import org.optsol.project_scheduling.mrcpsp.ctab.variables.DecisionVars_Ctab;

public class CTAB_d_ResourceUnitAssignment<CONST
    extends IConstants_V & IConstants_M_i & IConstants_R & IConstants_B_k & IConstants_b_imk>
    extends AbstractOrtoolsConstraintManager<CONST> {

  public CTAB_d_ResourceUnitAssignment() {
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

    constraint.setUb(0);
    constraint.setLb(0);

    for (int l : constants.get_B_k_asList(k)) {
      constraint.setCoefficient(variables.getVar(DecisionVars_Ctab.r, l, i, k), 1);
    }

    for (int m : constants.get_M_i(i)) {
      constraint.setCoefficient(variables.getVar(DecisionVars_Ctab.x, i, m),
          -constants.get_b_imk(i, m, k));
    }
  }
}
