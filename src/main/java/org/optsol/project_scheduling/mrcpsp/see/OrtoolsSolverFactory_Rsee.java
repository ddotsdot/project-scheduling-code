package org.optsol.project_scheduling.mrcpsp.see;

import org.optsol.jdecor.ortools.AbstractOrtoolsModelFactory;
import org.optsol.jdecor.ortools.OrtoolsSolver;
import org.optsol.jdecor.ortools.SolverEngine;
import org.optsol.project_scheduling.mrcpsp.MrcpspModelType;
import org.optsol.project_scheduling.mrcpsp.see.constants.ISeeModel_Constants;
import org.optsol.project_scheduling.mrcpsp.see.models.MrcpspModel_Rsee;
import org.optsol.project_scheduling.mrcpsp.see.solution.IRsee_Solution;

public class OrtoolsSolverFactory_Rsee {

  public static MrcpspSolver_Rsee createMrcpspSolver(
      SolverEngine solverEngine,
      MrcpspModelType modelType,
      int timeLimitSeconds) {
    return new MrcpspSolver_Rsee(solverEngine, modelType, timeLimitSeconds);
  }

  public static String getLpString(
      MrcpspModelType modelType,
      ISeeModel_Constants constants) throws Exception {
    return
        MrcpspSolver_Rsee.getOrtoolsModelFactory(SolverEngine.SCIP, modelType)
            .buildModel(constants)
            .getSolver()
            .exportModelAsLpFormat();
  }


  public static class MrcpspSolver_Rsee extends OrtoolsSolver<ISeeModel_Constants, IRsee_Solution> {

    MrcpspSolver_Rsee(
        SolverEngine solverEngine,
        MrcpspModelType modelType,
        int timeLimitSeconds) {
      super(timeLimitSeconds,
          getOrtoolsModelFactory(solverEngine, modelType),
          IRsee_Solution.class);
    }

    static AbstractOrtoolsModelFactory<ISeeModel_Constants> getOrtoolsModelFactory(
        SolverEngine solverEngine, MrcpspModelType modelType) {
      switch (modelType) {
        case RSEE:
          return new MrcpspModel_Rsee(solverEngine);
        default:
          throw new IllegalStateException("Unexpected RSEE model type: " + modelType);
      }
    }
  }
}
