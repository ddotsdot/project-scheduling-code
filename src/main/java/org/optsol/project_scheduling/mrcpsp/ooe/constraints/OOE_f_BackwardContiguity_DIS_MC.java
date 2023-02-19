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

public class OOE_f_BackwardContiguity_DIS_MC<CONST extends IConstants_E & IConstants_A & IConstants_M_i>
    extends AbstractOrtoolsConstraintManager<CONST> {

  public OOE_f_BackwardContiguity_DIS_MC() {
    super("i", "m", "e");
  }

  @Override
  protected Collection<ConstraintKey> createKeys(CONST constants) {
    //generate all indexes of constraint group:
    HashSet<ConstraintKey> indexes = new HashSet<>();
    for (int i : constants.get_A()) {
      for (int e : constants.get_E()) {
        if (e != 0 && e != constants.get_card_A()) {
          for (int m : constants.get_M_i(i)) {
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

    // e >=  sum_m' sum_e'=0..e-1 z_im'e' + e * (z_ime - z_im,e-1)
    // e >=  sum_m'\m sum_e'=0..e-1 z_im'e' + sum_e'=0..e-2 z_ime' + z_im,e-1 + e * z_ime - e * z_im,e-1
    // e >=  sum_m'\m sum_e'=0..e-1 z_im'e' + sum_e'=0..e-2 z_ime' - (e-1) * z_im,e-1 + e * z_ime
    // e >=  sum_m'\m sum_e'=0..e-1 z_im'e' + sum_e'=0..e-2 z_ime' + (1-e) * z_im,e-1 + e * z_ime
    constraint.setUb(e);

    for (Integer mPrime : constants.get_M_i(i)) {
      if (mPrime != m) {
        for (int ePrime = 0; ePrime <= e - 1; ePrime++) {
          constraint.setCoefficient(
              variables.getVar(DecisionVars_Ooe.z, i, mPrime, ePrime),
              1.);
        }
      }
    }

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