package org.optsol.project_scheduling.mrcpsp.ctab.constraints;

import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import java.util.Collection;
import java.util.HashSet;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.jdecor.ortools.AbstractOrtoolsConstraintManager;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_P;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_p_im;
import org.optsol.project_scheduling.mrcpsp.ctab.variables.DecisionVars_Ctab;
import org.optsol.project_scheduling.utils.Precedence;

public class CTAB_c_Precedences<CONST extends IConstants_P & IConstants_p_im>
    extends AbstractOrtoolsConstraintManager<CONST> {

  public CTAB_c_Precedences() {
    super("i", "j");
  }

  @Override
  protected Collection<ConstraintKey> createKeys(CONST constants) {
    //generate all indexes of constraint group:
    HashSet<ConstraintKey> indexes = new HashSet<>();
    for (Precedence prec : constants.get_P()) {
      indexes.add(new ConstraintKey(prec.get_i(), prec.get_j()));
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

    constraint.setUb(0);

    constraint.setCoefficient(variables.getVar(DecisionVars_Ctab.s, i), 1);
    constraint.setCoefficient(variables.getVar(DecisionVars_Ctab.s, j), -1);

    for (int m : constants.get_M_i(i)) {
      constraint.setCoefficient(variables.getVar(DecisionVars_Ctab.x, i, m),
          constants.get_p_im(i, m));
    }
  }
}
