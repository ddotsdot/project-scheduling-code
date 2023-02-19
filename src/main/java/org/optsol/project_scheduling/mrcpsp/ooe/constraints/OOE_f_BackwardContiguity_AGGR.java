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

public class OOE_f_BackwardContiguity_AGGR<CONST extends IConstants_E & IConstants_A & IConstants_M_i>
    extends AbstractOrtoolsConstraintManager<CONST> {

  public OOE_f_BackwardContiguity_AGGR() {
    super("i", "e");
  }

  @Override
  protected Collection<ConstraintKey> createKeys(CONST constants) {
    //generate all indexes of constraint group:
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
      AbstractConstraintManager<MPSolver, CONST, MPVariable, MPConstraint>.ConstraintKey constraintKey)
      throws Exception {

    int i = constraintKey.get("i");
    int e = constraintKey.get("e");

    // e >= sum_m sum_e'=0..e-1 z_ime' + e * sum_m (z_ime - z_im,e-1)
    // e >= sum_m sum_e'=0..e-2 z_ime' + sum_m z_im,e-1         + e * sum_m z_ime - e * sum_m z_im,e-1
    // e >= sum_m sum_e'=0..e-2 z_ime' - (e-1) * sum_m z_im,e-1 + e * sum_m z_ime
    // e >= sum_m sum_e'=0..e-2 z_ime' + (1-e) * sum_m z_im,e-1 + e * sum_m z_ime
    constraint.setUb(e);

    for (int m : constants.get_M_i(i)) {

      for (int ePrime = 0; ePrime <= e - 2; ePrime++) {
        constraint.setCoefficient(
            variables.getVar(DecisionVars_Ooe.z, i, m, ePrime),
            1.);
      }

      constraint.setCoefficient(
          variables.getVar(DecisionVars_Ooe.z, i, m, e - 1),
          1 - e);

      constraint.setCoefficient(
          variables.getVar(DecisionVars_Ooe.z, i, m, e),
          e);
    }
  }
}
