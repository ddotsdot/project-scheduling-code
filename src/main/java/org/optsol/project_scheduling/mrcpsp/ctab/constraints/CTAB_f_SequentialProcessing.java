package org.optsol.project_scheduling.mrcpsp.ctab.constraints;

import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.jdecor.ortools.AbstractOrtoolsConstraintManager;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_E_i;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_H_G;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_L_i;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_V;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_p_im;
import org.optsol.project_scheduling.mrcpsp.ctab.variables.DecisionVars_Ctab;
import org.optsol.project_scheduling.utils.Precedence;

public class CTAB_f_SequentialProcessing<CONST
    extends IConstants_V & IConstants_L_i & IConstants_E_i & IConstants_p_im & IConstants_H_G>
    extends AbstractOrtoolsConstraintManager<CONST> {

  public CTAB_f_SequentialProcessing() {
    super("i", "j");
  }

  @Override
  protected Collection<ConstraintKey> createKeys(CONST constants) {
    Set<Precedence> transitiveClosure = constants.get_H_G();
    HashSet<ConstraintKey> indexes = new HashSet<>();
    for (int i : constants.get_V()) {
      for (int j : constants.get_V()) {
        if (i != j && !transitiveClosure.contains(new Precedence(i, j))) {
          indexes.add(new ConstraintKey(i, j));
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
      ConstraintKey index) throws Exception {

    int i = index.get("i");
    int j = index.get("j");

    double ub = constants.get_L_i(i) + constants.get_p_i_max(i) - constants.get_E_i(j);

    constraint.setUb(ub);

    constraint.setCoefficient(variables.getVar(DecisionVars_Ctab.y, i, j), ub);

    constraint.setCoefficient(variables.getVar(DecisionVars_Ctab.s, i), 1.);
    constraint.setCoefficient(variables.getVar(DecisionVars_Ctab.s, j), -1.);

    for (int m : constants.get_M_i(i)) {
      constraint.setCoefficient(
          variables.getVar(DecisionVars_Ctab.x, i, m),
          constants.get_p_im(i, m));
    }
  }
}
