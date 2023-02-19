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
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_A;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_M_i;
import org.optsol.project_scheduling.mrcpsp.ooe.variables.DecisionVars_Ooe;

public class OOE_MC_ModeConsistency<CONST
    extends IConstants_E & IConstants_A & IConstants_M_i>
    extends AbstractOrtoolsConstraintManager<CONST> {

  public OOE_MC_ModeConsistency() {
    super("i", "m", "e");
  }

  @Override
  protected Collection<ConstraintKey> createKeys(CONST constants) {
    //generate all indexes of constraint group:
    HashSet<ConstraintKey> indexes = new HashSet<>();
    for (int i : constants.get_A()) {
      for (int m : constants.get_M_i(i)) {
        for (int e : constants.get_E()) {
          if (e != constants.get_card_A()) {
            indexes.add(new ConstraintKey(i, m, e));
          }
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
    int m = constraintKey.get("m");
    int e = constraintKey.get("e");

    constraint.setUb(constants.get_card_A());

    constraint.setCoefficient(
        variables.getVar(DecisionVars_Ooe.z, i, m, e),
        constants.get_card_A());

    for (int mPrime : constants.get_M_i(i)) {
      if (mPrime != m) {
        for (int f : constants.get_E()) {
          if (f != constants.get_card_A()) {
            double coeff = f != e ? 1. : -constants.get_card_A();
            constraint.setCoefficient(
                variables.getVar(DecisionVars_Ooe.z, i, mPrime, f),
                coeff);
          }
        }
      }
    }
  }
}
