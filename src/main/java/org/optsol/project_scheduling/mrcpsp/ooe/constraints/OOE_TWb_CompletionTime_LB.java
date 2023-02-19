package org.optsol.project_scheduling.mrcpsp.ooe.constraints;

import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.optsol.jdecor.core.AbstractConstraintManager;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.jdecor.ortools.AbstractOrtoolsConstraintManager;
import org.optsol.project_scheduling.mrcpsp.constants.shared.events.IConstants_E;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_A;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_E_i;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_L_i;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_M_i;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_p_im;
import org.optsol.project_scheduling.mrcpsp.ooe.variables.DecisionVars_Ooe;

public class OOE_TWb_CompletionTime_LB<CONST extends
    IConstants_E & IConstants_A & IConstants_M_i
    & IConstants_E_i & IConstants_L_i & IConstants_p_im>
    extends AbstractOrtoolsConstraintManager<CONST> {

  public OOE_TWb_CompletionTime_LB() {
    super("i", "e");
  }

  @Override
  protected Collection<ConstraintKey> createKeys(CONST constants) {
    Set<ConstraintKey> indexes = new HashSet<>();
    for (int i : constants.get_A()) {
      for (int e : constants.get_E()) {
        if (e > 1 && e != constants.get_card_A()) {
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
      AbstractConstraintManager<MPSolver, CONST, MPVariable, MPConstraint>.ConstraintKey constraintKey)
      throws Exception {

    int i = constraintKey.get("i");
    int e = constraintKey.get("e");

    constraint.setUb(0.);

    for (int m : constants.get_M_i(i)) {
      double coeff = constants.get_E_i(i) + constants.get_p_im(i, m);

      constraint.setCoefficient(variables.getVar(DecisionVars_Ooe.z, i, m, e - 1), coeff);

      constraint.setCoefficient(variables.getVar(DecisionVars_Ooe.z, i, m, e), -coeff);
    }

    constraint.setCoefficient(variables.getVar(DecisionVars_Ooe.s, e), -1.);
  }
}
