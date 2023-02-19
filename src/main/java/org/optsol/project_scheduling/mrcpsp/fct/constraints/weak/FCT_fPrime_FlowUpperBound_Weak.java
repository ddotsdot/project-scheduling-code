package org.optsol.project_scheduling.mrcpsp.fct.constraints.weak;

import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.jdecor.ortools.AbstractOrtoolsConstraintManager;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_R;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_V;
import org.optsol.project_scheduling.mrcpsp.fct.constants.sub.IConstants_Wf_ijk;
import org.optsol.project_scheduling.mrcpsp.fct.variables.DecisionVars_Fct;

public class FCT_fPrime_FlowUpperBound_Weak<CONST extends IConstants_V & IConstants_R & IConstants_Wf_ijk>
    extends AbstractOrtoolsConstraintManager<CONST> {

  public FCT_fPrime_FlowUpperBound_Weak() {
    super("i", "j", "k");
  }

  @Override
  protected Collection<ConstraintKey> createKeys(CONST constants) {
    HashSet<ConstraintKey> indexes = new HashSet<>();
    List<Integer> A_0 =
        constants.get_V().stream()
            .filter(i -> i < constants.get_A_bar())
            .collect(Collectors.toList());

    List<Integer> A_A_bar =
        constants.get_V().stream()
            .filter(i -> i > 0)
            .collect(Collectors.toList());

    for (int i : A_0) {
      for (int j : A_A_bar) {
        if (i != j) {
          for (int k : constants.get_R()) {
            indexes.add(new ConstraintKey(i, j, k));
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
      ConstraintKey index)
      throws Exception {

    int i = index.get("i");
    int j = index.get("j");
    int k = index.get("k");

    constraint.setUb(0.);

    constraint.setCoefficient(variables.getVar(DecisionVars_Fct.f, i, j, k), 1.);
    constraint.setCoefficient(
        variables.getVar(DecisionVars_Fct.y, i, j),
        -constants.get_Wf_ijk(i, j, k));
  }
}
