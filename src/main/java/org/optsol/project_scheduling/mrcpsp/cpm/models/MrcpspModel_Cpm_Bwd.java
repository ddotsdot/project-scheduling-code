package org.optsol.project_scheduling.mrcpsp.cpm.models;

import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import java.util.List;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.jdecor.core.IConstraintManager;
import org.optsol.jdecor.core.IObjectiveManager;
import org.optsol.jdecor.ortools.AbstractOrtoolsModelFactory;
import org.optsol.jdecor.ortools.OrtoolsVariableManager;
import org.optsol.jdecor.ortools.SolverEngine;
import org.optsol.project_scheduling.mrcpsp.cpm.constants.ICpmBwd_Constants;
import org.optsol.project_scheduling.mrcpsp.cpm.constraints.CPM_Bwd_b_Precedences;
import org.optsol.project_scheduling.mrcpsp.cpm.constraints.CPM_Bwd_c_InitProjectEnd;
import org.optsol.project_scheduling.mrcpsp.cpm.constraints.CPM_Bwd_c_InitProjectStart;
import org.optsol.project_scheduling.mrcpsp.cpm.objectives.Cpm_Bwd_MaximizeTotalLatestCompletionTimes;
import org.optsol.project_scheduling.mrcpsp.cpm.variables.DecisionVars_Cpm_Bwd;

public class MrcpspModel_Cpm_Bwd<CONST extends ICpmBwd_Constants>
    extends AbstractOrtoolsModelFactory<CONST> {

  public MrcpspModel_Cpm_Bwd(SolverEngine solverEngine) {
    super(solverEngine);
  }

  @Override
  protected AbstractVariableManager<MPSolver, MPVariable> generateVarManager() {
    return new OrtoolsVariableManager.Builder()

        // c : real+ (time that activity i completes processing)
        .addLowerBound(DecisionVars_Cpm_Bwd.c, 0.)
        .build();
  }

  @Override
  protected IObjectiveManager<CONST, MPVariable, MPSolver> generateObjective() {
    return new Cpm_Bwd_MaximizeTotalLatestCompletionTimes<>();
  }

  @Override
  protected List<IConstraintManager<? super CONST, MPVariable, MPSolver>> generateConstraints() {
    return List.of(
        new CPM_Bwd_b_Precedences<>(),
        new CPM_Bwd_c_InitProjectStart<>(),
        new CPM_Bwd_c_InitProjectEnd<>()
    );
  }
}
