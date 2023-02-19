package org.optsol.project_scheduling.mrcpsp.validation;

import lombok.extern.slf4j.Slf4j;
import org.optsol.jdecor.core.ISolution;
import org.optsol.project_scheduling.mrcpsp.MrcpspConstantsFactory;
import org.optsol.project_scheduling.mrcpsp.MrcpspModelType;
import org.optsol.project_scheduling.mrcpsp.constants.IMrcpsp_Constants;
import org.optsol.project_scheduling.mrcpsp.fct.solution.IFct_Solution;
import org.optsol.project_scheduling.mrcpsp.ooe.solution.IOoe_Solution;
import org.optsol.project_scheduling.mrcpsp.see.solution.IRsee_Solution;
import org.optsol.project_scheduling.mrcpsp.see.solution.ISee_Solution;

@Slf4j
public class ValidityChecksResultFactory {

  public static boolean getValidityChecksResult(
      MrcpspModelType modelType,
      IMrcpsp_Constants constantsBase,
      ISolution solution) {
    switch (modelType) {
      case MCTAB_EXT:
        log.info("No validity checks present.");
        return true;
      case FCT_W_TW:
      case FCT_W_TW_RC:
      case FCT_S_TW:
      case FCT_OZON:
        return new FctSolutionValidityChecks().isValid(
            MrcpspConstantsFactory.createFctConstants(constantsBase),
            (IFct_Solution) solution);
      case OOE_A_TW_VF:
      case OOE_TW_VF:
        return new OoeSolutionValidityChecks().isValid(
            MrcpspConstantsFactory.createOoeConstants(constantsBase),
            (IOoe_Solution) solution
        );
      case RSEE:
        return new RseeSolutionValidityChecks().isValid(
            MrcpspConstantsFactory.createSeeConstants(constantsBase),
            (IRsee_Solution) solution
        );
      case SEE_A_TW_VF:
      case SEE_TW_VF:
        return new SeeSolutionValidityChecks().isValid(
            MrcpspConstantsFactory.createSeeConstants(constantsBase),
            (ISee_Solution) solution
        );
      default:
        throw new IllegalStateException("Unexpected modelType: " + modelType);
    }
  }
}
