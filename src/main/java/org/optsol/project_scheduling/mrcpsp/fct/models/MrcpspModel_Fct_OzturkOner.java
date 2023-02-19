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
import org.optsol.project_scheduling.mrcpsp.fct.constraints.FCT_b_ChooseOneMode;
import org.optsol.project_scheduling.mrcpsp.fct.constraints.FCT_d_UniqueOrderIfSequentially;
import org.optsol.project_scheduling.mrcpsp.fct.constraints.FCT_e_Transitivity;
import org.optsol.project_scheduling.mrcpsp.fct.constraints.FCT_g_FlowConservationOutgoing;
import org.optsol.project_scheduling.mrcpsp.fct.constraints.FCT_h_FlowConservationIncoming;
import org.optsol.project_scheduling.mrcpsp.fct.constraints.FCT_i_FixInitialFlows;
import org.optsol.project_scheduling.mrcpsp.fct.constraints.FCT_j_NoFlowAlongLoops;
import org.optsol.project_scheduling.mrcpsp.fct.constraints.FCT_n_NonrenewableResourceCapacity;
import org.optsol.project_scheduling.mrcpsp.fct.constraints.FCT_o_ProjectStart;
import org.optsol.project_scheduling.mrcpsp.fct.constraints.ozon.FCT_OzOn_FixPrecedences;
import org.optsol.project_scheduling.mrcpsp.fct.constraints.ozon.FCT_c1_StartingTimesDef;
import org.optsol.project_scheduling.mrcpsp.fct.constraints.ozon.FCT_c2_StartingTimesDef;
import org.optsol.project_scheduling.mrcpsp.fct.constraints.ozon.FCT_c3_StartingTimesDef;
import org.optsol.project_scheduling.mrcpsp.fct.constraints.ozon.FCT_c4_StartingTimesDef;
import org.optsol.project_scheduling.mrcpsp.fct.constraints.ozon.FCT_f1_FlowUpperBound;
import org.optsol.project_scheduling.mrcpsp.fct.constraints.ozon.FCT_f2_FlowUpperBound;
import org.optsol.project_scheduling.mrcpsp.fct.constraints.ozon.FCT_f3_FlowUpperBound;
import org.optsol.project_scheduling.mrcpsp.fct.constraints.ozon.FCT_f4_FlowUpperBound;
import org.optsol.project_scheduling.mrcpsp.fct.objective.Fct_MinimizeMakespan;
import org.optsol.project_scheduling.mrcpsp.fct.variables.DecisionVars_Fct;

public class MrcpspModel_Fct_OzturkOner extends AbstractOrtoolsModelFactory<IFctModel_Constants> {

  public MrcpspModel_Fct_OzturkOner(SolverEngine solverEngine) {
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

        // alpha : real+ (auxiliary variable)
        .addLowerBound(DecisionVars_Fct.alpha, 0.)

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
        new FCT_c1_StartingTimesDef<>(),
        new FCT_c2_StartingTimesDef<>(),
        new FCT_c3_StartingTimesDef<>(),
        new FCT_c4_StartingTimesDef<>(),
        new FCT_d_UniqueOrderIfSequentially<>(),
        new FCT_e_Transitivity<>(),
        new FCT_f1_FlowUpperBound<>(),
        new FCT_f2_FlowUpperBound<>(),
        new FCT_f3_FlowUpperBound<>(),
        new FCT_f4_FlowUpperBound<>(),
        new FCT_g_FlowConservationOutgoing<>(),
        new FCT_h_FlowConservationIncoming<>(),
        new FCT_i_FixInitialFlows<>(),
        new FCT_OzOn_FixPrecedences<>(),
        new FCT_n_NonrenewableResourceCapacity<>(),
        new FCT_o_ProjectStart<>(),
        new FCT_j_NoFlowAlongLoops<>()
    );
  }
}
