package org.optsol.project_scheduling.mrcpsp.fct.constraints;

import static org.optsol.project_scheduling.mrcpsp.constants.shared.ActivityModeCombinationAnalyzer.findDistinctCombinationsWithBottleneck;


import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.jdecor.ortools.AbstractOrtoolsConstraintManager;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_A;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_B_k;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_M_i;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_R;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_b_imk;
import org.optsol.project_scheduling.mrcpsp.fct.variables.DecisionVars_Fct;
import org.optsol.project_scheduling.utils.ImmutableIntQuadruple;

public class FCT_RC_EnforceSequentialProcessing<CONST
    extends IConstants_A & IConstants_M_i & IConstants_R & IConstants_b_imk & IConstants_B_k>
    extends AbstractOrtoolsConstraintManager<CONST> {

  public FCT_RC_EnforceSequentialProcessing() {
    super("i", "m_i", "j", "m_j");
  }

  @Override
  protected Collection<ConstraintKey> createKeys(CONST constants) {
    Set<ImmutableIntQuadruple> quadruples = findDistinctCombinationsWithBottleneck(constants);
    return
        quadruples.stream()
            .map(quadruple ->
                new ConstraintKey(
                    quadruple.getFirst(),
                    quadruple.getSecond(),
                    quadruple.getThird(),
                    quadruple.getFourth()))
            .collect(Collectors.toSet());
  }

  @Override
  protected void configureConstraint(
      MPConstraint constraint,
      CONST constants,
      AbstractVariableManager<MPSolver, MPVariable> variables,
      ConstraintKey index) throws Exception {

    int i = index.get("i");
    int m_i = index.get("m_i");
    int j = index.get("j");
    int m_j = index.get("m_j");

    constraint.setLb(-1.);

    constraint.setCoefficient(
        variables.getVar(DecisionVars_Fct.y, i, j),
        1.);
    constraint.setCoefficient(
        variables.getVar(DecisionVars_Fct.y, j, i),
        1.);
    constraint.setCoefficient(
        variables.getVar(DecisionVars_Fct.x, i, m_i),
        -1.);
    constraint.setCoefficient(
        variables.getVar(DecisionVars_Fct.x, j, m_j),
        -1.);
  }
}
