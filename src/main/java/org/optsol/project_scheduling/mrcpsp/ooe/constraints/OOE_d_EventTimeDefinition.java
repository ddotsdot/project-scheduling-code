package org.optsol.project_scheduling.mrcpsp.ooe.constraints;

import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import java.util.Collection;
import java.util.HashSet;
import org.optsol.jdecor.core.AbstractConstraintManager;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.jdecor.ortools.AbstractOrtoolsConstraintManager;
import org.optsol.project_scheduling.mrcpsp.constants.shared.events.IConstants_E_squared;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_A;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_M_i;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_p_im;
import org.optsol.project_scheduling.mrcpsp.ooe.variables.DecisionVars_Ooe;
import org.optsol.project_scheduling.utils.ImmutableIntPair;

public class OOE_d_EventTimeDefinition<CONST
    extends IConstants_E_squared & IConstants_A & IConstants_M_i & IConstants_p_im>
    extends AbstractOrtoolsConstraintManager<CONST> {

  public OOE_d_EventTimeDefinition() {
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
  protected void configureConstraint(
      MPConstraint constraint,
      CONST constants,
      AbstractVariableManager<MPSolver, MPVariable> variables,
      AbstractConstraintManager<MPSolver, CONST, MPVariable, MPConstraint>.ConstraintKey constraintKey)
      throws Exception {

    int i = constraintKey.get("i");
    int m = constraintKey.get("m");
    int e = constraintKey.get("e");
    int f = constraintKey.get("f");

    constraint.setLb(-constants.get_p_im(i, m));

    constraint.setCoefficient(
        variables.getVar(DecisionVars_Ooe.s, f),
        1.);

    if (f != constants.get_card_A()) {
      constraint.setCoefficient(
          variables.getVar(DecisionVars_Ooe.z, i, m, f),
          constants.get_p_im(i, m));
    }

    if (f == e + 1) {

      // in this case: e = f-1
      double coeff = 2. * -constants.get_p_im(i, m);
      constraint.setCoefficient(
          variables.getVar(DecisionVars_Ooe.z, i, m, e),
          coeff);

    } else {

      constraint.setCoefficient(
          variables.getVar(DecisionVars_Ooe.z, i, m, f - 1),
          -constants.get_p_im(i, m));

      constraint.setCoefficient(
          variables.getVar(DecisionVars_Ooe.z, i, m, e),
          -constants.get_p_im(i, m));
    }

    constraint.setCoefficient(
        variables.getVar(DecisionVars_Ooe.s, e),
        -1.);

    if (e != 0) {
      constraint.setCoefficient(
          variables.getVar(DecisionVars_Ooe.z, i, m, e - 1),
          constants.get_p_im(i, m));
    }
  }
}
