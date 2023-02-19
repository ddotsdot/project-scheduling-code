package org.optsol.project_scheduling.mrcpsp.fct.models;

import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import java.util.List;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.jdecor.core.IConstraintManager;
import org.optsol.jdecor.core.IObjectiveManager;
import org.optsol.jdecor.ortools.AbstractOrtoolsModelFactory;
import org.optsol.jdecor.ortools.OrtoolsVariableManager;
import org.optsol.jdecor.ortools.SolverEngine;
import org.optsol.project_scheduling.mrcpsp.fct.constants.IFctModel_Constants;
import org.optsol.project_scheduling.mrcpsp.fct.constraints.FCT_TW_StartingTimeBounds;
import org.optsol.project_scheduling.mrcpsp.fct.constraints.FCT_b_ChooseOneMode;
import org.optsol.project_scheduling.mrcpsp.fct.constraints.FCT_d_UniqueOrderIfSequentially;
import org.optsol.project_scheduling.mrcpsp.fct.constraints.FCT_e_Transitivity;
import org.optsol.project_scheduling.mrcpsp.fct.constraints.FCT_g_FlowConservationOutgoing;
import org.optsol.project_scheduling.mrcpsp.fct.constraints.FCT_h_FlowConservationIncoming;
import org.optsol.project_scheduling.mrcpsp.fct.constraints.FCT_i_FixInitialFlows;
import org.optsol.project_scheduling.mrcpsp.fct.constraints.FCT_j_NoFlowAlongLoops;
import org.optsol.project_scheduling.mrcpsp.fct.constraints.FCT_k_FixTransitivePrecedences;
import org.optsol.project_scheduling.mrcpsp.fct.constraints.FCT_l_FixTransitivePrecedences;
import org.optsol.project_scheduling.mrcpsp.fct.constraints.FCT_m_NoSelfPrecedence;
import org.optsol.project_scheduling.mrcpsp.fct.constraints.FCT_n_NonrenewableResourceCapacity;
import org.optsol.project_scheduling.mrcpsp.fct.constraints.FCT_o_ProjectStart;
import org.optsol.project_scheduling.mrcpsp.fct.constraints.ozon.FCT_c1_StartingTimesDef_Ws;
import org.optsol.project_scheduling.mrcpsp.fct.constraints.ozon.FCT_c2_StartingTimesDef_Ws;
import org.optsol.project_scheduling.mrcpsp.fct.constraints.ozon.FCT_c3_StartingTimesDef;
import org.optsol.project_scheduling.mrcpsp.fct.constraints.ozon.FCT_c4_StartingTimesDef_Ws;
import org.optsol.project_scheduling.mrcpsp.fct.constraints.strong.FCT_f1_FlowUpperBound_Strong_beta;
import org.optsol.project_scheduling.mrcpsp.fct.constraints.strong.FCT_f1_FlowUpperBound_Strong_gamma;
import org.optsol.project_scheduling.mrcpsp.fct.constraints.strong.FCT_f2_FlowUpperBound_Strong_beta;
import org.optsol.project_scheduling.mrcpsp.fct.constraints.strong.FCT_f2_FlowUpperBound_Strong_gamma;
import org.optsol.project_scheduling.mrcpsp.fct.constraints.strong.FCT_f3_FlowUpperBound_Strong_beta;
import org.optsol.project_scheduling.mrcpsp.fct.constraints.strong.FCT_f3_FlowUpperBound_Strong_gamma;
import org.optsol.project_scheduling.mrcpsp.fct.constraints.strong.FCT_f4_FlowUpperBound_Strong_beta;
import org.optsol.project_scheduling.mrcpsp.fct.constraints.strong.FCT_f4_FlowUpperBound_Strong_gamma;
import org.optsol.project_scheduling.mrcpsp.fct.objective.Fct_MinimizeMakespan;
import org.optsol.project_scheduling.mrcpsp.fct.variables.DecisionVars_Fct;

public class MrcpspModel_Fct_Strong_TW extends
    AbstractOrtoolsModelFactory<IFctModel_Constants> {

  public MrcpspModel_Fct_Strong_TW(SolverEngine solverEngine) {
    super(solverEngine);
  }

  @Override
  protected AbstractVariableManager<MPSolver, MPVariable> generateVarManager() {
    return new OrtoolsVariableManager.Builder()

        // x : bool (activity i is processed in mode m)
        .addIntVar(DecisionVars_Fct.x)
        .addLowerBound(DecisionVars_Fct.x, 0.)
        .addUpperBound(DecisionVars_Fct.x, 1.)

        // y : bool (is activity i is processed before activity j)
        .addIntVar(DecisionVars_Fct.y)
        .addLowerBound(DecisionVars_Fct.y, 0.)
        .addUpperBound(DecisionVars_Fct.y, 1.)

        // f : real+ (capacity of resource k transferred from activity i to activity j)
        .addLowerBound(DecisionVars_Fct.f, 0.)

        // s : real+ (time that activity i starts processing)
        .addLowerBound(DecisionVars_Fct.s, 0.)

        // beta : real+ (auxiliary variable)
        .addLowerBound(DecisionVars_Fct.beta, 0.)

        // gamma : real+ (auxiliary variable)
        .addLowerBound(DecisionVars_Fct.gamma, 0.)

        // delta : real+ (auxiliary variable)
        .addLowerBound(DecisionVars_Fct.delta, 0.)

        .build();
  }

  @Override
  protected IObjectiveManager<? super IFctModel_Constants, MPVariable, MPSolver> generateObjective() {
    return new Fct_MinimizeMakespan();
  }

  @Override
  protected List<IConstraintManager<? super IFctModel_Constants, MPVariable, MPSolver>> generateConstraints() {
    return List.of(
        new FCT_b_ChooseOneMode<>(),
        new FCT_c1_StartingTimesDef_Ws<>(),
        new FCT_c2_StartingTimesDef_Ws<>(),
        new FCT_c3_StartingTimesDef<>(),
        new FCT_c4_StartingTimesDef_Ws<>(),
        new FCT_d_UniqueOrderIfSequentially<>(),
        new FCT_e_Transitivity<>(),
        new FCT_f1_FlowUpperBound_Strong_beta<>(),
        new FCT_f1_FlowUpperBound_Strong_gamma<>(),
        new FCT_f2_FlowUpperBound_Strong_beta<>(),
        new FCT_f2_FlowUpperBound_Strong_gamma<>(),
        new FCT_f3_FlowUpperBound_Strong_beta<>(),
        new FCT_f3_FlowUpperBound_Strong_gamma<>(),
        new FCT_f4_FlowUpperBound_Strong_beta<>(),
        new FCT_f4_FlowUpperBound_Strong_gamma<>(),
        new FCT_g_FlowConservationOutgoing<>(),
        new FCT_h_FlowConservationIncoming<>(),
        new FCT_i_FixInitialFlows<>(),
        new FCT_k_FixTransitivePrecedences<>(),
        new FCT_l_FixTransitivePrecedences<>(),
        new FCT_m_NoSelfPrecedence<>(),
        new FCT_n_NonrenewableResourceCapacity<>(),
        new FCT_o_ProjectStart<>(),
        new FCT_j_NoFlowAlongLoops<>(),
        new FCT_TW_StartingTimeBounds<>()
    );
  }
}
