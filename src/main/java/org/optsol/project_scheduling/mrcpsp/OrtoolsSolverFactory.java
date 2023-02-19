package org.optsol.project_scheduling.mrcpsp;


import org.optsol.jdecor.core.ISolution;
import org.optsol.jdecor.ortools.SolverEngine;
import org.optsol.project_scheduling.mrcpsp.constants.IMrcpsp_Constants;
import org.optsol.project_scheduling.mrcpsp.ctab.MrcpspSolverFactory_Ctab;
import org.optsol.project_scheduling.mrcpsp.fct.MrcpspSolverFactory_Fct;
import org.optsol.project_scheduling.mrcpsp.ooe.MrcpspSolverFactory_Ooe;
import org.optsol.project_scheduling.mrcpsp.see.OrtoolsSolverFactory_Rsee;
import org.optsol.project_scheduling.mrcpsp.see.OrtoolsSolverFactory_See;

public class OrtoolsSolverFactory {

  public static String getLpString(
      MrcpspModelType modelType,
      IMrcpsp_Constants constantsBase) throws Exception {

    switch (modelType) {
      case SEE_TW_VF:
      case SEE_A_TW_VF:
        return OrtoolsSolverFactory_See.getLpString(
            modelType,
            MrcpspConstantsFactory.createSeeConstants(constantsBase));
      case MCTAB_EXT:
        return MrcpspSolverFactory_Ctab.getLpString(
            modelType,
            MrcpspConstantsFactory.createCtabConstants(constantsBase));
      case OOE_A_TW_VF:
      case OOE_TW_VF:
        return MrcpspSolverFactory_Ooe.getLpString(
            modelType,
            MrcpspConstantsFactory.createOoeConstants(constantsBase));
      case RSEE:
        return OrtoolsSolverFactory_Rsee.getLpString(
            modelType,
            MrcpspConstantsFactory.createSeeConstants(constantsBase));
      case FCT_OZON:
      case FCT_W_TW:
      case FCT_W_TW_RC:
      case FCT_S_TW:
      default:
        throw new IllegalStateException("Unexpected model type: " + modelType);
    }
  }

  public static ISolution generateMrcpspSolution(
      MrcpspModelType modelType,
      IMrcpsp_Constants constantsBase,
      SolverEngine solverEngine,
      int timeLimitSeconds) throws Exception {

    switch (modelType) {
      case SEE_TW_VF:
      case SEE_A_TW_VF:
        return
            OrtoolsSolverFactory_See.createMrcpspSolver(
                    solverEngine, modelType, timeLimitSeconds)
                .generateSolution(MrcpspConstantsFactory.createSeeConstants(constantsBase));
      case MCTAB_EXT:
        return
            MrcpspSolverFactory_Ctab.createMrcpspSolver(
                    solverEngine, modelType, timeLimitSeconds)
                .generateSolution(MrcpspConstantsFactory.createCtabConstants(constantsBase));
      case OOE_A_TW_VF:
      case OOE_TW_VF:
        return
            MrcpspSolverFactory_Ooe.createMrcpspSolver(
                    solverEngine, modelType, timeLimitSeconds)
                .generateSolution(MrcpspConstantsFactory.createOoeConstants(constantsBase));
      case RSEE:
        return
            OrtoolsSolverFactory_Rsee.createMrcpspSolver(
                    solverEngine, modelType, timeLimitSeconds)
                .generateSolution(MrcpspConstantsFactory.createSeeConstants(constantsBase));
      case FCT_W_TW:
      case FCT_W_TW_RC:
      case FCT_S_TW:
      case FCT_OZON:
        return
            MrcpspSolverFactory_Fct.createMrcpspSolver(solverEngine, modelType, timeLimitSeconds)
                .generateSolution(MrcpspConstantsFactory.createFctConstants(constantsBase));
      default:
        throw new IllegalStateException("Unexpected model type: " + modelType);
    }
  }
}
