package org.optsol.project_scheduling.mrcpsp.see.constraints.rsee;

import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import java.util.Collection;
import java.util.HashSet;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.jdecor.ortools.AbstractOrtoolsConstraintManager;
import org.optsol.project_scheduling.mrcpsp.constants.shared.events.IConstants_E_squared;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_A;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_M_i;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_p_im;
import org.optsol.project_scheduling.mrcpsp.see.variables.DecisionVars_Rsee;
import org.optsol.project_scheduling.utils.ImmutableIntPair;

public class RSEE_d_EventTimeDefinition<CONST
    extends IConstants_A & IConstants_E_squared & IConstants_M_i & IConstants_p_im>
    extends AbstractOrtoolsConstraintManager<CONST> {

  public RSEE_d_EventTimeDefinition() {
    super("i", "m", "e", "f");
  }

  @Override
  protected Collection<ConstraintKey> createKeys(CONST constants) {
    HashSet<ConstraintKey> indexes = new HashSet<>();
    for (int i : constants.get_A()) {
      for (int m : constants.get_M_i(i)) {
        for (ImmutableIntPair pair : constants.get_E_squared()) {
          indexes.add(new ConstraintKey(i, m, pair.getFirst(), pair.getSecond()));
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
    int m = index.get("m");
    int e = index.get("e");
    int f = index.get("f");

    constraint.setUb(0);

    constraint.setCoefficient(variables.getVar(DecisionVars_Rsee.s, e), 1);
    constraint.setCoefficient(variables.getVar(DecisionVars_Rsee.s, f), -1);

//    constraint.setCoefficient(variables.getVar(DecisionVars_Rsee.x_tilde, i, m, e),
//        constants.get_p_im(i, m));

    constraint.setCoefficient(variables.getVar(DecisionVars_Rsee.y_tilde, i, m, f),
        constants.get_p_im(i, m));

    if (e != 0) {
      constraint.setCoefficient(variables.getVar(DecisionVars_Rsee.x_tilde, i, m, e - 1),
          -constants.get_p_im(i, m));
    }

//    if (f != 1) {
//      constraint.setCoefficient(variables.getVar(DecisionVars_Rsee.y_tilde, i, m, f - 1),
//          -constants.get_p_im(i, m));
//    }
  }
}
