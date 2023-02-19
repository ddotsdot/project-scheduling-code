package org.optsol.project_scheduling.mrcpsp.validation.fct;

import org.optsol.project_scheduling.mrcpsp.fct.constants.IFctModel_Constants;
import org.optsol.project_scheduling.mrcpsp.fct.solution.IFct_Solution;
import org.optsol.project_scheduling.utils.validation.IValidityChecks;

public class FctModeConsistency implements IValidityChecks<IFctModel_Constants, IFct_Solution> {

  @Override
  public boolean isValid(IFctModel_Constants constants, IFct_Solution solution) {
    Boolean[][] x = solution.get_x();
    for (int i : constants.get_A()) {
      int countSelectedModes = 0;
      for (int m : constants.get_M_i(i)) {
        if (x[i][m]) {
          countSelectedModes++;
        }
      }
      if (countSelectedModes > 1) {
        return false;
      }
    }
    return true;
  }
}
