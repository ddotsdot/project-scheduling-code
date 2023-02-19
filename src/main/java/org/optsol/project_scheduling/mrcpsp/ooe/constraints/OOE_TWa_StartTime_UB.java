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
import org.optsol.project_scheduling.mrcpsp.ooe.variables.DecisionVars_Ooe;

public class OOE_TWa_StartTime_UB<CONST extends
    IConstants_E & IConstants_A & IConstants_M_i & IConstants_E_i & IConstants_L_i>
    extends AbstractOrtoolsConstraintManager<CONST> {

  public OOE_TWa_StartTime_UB() {
    super("i", "e");
  }

  @Override
  protected Collection<ConstraintKey> createKeys(CONST constants) {
    Set<ConstraintKey> indexes = new HashSet<>();
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
      AbstractConstraintManager<MPSolver, CONST, MPVariable, MPConstraint>.ConstraintKey constraintKey)
      throws Exception {

    int i = constraintKey.get("i");
    int e = constraintKey.get("e");

    constraint.setUb(constants.get_L_i(constants.get_A_bar()));

    constraint.setCoefficient(variables.getVar(DecisionVars_Ooe.s, e), 1.);

    double coeff = constants.get_L_i(i) - constants.get_L_i(constants.get_A_bar());
    for (int m : constants.get_M_i(i)) {
      constraint.setCoefficient(variables.getVar(DecisionVars_Ooe.z, i, m, e), -coeff);
    }
  }
}
