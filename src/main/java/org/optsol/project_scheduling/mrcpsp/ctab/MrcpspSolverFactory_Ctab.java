package org.optsol.project_scheduling.mrcpsp.ctab;

import org.optsol.jdecor.ortools.AbstractOrtoolsModelFactory;
import org.optsol.jdecor.ortools.OrtoolsSolver;
import org.optsol.jdecor.ortools.SolverEngine;
import org.optsol.project_scheduling.mrcpsp.MrcpspModelType;
import org.optsol.project_scheduling.mrcpsp.ctab.constants.ICtabModel_Constants;
import org.optsol.project_scheduling.mrcpsp.ctab.models.MrcpspModel_Mctab_Ext;
import org.optsol.project_scheduling.mrcpsp.ctab.solution.ICtab_Solution;

public class MrcpspSolverFactory_Ctab {

  public static MrcpspSolver_Ctab createMrcpspSolver(
      SolverEngine solverEngine,
      MrcpspModelType modelType,
      int timeLimitSeconds) {
    return new MrcpspSolver_Ctab(solverEngine, modelType, timeLimitSeconds);
  }

  public static String getLpString(
      MrcpspModelType modelType,
      ICtabModel_Constants constants)
      throws Exception {
    return
        MrcpspSolver_Ctab.getModelFactory(SolverEngine.SCIP, modelType)
            .buildModel(constants)
            .getSolver()
            .exportModelAsLpFormat();
  }

  public static class MrcpspSolver_Ctab extends
                                        OrtoolsSolver<ICtabModel_Constants, ICtab_Solution> {

    private MrcpspSolver_Ctab(
        SolverEngine solverEngine,
        MrcpspModelType modelType,
        int timeLimitSeconds) {
      super(timeLimitSeconds, getModelFactory(solverEngine, modelType), ICtab_Solution.class);
    }

    static AbstractOrtoolsModelFactory<ICtabModel_Constants> getModelFactory(
        SolverEngine solverEngine,
        MrcpspModelType modelType) {

      switch (modelType) {
        case MCTAB_EXT:
          return new MrcpspModel_Mctab_Ext(solverEngine);
        default:
          throw new IllegalStateException("Unexpected value for modelType: " + modelType);
      }
    }
  }
}
