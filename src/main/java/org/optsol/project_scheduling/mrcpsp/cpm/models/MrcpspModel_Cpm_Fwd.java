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
import org.optsol.project_scheduling.mrcpsp.cpm.constants.ICpmFwd_Constants;
import org.optsol.project_scheduling.mrcpsp.cpm.constraints.CPM_Fwd_b_Precedences;
import org.optsol.project_scheduling.mrcpsp.cpm.constraints.CPM_Fwd_c_InitProjectStart;
import org.optsol.project_scheduling.mrcpsp.cpm.objectives.Cpm_Fwd_MinimizeTotalEarliestStartTimes;
import org.optsol.project_scheduling.mrcpsp.cpm.variables.DecisionVars_Cpm_Fwd;

public class MrcpspModel_Cpm_Fwd<CONST extends ICpmFwd_Constants>
    extends AbstractOrtoolsModelFactory<CONST> {

  public MrcpspModel_Cpm_Fwd(SolverEngine solverEngine) {
    super(solverEngine);
  }

  @Override
  protected AbstractVariableManager<MPSolver, MPVariable> generateVarManager() {
    return new OrtoolsVariableManager.Builder()

        // s : real+ (time that activity i starts processing)
        .addLowerBound(DecisionVars_Cpm_Fwd.s, 0.)
        .build();
  }

  @Override
  protected IObjectiveManager<CONST, MPVariable, MPSolver> generateObjective() {
    return new Cpm_Fwd_MinimizeTotalEarliestStartTimes<>();
  }

  @Override
  protected List<IConstraintManager<? super CONST, MPVariable, MPSolver>> generateConstraints() {
    return List.of(
        new CPM_Fwd_b_Precedences<>(),
        new CPM_Fwd_c_InitProjectStart<>()
    );
  }
}
