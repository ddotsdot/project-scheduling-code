package org.optsol.project_scheduling.mrcpsp.see.models;

import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import java.util.List;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.jdecor.core.IConstraintManager;
import org.optsol.jdecor.core.IObjectiveManager;
import org.optsol.jdecor.ortools.AbstractOrtoolsModelFactory;
import org.optsol.jdecor.ortools.OrtoolsVariableManager;
import org.optsol.jdecor.ortools.SolverEngine;
import org.optsol.project_scheduling.mrcpsp.see.constants.ISeeModel_Constants;
import org.optsol.project_scheduling.mrcpsp.see.constraints.SEE_MC_ModeConsistency;
import org.optsol.project_scheduling.mrcpsp.see.constraints.SEE_TWa_StartTime_LB;
import org.optsol.project_scheduling.mrcpsp.see.constraints.SEE_TWa_StartTime_UB;
import org.optsol.project_scheduling.mrcpsp.see.constraints.SEE_TWb_CompletionTime_LB;
import org.optsol.project_scheduling.mrcpsp.see.constraints.SEE_TWb_CompletionTime_UB;
import org.optsol.project_scheduling.mrcpsp.see.constraints.SEE_TWc_Makespan_LB;
import org.optsol.project_scheduling.mrcpsp.see.constraints.SEE_TWc_Makespan_UB;
import org.optsol.project_scheduling.mrcpsp.see.constraints.SEE_VFa_EliminateAssignments;
import org.optsol.project_scheduling.mrcpsp.see.constraints.SEE_VFb_EliminateAssignments;
import org.optsol.project_scheduling.mrcpsp.see.constraints.SEE_b_InitialEventTime;
import org.optsol.project_scheduling.mrcpsp.see.constraints.SEE_c_EventTimeOrder;
import org.optsol.project_scheduling.mrcpsp.see.constraints.SEE_d_EventTimeDefinition;
import org.optsol.project_scheduling.mrcpsp.see.constraints.SEE_e_OneStartEvent;
import org.optsol.project_scheduling.mrcpsp.see.constraints.SEE_f_OneEndEvent;
import org.optsol.project_scheduling.mrcpsp.see.constraints.SEE_g_StartBeforeEnd;
import org.optsol.project_scheduling.mrcpsp.see.constraints.SEE_h_Precedences;
import org.optsol.project_scheduling.mrcpsp.see.constraints.SEE_i_InitialResourceRequirements;
import org.optsol.project_scheduling.mrcpsp.see.constraints.SEE_j_ResourceConservation;
import org.optsol.project_scheduling.mrcpsp.see.constraints.SEE_k_NonrenewableResourceCapacity;
import org.optsol.project_scheduling.mrcpsp.see.constraints.SEE_o_RenewableResourceCapacity;
import org.optsol.project_scheduling.mrcpsp.see.objective.SEE_a_MinimizeMakespan;
import org.optsol.project_scheduling.mrcpsp.see.variables.DecisionVars_See;

public class MrcpspModel_See_TW_VF extends
    AbstractOrtoolsModelFactory<ISeeModel_Constants> {

  public MrcpspModel_See_TW_VF(SolverEngine solverEngine) {
    super(solverEngine);
  }

  @Override
  protected AbstractVariableManager<MPSolver, MPVariable> generateVarManager() {
    return new OrtoolsVariableManager.Builder()

        // r : real+ (completion time of job j)
        .addLowerBound(DecisionVars_See.r, 0.)

        // s : real+ (time of occurrence of start-end event e)
        .addLowerBound(DecisionVars_See.s, 0.)

        // x : bool (is activity i starting in mode m when event e occurs)
        .addIntVar(DecisionVars_See.x)
        .addLowerBound(DecisionVars_See.x, 0.)
        .addUpperBound(DecisionVars_See.x, 1.)

        // y : bool (is activity i finishing in mode m when event e occurs)
        .addIntVar(DecisionVars_See.y)
        .addLowerBound(DecisionVars_See.y, 0.)
        .addUpperBound(DecisionVars_See.y, 1.)

        .build();
  }

  @Override
  protected IObjectiveManager<? super ISeeModel_Constants, MPVariable, MPSolver> generateObjective() {
    return new SEE_a_MinimizeMakespan();
  }

  @Override
  protected List<IConstraintManager<? super ISeeModel_Constants, MPVariable, MPSolver>> generateConstraints() {
    return List.of(
        new SEE_b_InitialEventTime<>(),
        new SEE_c_EventTimeOrder<>(),
        new SEE_d_EventTimeDefinition<>(),
        new SEE_e_OneStartEvent<>(),
        new SEE_f_OneEndEvent<>(),
        new SEE_h_Precedences<>(),
        new SEE_g_StartBeforeEnd<>(),
        new SEE_i_InitialResourceRequirements<>(),
        new SEE_j_ResourceConservation<>(),
        new SEE_k_NonrenewableResourceCapacity<>(),
        new SEE_o_RenewableResourceCapacity<>(),
        new SEE_TWa_StartTime_LB<>(),
        new SEE_TWa_StartTime_UB<>(),
        new SEE_TWb_CompletionTime_LB<>(),
        new SEE_TWb_CompletionTime_UB<>(),
        new SEE_MC_ModeConsistency<>(),
        new SEE_VFa_EliminateAssignments<>(),
        new SEE_VFb_EliminateAssignments<>(),
        new SEE_TWc_Makespan_LB<>(),
        new SEE_TWc_Makespan_UB<>()
    );
  }
}
