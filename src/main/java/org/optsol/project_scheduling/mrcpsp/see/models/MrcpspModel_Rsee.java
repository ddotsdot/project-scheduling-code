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
import org.optsol.project_scheduling.mrcpsp.see.constraints.rsee.RSEE_b_InitialEventTime;
import org.optsol.project_scheduling.mrcpsp.see.constraints.rsee.RSEE_c_EventTimeOrder;
import org.optsol.project_scheduling.mrcpsp.see.constraints.rsee.RSEE_d_EventTimeDefinition;
import org.optsol.project_scheduling.mrcpsp.see.constraints.rsee.RSEE_e_ForceStart;
import org.optsol.project_scheduling.mrcpsp.see.constraints.rsee.RSEE_f_ForceEnd;
import org.optsol.project_scheduling.mrcpsp.see.constraints.rsee.RSEE_g_MonotoneStartVars;
import org.optsol.project_scheduling.mrcpsp.see.constraints.rsee.RSEE_h_MonotoneEndVars;
import org.optsol.project_scheduling.mrcpsp.see.constraints.rsee.RSEE_i_StartBeforeEnd_dis;
import org.optsol.project_scheduling.mrcpsp.see.constraints.rsee.RSEE_j_Precedences;
import org.optsol.project_scheduling.mrcpsp.see.constraints.rsee.RSEE_k_RenewableResourceCapacity;
import org.optsol.project_scheduling.mrcpsp.see.constraints.rsee.RSEE_l_NonrenewableResourceCapacity;
import org.optsol.project_scheduling.mrcpsp.see.constraints.rsee.RSEE_m_ModeConsistency;
import org.optsol.project_scheduling.mrcpsp.see.objective.RSEE_a_MinimizeMakespan;
import org.optsol.project_scheduling.mrcpsp.see.variables.DecisionVars_Rsee;

public class MrcpspModel_Rsee extends
    AbstractOrtoolsModelFactory<ISeeModel_Constants> {

  public MrcpspModel_Rsee(SolverEngine solverEngine) {
    super(solverEngine);
  }

  @Override
  protected AbstractVariableManager<MPSolver, MPVariable> generateVarManager() {
    return new OrtoolsVariableManager.Builder()

        // s : real+ (time of occurrence of start-end event e)
        .addLowerBound(DecisionVars_Rsee.s, 0.)

        // x_tilde : bool (is activity i starting in mode m until event e)
        .addIntVar(DecisionVars_Rsee.x_tilde)
        .addLowerBound(DecisionVars_Rsee.x_tilde, 0.)
        .addUpperBound(DecisionVars_Rsee.x_tilde, 1.)

        // y_tilde : bool (is activity i finishing in mode m until event e)
        .addIntVar(DecisionVars_Rsee.y_tilde)
        .addLowerBound(DecisionVars_Rsee.y_tilde, 0.)
        .addUpperBound(DecisionVars_Rsee.y_tilde, 1.)

        .build();
  }

  @Override
  protected IObjectiveManager<? super ISeeModel_Constants, MPVariable, MPSolver> generateObjective() {
    return new RSEE_a_MinimizeMakespan();
  }

  @Override
  protected List<IConstraintManager<? super ISeeModel_Constants, MPVariable, MPSolver>> generateConstraints() {
    return List.of(
        new RSEE_b_InitialEventTime<>(),
        new RSEE_c_EventTimeOrder<>(),
        new RSEE_d_EventTimeDefinition<>(),
        new RSEE_e_ForceStart<>(),
        new RSEE_f_ForceEnd<>(),
        new RSEE_g_MonotoneStartVars<>(),
        new RSEE_h_MonotoneEndVars<>(),
        new RSEE_i_StartBeforeEnd_dis<>(),
        new RSEE_j_Precedences<>(),
        new RSEE_k_RenewableResourceCapacity<>(),
        new RSEE_l_NonrenewableResourceCapacity<>(),
        new RSEE_m_ModeConsistency<>()
    );
  }
}
