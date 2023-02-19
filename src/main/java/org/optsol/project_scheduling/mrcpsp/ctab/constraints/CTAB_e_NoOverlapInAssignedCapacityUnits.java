package org.optsol.project_scheduling.mrcpsp.ctab.constraints;

import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.jdecor.ortools.AbstractOrtoolsConstraintManager;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_B_k;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_H_G;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_R;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_V;
import org.optsol.project_scheduling.mrcpsp.ctab.variables.DecisionVars_Ctab;
import org.optsol.project_scheduling.utils.Precedence;

public class CTAB_e_NoOverlapInAssignedCapacityUnits<CONST
    extends IConstants_V & IConstants_B_k & IConstants_R & IConstants_H_G>
    extends AbstractOrtoolsConstraintManager<CONST> {

  public CTAB_e_NoOverlapInAssignedCapacityUnits() {
    super("k", "l", "i", "j");
  }

  @Override
  protected Collection<ConstraintKey> createKeys(CONST constants) {
    Set<Precedence> transitiveClosure = constants.get_H_G();
    HashSet<ConstraintKey> indexes = new HashSet<>();
    for (int k : constants.get_R()) {
      for (int l : constants.get_B_k_asList(k)) {
        for (int i : constants.get_V()) {
          for (int j : constants.get_V()) {
            if (i < j && !transitiveClosure.contains(new Precedence(i, j))) {
              indexes.add(new ConstraintKey(k, l, i, j));
            }
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
      ConstraintKey index) throws Exception {

    int k = index.get("k");
    int l = index.get("l");
    int i = index.get("i");
    int j = index.get("j");

    constraint.setUb(1.);

    constraint.setCoefficient(variables.getVar(DecisionVars_Ctab.r, l, i, k), 1.);
    constraint.setCoefficient(variables.getVar(DecisionVars_Ctab.r, l, j, k), 1.);
    constraint.setCoefficient(variables.getVar(DecisionVars_Ctab.y, i, j), -1.);
    constraint.setCoefficient(variables.getVar(DecisionVars_Ctab.y, j, i), -1.);
  }
}
