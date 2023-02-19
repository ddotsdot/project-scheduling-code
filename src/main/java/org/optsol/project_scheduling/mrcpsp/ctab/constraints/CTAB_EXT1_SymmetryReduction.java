package org.optsol.project_scheduling.mrcpsp.ctab.constraints;

import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.jdecor.ortools.AbstractOrtoolsConstraintManager;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_A;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_M_i;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_R;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_b_imk;
import org.optsol.project_scheduling.mrcpsp.ctab.variables.DecisionVars_Ctab;

public class CTAB_EXT1_SymmetryReduction<CONST
    extends IConstants_A & IConstants_M_i & IConstants_R & IConstants_b_imk> extends
    AbstractOrtoolsConstraintManager<CONST> {

  private Map<Integer, Integer> activityWithLargestMinimumDemandByResource;

  public CTAB_EXT1_SymmetryReduction() {
    super("k", "l");
  }

  @Override
  protected Collection<ConstraintKey> createKeys(CONST constants) {
    activityWithLargestMinimumDemandByResource = new HashMap<>();
    Set<ConstraintKey> indexes = new HashSet<>();

    for (int k : constants.get_R()) {
      int i_star_k = findActivityWithLargestMinimumDemand(k, constants);
      for (int l = 1; l <= constants.get_b_min_ik(i_star_k, k); l++) {
        indexes.add(new ConstraintKey(k, l));
      }
      activityWithLargestMinimumDemandByResource.put(k, i_star_k);
    }
    return indexes;
  }

  private int findActivityWithLargestMinimumDemand(int k, CONST constants) {
    int largestMinimumDemand =
        constants.get_A().stream()
            .map(i -> constants.get_b_min_ik(i, k))
            .max(Integer::compareTo)
            .get();

    // return lowest-index activity with largest minimum demand
    return
        constants.get_A()
            .stream()
            .filter(i -> constants.get_b_min_ik(i, k) == largestMinimumDemand)
            .min(Integer::compareTo)
            .get();
  }

  @Override
  protected void configureConstraint(
      MPConstraint constraint,
      CONST constants,
      AbstractVariableManager<MPSolver, MPVariable> variables,
      ConstraintKey index) throws Exception {

    int k = index.get("k");
    int l = index.get("l");

    int i = activityWithLargestMinimumDemandByResource.get(k);
    MPVariable var = variables.getVar(DecisionVars_Ctab.r, l, i, k);
    var.setLb(1.);
    var.setUb(1.);
  }
}
