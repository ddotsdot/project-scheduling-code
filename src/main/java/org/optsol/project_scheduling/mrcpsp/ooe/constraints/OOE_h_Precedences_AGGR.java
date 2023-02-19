package org.optsol.project_scheduling.mrcpsp.ooe.constraints;

import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import java.util.Collection;
import java.util.HashSet;
import org.optsol.jdecor.core.AbstractConstraintManager;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.jdecor.ortools.AbstractOrtoolsConstraintManager;
import org.optsol.project_scheduling.mrcpsp.constants.shared.events.IConstants_E;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_M_i;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_P_bar;
import org.optsol.project_scheduling.mrcpsp.ooe.variables.DecisionVars_Ooe;
import org.optsol.project_scheduling.utils.Precedence;

public class OOE_h_Precedences_AGGR<CONST
    extends IConstants_E & IConstants_P_bar & IConstants_M_i>
    extends AbstractOrtoolsConstraintManager<CONST> {

  public OOE_h_Precedences_AGGR() {
    super("i", "j", "e");
  }

  @Override
  protected Collection<ConstraintKey> createKeys(CONST constants) {
    //generate all indexes of constraint group:
    HashSet<ConstraintKey> indexes = new HashSet<>();
    for (Precedence precedence : constants.get_P_bar()) {
      for (int e : constants.get_E()) {
        if (e != constants.get_card_A()) {
          indexes.add(new ConstraintKey(precedence.get_i(), precedence.get_j(), e));
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
    int j = constraintKey.get("j");
    int e = constraintKey.get("e");

    constraint.setUb(e + 1);

    for (int m : constants.get_M_i(i)) {
      constraint.setCoefficient(
          variables.getVar(DecisionVars_Ooe.z, i, m, e),
          e + 1);
    }

    for (int m : constants.get_M_i(j)) {
      for (int ePrime = 0; ePrime <= e; ePrime++) {
        constraint.setCoefficient(
            variables.getVar(DecisionVars_Ooe.z, j, m, ePrime),
            1.);
      }
    }
  }
}
