package org.optsol.project_scheduling.mrcpsp.fct;

import org.optsol.jdecor.ortools.AbstractOrtoolsModelFactory;
import org.optsol.jdecor.ortools.OrtoolsSolver;
import org.optsol.jdecor.ortools.SolverEngine;
import org.optsol.project_scheduling.mrcpsp.MrcpspModelType;
import org.optsol.project_scheduling.mrcpsp.fct.constants.IFctModel_Constants;
import org.optsol.project_scheduling.mrcpsp.fct.models.MrcpspModel_Fct_OzturkOner;
import org.optsol.project_scheduling.mrcpsp.fct.models.MrcpspModel_Fct_Strong_TW;
import org.optsol.project_scheduling.mrcpsp.fct.models.MrcpspModel_Fct_Weak_TW;
import org.optsol.project_scheduling.mrcpsp.fct.models.MrcpspModel_Fct_Weak_TW_RC;
import org.optsol.project_scheduling.mrcpsp.fct.solution.IFct_Solution;

public class MrcpspSolverFactory_Fct {

  public static MrcpspSolver_Fct createMrcpspSolver(
      SolverEngine solverEngine, MrcpspModelType modelType, int timeLimitSeconds) {
    return new MrcpspSolver_Fct(solverEngine, modelType, timeLimitSeconds);
  }

  public static String getLpString(
      MrcpspModelType modelType,
      IFctModel_Constants constants)
      throws Exception {
    return MrcpspSolver_Fct.getModelFactory(SolverEngine.SCIP, modelType)
        .buildModel(constants)
        .getSolver()
        .exportModelAsLpFormat();
  }

  public static class MrcpspSolver_Fct extends
      OrtoolsSolver<IFctModel_Constants, IFct_Solution> {

    private MrcpspSolver_Fct(
        SolverEngine solverEngine,
        MrcpspModelType modelType,
        int timeLimitSeconds) {
      super(timeLimitSeconds, getModelFactory(solverEngine, modelType), IFct_Solution.class);
    }

    static AbstractOrtoolsModelFactory<IFctModel_Constants> getModelFactory(
        SolverEngine solverEngine,
        MrcpspModelType modelType) {

      switch (modelType) {
        case FCT_W_TW:
          return new MrcpspModel_Fct_Weak_TW(solverEngine);
        case FCT_W_TW_RC:
          return new MrcpspModel_Fct_Weak_TW_RC(solverEngine);
        case FCT_S_TW:
          return new MrcpspModel_Fct_Strong_TW(solverEngine);
        case FCT_OZON:
          return new MrcpspModel_Fct_OzturkOner(solverEngine);
        default:
          throw new IllegalStateException("Unexpected Fct modelType " + modelType);
      }
    }
  }
}
