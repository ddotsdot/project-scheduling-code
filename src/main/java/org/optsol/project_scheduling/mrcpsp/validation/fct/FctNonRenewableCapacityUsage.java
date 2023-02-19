package org.optsol.project_scheduling.mrcpsp.validation.fct;

import org.optsol.project_scheduling.mrcpsp.fct.constants.IFctModel_Constants;
import org.optsol.project_scheduling.mrcpsp.fct.solution.IFct_Solution;
import org.optsol.project_scheduling.utils.validation.IValidityChecks;

public class FctNonRenewableCapacityUsage implements
    IValidityChecks<IFctModel_Constants, IFct_Solution> {

  @Override
  public boolean isValid(IFctModel_Constants constants, IFct_Solution solution) {
    Boolean[][] x = solution.get_x();
    for (int k : constants.get_N()) {
      int totalCapacityRequested = 0;
      for (int i : constants.get_A()) {
        for (int m : constants.get_M_i(i)) {
          if (x[i][m]) {
            totalCapacityRequested += constants.get_w_imk(i, m, k);
          }
        }
      }
      if (totalCapacityRequested > constants.get_W_k(k)) {
        return false;
      }
    }
    return true;
  }
}
