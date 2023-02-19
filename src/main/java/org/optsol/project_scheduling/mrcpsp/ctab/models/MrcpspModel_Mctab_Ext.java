package org.optsol.project_scheduling.mrcpsp.ctab.models;

import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import java.util.List;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.jdecor.core.IConstraintManager;
import org.optsol.jdecor.core.IObjectiveManager;
import org.optsol.jdecor.ortools.AbstractOrtoolsModelFactory;
import org.optsol.jdecor.ortools.OrtoolsVariableManager;
import org.optsol.jdecor.ortools.SolverEngine;
import org.optsol.project_scheduling.mrcpsp.ctab.constants.ICtabModel_Constants;
import org.optsol.project_scheduling.mrcpsp.ctab.constraints.CTAB_EXT1_SymmetryReduction;
import org.optsol.project_scheduling.mrcpsp.ctab.constraints.CTAB_EXT2_EnforceSequentialProcessing_Pairs;
import org.optsol.project_scheduling.mrcpsp.ctab.constraints.CTAB_b_ChooseOneMode;
import org.optsol.project_scheduling.mrcpsp.ctab.constraints.CTAB_c_Precedences;
import org.optsol.project_scheduling.mrcpsp.ctab.constraints.CTAB_d_ResourceUnitAssignment;
import org.optsol.project_scheduling.mrcpsp.ctab.constraints.CTAB_e_NoOverlapInAssignedCapacityUnits;
import org.optsol.project_scheduling.mrcpsp.ctab.constraints.CTAB_f_SequentialProcessing;
import org.optsol.project_scheduling.mrcpsp.ctab.constraints.CTAB_g_NonRenewableResourceCapacity;
import org.optsol.project_scheduling.mrcpsp.ctab.objective.Ctab_MinimizeMakespan;
import org.optsol.project_scheduling.mrcpsp.ctab.variables.DecisionVars_Ctab;

public class MrcpspModel_Mctab_Ext extends AbstractOrtoolsModelFactory<ICtabModel_Constants> {

  public MrcpspModel_Mctab_Ext(SolverEngine solverEngine) {
    super(solverEngine);
  }

  @Override
  protected AbstractVariableManager<MPSolver, MPVariable> generateVarManager() {
    return new OrtoolsVariableManager.Builder()

        // s : real+ (time of occurrence of start-end event e)
        .addLowerBound(DecisionVars_Ctab.s, 0.)

        // x : bool (is activity i assigned to mode m)
        .addIntVar(DecisionVars_Ctab.x)
        .addLowerBound(DecisionVars_Ctab.x, 0.)
        .addUpperBound(DecisionVars_Ctab.x, 1.)

        // y : bool (=1 if activity i must complete before activity j starts)
        .addIntVar(DecisionVars_Ctab.y)
        .addLowerBound(DecisionVars_Ctab.y, 0.)
        .addUpperBound(DecisionVars_Ctab.y, 1.)

        // r : bool (is resource unit l of renewable resource k assigned to activity i)
        .addIntVar(DecisionVars_Ctab.r)
        .addLowerBound(DecisionVars_Ctab.r, 0.)
        .addUpperBound(DecisionVars_Ctab.r, 1.)

        .build();
  }

  @Override
  protected IObjectiveManager<? super ICtabModel_Constants, MPVariable, MPSolver> generateObjective() {
    return new Ctab_MinimizeMakespan<>();
  }

  @Override
  protected List<IConstraintManager<? super ICtabModel_Constants, MPVariable, MPSolver>> generateConstraints() {
    return List.of(
        new CTAB_b_ChooseOneMode<>(),
        new CTAB_c_Precedences<>(),
        new CTAB_d_ResourceUnitAssignment<>(),
        new CTAB_e_NoOverlapInAssignedCapacityUnits<>(),
        new CTAB_f_SequentialProcessing<>(),
        new CTAB_g_NonRenewableResourceCapacity<>(),
        new CTAB_EXT1_SymmetryReduction<>(),
        new CTAB_EXT2_EnforceSequentialProcessing_Pairs<>()
    );
  }
}
