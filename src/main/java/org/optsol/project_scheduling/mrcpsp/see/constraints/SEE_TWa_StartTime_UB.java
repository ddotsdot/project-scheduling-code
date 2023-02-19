package org.optsol.project_scheduling.mrcpsp.see.constraints;

import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import java.util.Collection;
import java.util.HashSet;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.jdecor.ortools.AbstractOrtoolsConstraintManager;
import org.optsol.project_scheduling.mrcpsp.constants.shared.events.IConstants_E;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_E_i;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_L_i;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_M_i;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_V;
import org.optsol.project_scheduling.mrcpsp.see.variables.DecisionVars_See;

public class SEE_TWa_StartTime_UB<CONST
    extends IConstants_V & IConstants_E & IConstants_M_i & IConstants_E_i & IConstants_L_i>
    extends AbstractOrtoolsConstraintManager<CONST> {

  public SEE_TWa_StartTime_UB() {
    super("i", "e");
  }

  @Override
  protected Collection<ConstraintKey> createKeys(CONST constants) {
    HashSet<ConstraintKey> indexes = new HashSet<>();
    for (int i : constants.get_A()) {
      for (int e : constants.get_E()) {
        if (e != 0 && e != constants.get_card_A()) {
          indexes.add(new ConstraintKey(i, e));
        }
      }
    }
    return indexes;
  }

  @Override
  protected void configureConstraint(
      MPConstraint constraint,
      CONST constants,
      AbstractVariableManager<MPSolver, MPVariable> variables,
      ConstraintKey index)
      throws Exception {

    int i = index.get("i");
    int e = index.get("e");

    double L_A_bar = constants.get_L_i(constants.get_A_bar());
    constraint.setUb(L_A_bar);
    constraint.setCoefficient(variables.getVar(DecisionVars_See.s, e), 1);

    for (int m : constants.get_M_i(i)) {
      constraint.setCoefficient(
          variables.getVar(DecisionVars_See.x, i, m, e),
          -(constants.get_L_i(i) - L_A_bar));
    }
  }
}
