package org.optsol.project_scheduling.mrcpsp.fct.constraints;

import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.jdecor.ortools.AbstractOrtoolsConstraintManager;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_B_k;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_R;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_V;
import org.optsol.project_scheduling.mrcpsp.fct.variables.DecisionVars_Fct;

public class FCT_i_FixInitialFlows<CONST extends IConstants_V & IConstants_R & IConstants_B_k>
    extends AbstractOrtoolsConstraintManager<CONST> {

  public FCT_i_FixInitialFlows() {
    super("j", "k");
  }

  @Override
  protected Collection<ConstraintKey> createKeys(CONST constants) {
    Set<ConstraintKey> indexes = new HashSet<>();
    List<Integer> A_0 =
        constants.get_V().stream()
            .filter(j -> j < constants.get_A_bar())
            .collect(Collectors.toList());

    for (Integer j : A_0) {
      for (int k : constants.get_R()) {
        indexes.add(new ConstraintKey(j, k));
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

    int j = index.get("j");
    int k = index.get("k");

    MPVariable var =
        variables.getVar(DecisionVars_Fct.f, constants.get_A_bar(), j, k);

    if (j == constants.get_dummy_activity_project_start()) {
      var.setUb(constants.get_B_k(k));
      var.setLb(constants.get_B_k(k));
    } else {
      var.setUb(0.);
      var.setLb(0.);
    }
  }
}
