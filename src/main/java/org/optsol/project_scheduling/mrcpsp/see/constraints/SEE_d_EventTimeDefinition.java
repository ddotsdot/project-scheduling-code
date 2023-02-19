package org.optsol.project_scheduling.mrcpsp.see.constraints;

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
import org.optsol.project_scheduling.mrcpsp.see.variables.DecisionVars_See;
import org.optsol.project_scheduling.utils.ImmutableIntPair;

public class SEE_d_EventTimeDefinition<CONST
    extends IConstants_A & IConstants_E_squared & IConstants_M_i & IConstants_p_im>
    extends AbstractOrtoolsConstraintManager<CONST> {

  public SEE_d_EventTimeDefinition() {
    super("i", "m", "e", "f");
  }

  @Override
  protected Collection<ConstraintKey> createKeys(CONST constants) {
    //generate all indexes of constraint group:
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
  protected void configureConstraint(MPConstraint constraint,
      CONST constants,
      AbstractVariableManager<MPSolver, MPVariable> variables,
      ConstraintKey index) throws Exception {
    int i = index.get("i");
    int m = index.get("m");
    int e = index.get("e");
    int f = index.get("f");

    constraint.setUb(constants.get_p_im(i, m));

    constraint.setCoefficient(variables.getVar(DecisionVars_See.s, e), 1);
    constraint.setCoefficient(variables.getVar(DecisionVars_See.s, f), -1);
    constraint.setCoefficient(variables.getVar(DecisionVars_See.x, i, m, e),
        constants.get_p_im(i, m));
    constraint.setCoefficient(variables.getVar(DecisionVars_See.y, i, m, f),
        constants.get_p_im(i, m));
  }
}
