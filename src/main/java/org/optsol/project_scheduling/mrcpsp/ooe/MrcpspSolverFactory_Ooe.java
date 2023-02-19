package org.optsol.project_scheduling.mrcpsp.ooe;

import org.optsol.jdecor.ortools.AbstractOrtoolsModelFactory;
import org.optsol.jdecor.ortools.OrtoolsSolver;
import org.optsol.jdecor.ortools.SolverEngine;
import org.optsol.project_scheduling.mrcpsp.MrcpspModelType;
import org.optsol.project_scheduling.mrcpsp.ooe.constants.IOoeModel_Constants;
import org.optsol.project_scheduling.mrcpsp.ooe.models.MrcpspModel_Ooe_Aggr_TW_VF;
import org.optsol.project_scheduling.mrcpsp.ooe.models.MrcpspModel_Ooe_TW_VF;
import org.optsol.project_scheduling.mrcpsp.ooe.solution.IOoe_Solution;

public class MrcpspSolverFactory_Ooe {

  public static MrcpspSolver_Ooe createMrcpspSolver(
      SolverEngine solverEngine,
      MrcpspModelType modelType,
      int timeLimitSeconds) {
    return new MrcpspSolver_Ooe(solverEngine, modelType, timeLimitSeconds);
  }

  public static String getLpString(
      MrcpspModelType modelType,
      IOoeModel_Constants constants)
      throws Exception {
    return
        MrcpspSolver_Ooe.getOrtoolsModelFactory(SolverEngine.SCIP, modelType)
            .buildModel(constants)
            .getSolver()
            .exportModelAsLpFormat();
  }

  public static class MrcpspSolver_Ooe extends OrtoolsSolver<IOoeModel_Constants, IOoe_Solution> {

    private MrcpspSolver_Ooe(
        SolverEngine solverEngine,
        MrcpspModelType modelType,
        int timeLimitSeconds) {
      super(timeLimitSeconds, getOrtoolsModelFactory(solverEngine, modelType), IOoe_Solution.class);
    }

    static AbstractOrtoolsModelFactory<IOoeModel_Constants> getOrtoolsModelFactory(
        SolverEngine solverEngine,
        MrcpspModelType modelType) {
      switch (modelType) {
        case OOE_A_TW_VF:
          return new MrcpspModel_Ooe_Aggr_TW_VF(solverEngine);
        case OOE_TW_VF:
          return new MrcpspModel_Ooe_TW_VF(solverEngine);
        default:
          throw new IllegalStateException("Unexpected OOE model type: " + modelType);
      }
    }
  }
}