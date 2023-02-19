package org.optsol.project_scheduling.mrcpsp.ooe.models;

import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import java.util.List;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.jdecor.core.IConstraintManager;
import org.optsol.jdecor.core.IObjectiveManager;
import org.optsol.jdecor.ortools.AbstractOrtoolsModelFactory;
import org.optsol.jdecor.ortools.OrtoolsVariableManager;
import org.optsol.jdecor.ortools.SolverEngine;
import org.optsol.project_scheduling.mrcpsp.ooe.constants.IOoeModel_Constants;
import org.optsol.project_scheduling.mrcpsp.ooe.constraints.OOE_MC_ModeConsistency;
import org.optsol.project_scheduling.mrcpsp.ooe.constraints.OOE_TWa_StartTime_LB;
import org.optsol.project_scheduling.mrcpsp.ooe.constraints.OOE_TWa_StartTime_UB;
import org.optsol.project_scheduling.mrcpsp.ooe.constraints.OOE_TWb_CompletionTime_LB;
import org.optsol.project_scheduling.mrcpsp.ooe.constraints.OOE_TWb_CompletionTime_UB;
import org.optsol.project_scheduling.mrcpsp.ooe.constraints.OOE_TWc_Makespan_LB;
import org.optsol.project_scheduling.mrcpsp.ooe.constraints.OOE_TWc_Makespan_UB;
import org.optsol.project_scheduling.mrcpsp.ooe.constraints.OOE_VF_EliminateAssignments;
import org.optsol.project_scheduling.mrcpsp.ooe.constraints.OOE_b_InitialEventTime;
import org.optsol.project_scheduling.mrcpsp.ooe.constraints.OOE_c_EventOrder;
import org.optsol.project_scheduling.mrcpsp.ooe.constraints.OOE_d_EventTimeDefinition;
import org.optsol.project_scheduling.mrcpsp.ooe.constraints.OOE_e_AtLeastOneEvent;
import org.optsol.project_scheduling.mrcpsp.ooe.constraints.OOE_f_BackwardContiguity_AGGR;
import org.optsol.project_scheduling.mrcpsp.ooe.constraints.OOE_g_ForwardContiguity_AGGR;
import org.optsol.project_scheduling.mrcpsp.ooe.constraints.OOE_h_Precedences_AGGR;
import org.optsol.project_scheduling.mrcpsp.ooe.constraints.OOE_i_MaxResourceCapacity;
import org.optsol.project_scheduling.mrcpsp.ooe.constraints.OOE_j_NonrenewableCapacityConsumption;
import org.optsol.project_scheduling.mrcpsp.ooe.constraints.OOE_k_NonrenewableResourceMaxCapacity;
import org.optsol.project_scheduling.mrcpsp.ooe.objective.OOE_a_MinimizeMakespan;
import org.optsol.project_scheduling.mrcpsp.ooe.variables.DecisionVars_Ooe;

public class MrcpspModel_Ooe_Aggr_TW_VF extends
    AbstractOrtoolsModelFactory<IOoeModel_Constants> {

  public MrcpspModel_Ooe_Aggr_TW_VF(SolverEngine solverEngine) {
    super(solverEngine);
  }

  @Override
  protected AbstractVariableManager<MPSolver, MPVariable> generateVarManager() {
    return new OrtoolsVariableManager.Builder()

        // s : real+ (time of occurrence of on-off event e)
        .addLowerBound(DecisionVars_Ooe.s, 0.)

        // r : real+ (consumption of nonrenewable resource k by activity i)
        .addLowerBound(DecisionVars_Ooe.r, 0.)

        // z : bool (is activity i being processed in mode m when event e occurs)
        .addIntVar(DecisionVars_Ooe.z)
        .addLowerBound(DecisionVars_Ooe.z, 0.)
        .addUpperBound(DecisionVars_Ooe.z, 1.)

        .build();
  }

  @Override
  protected IObjectiveManager<? super IOoeModel_Constants, MPVariable, MPSolver> generateObjective() {
    return new OOE_a_MinimizeMakespan<>();
  }

  @Override
  protected List<IConstraintManager<? super IOoeModel_Constants, MPVariable, MPSolver>> generateConstraints() {
    return List.of(
        new OOE_b_InitialEventTime<>(),
        new OOE_c_EventOrder<>(),
        new OOE_d_EventTimeDefinition<>(),
        new OOE_e_AtLeastOneEvent<>(),
        new OOE_f_BackwardContiguity_AGGR<>(),
        new OOE_g_ForwardContiguity_AGGR<>(),
        new OOE_MC_ModeConsistency<>(),
        new OOE_h_Precedences_AGGR<>(),
        new OOE_i_MaxResourceCapacity<>(),
        new OOE_j_NonrenewableCapacityConsumption<>(),
        new OOE_k_NonrenewableResourceMaxCapacity<>(),
        new OOE_TWa_StartTime_LB<>(),
        new OOE_TWa_StartTime_UB<>(),
        new OOE_TWb_CompletionTime_LB<>(),
        new OOE_TWb_CompletionTime_UB<>(),
        new OOE_TWc_Makespan_LB<>(),
        new OOE_TWa_StartTime_LB<>(),
        new OOE_TWa_StartTime_UB<>(),
        new OOE_TWb_CompletionTime_LB<>(),
        new OOE_TWb_CompletionTime_UB<>(),
        new OOE_TWc_Makespan_LB<>(),
        new OOE_TWc_Makespan_UB<>(),
        new OOE_VF_EliminateAssignments<>()
    );
  }
}
