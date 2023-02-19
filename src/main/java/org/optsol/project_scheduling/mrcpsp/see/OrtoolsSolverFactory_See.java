package org.optsol.project_scheduling.mrcpsp.see;

import org.optsol.jdecor.ortools.AbstractOrtoolsModelFactory;
import org.optsol.jdecor.ortools.OrtoolsSolver;
import org.optsol.jdecor.ortools.SolverEngine;
import org.optsol.project_scheduling.mrcpsp.MrcpspModelType;
import org.optsol.project_scheduling.mrcpsp.see.constants.ISeeModel_Constants;
import org.optsol.project_scheduling.mrcpsp.see.models.MrcpspModel_See_TW_VF;
import org.optsol.project_scheduling.mrcpsp.see.models.MrcpspModel_See_TW_VF_aggr;
import org.optsol.project_scheduling.mrcpsp.see.solution.ISee_Solution;

public class OrtoolsSolverFactory_See {

  public static MrcpspSolver_See createMrcpspSolver(
      SolverEngine solverEngine,
      MrcpspModelType modelType,
      int timeLimitSeconds) {
    return new MrcpspSolver_See(solverEngine, modelType, timeLimitSeconds);
  }

  public static String getLpString(
      MrcpspModelType modelType,
      ISeeModel_Constants constants)
      throws Exception {
    return
        MrcpspSolver_See.getOrtoolsModelFactory(SolverEngine.SCIP, modelType)
            .buildModel(constants)
            .getSolver()
            .exportModelAsLpFormat();
  }

  public static class MrcpspSolver_See extends OrtoolsSolver<ISeeModel_Constants, ISee_Solution> {

    MrcpspSolver_See(
        SolverEngine solverEngine,
        MrcpspModelType modelType,
        int timeLimitSeconds) {
      super(timeLimitSeconds, getOrtoolsModelFactory(solverEngine, modelType), ISee_Solution.class);
    }

    static AbstractOrtoolsModelFactory<ISeeModel_Constants> getOrtoolsModelFactory(
        SolverEngine solverEngine, MrcpspModelType modelType) {
      switch (modelType) {
        case SEE_A_TW_VF:
          return new MrcpspModel_See_TW_VF_aggr(solverEngine);
        case SEE_TW_VF:
          return new MrcpspModel_See_TW_VF(solverEngine);
        default:
          throw new IllegalStateException("Unexpected SEE model type: " + modelType);
      }
    }
  }
}
