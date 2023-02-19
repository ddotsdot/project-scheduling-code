package org.optsol.project_scheduling.mrcpsp.fct.constraints;

import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import java.util.Collection;
import java.util.HashSet;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.jdecor.ortools.AbstractOrtoolsConstraintManager;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_V;
import org.optsol.project_scheduling.mrcpsp.fct.variables.DecisionVars_Fct;

public class FCT_d_UniqueOrderIfSequentially<CONST extends IConstants_V>
    extends AbstractOrtoolsConstraintManager<CONST> {

  public FCT_d_UniqueOrderIfSequentially() {
    super("i", "j");
  }

  @Override
  protected Collection<ConstraintKey> createKeys(CONST constants) {
    HashSet<ConstraintKey> indexes = new HashSet<>();
    for (int i : constants.get_V()) {
      for (int j : constants.get_V()) {
        if (i < j) {
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

    constraint.setUb(1.);

    constraint.setCoefficient(variables.getVar(DecisionVars_Fct.y, i, j), 1.);
    constraint.setCoefficient(variables.getVar(DecisionVars_Fct.y, j, i), 1.);
  }
}
