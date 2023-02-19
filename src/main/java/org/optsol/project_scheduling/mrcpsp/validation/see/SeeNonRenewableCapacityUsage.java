package org.optsol.project_scheduling.mrcpsp.validation.see;

import org.optsol.project_scheduling.mrcpsp.see.constants.ISeeModel_Constants;
import org.optsol.project_scheduling.mrcpsp.see.solution.ISee_Solution;
import org.optsol.project_scheduling.utils.validation.IValidityChecks;

public class SeeNonRenewableCapacityUsage implements
    IValidityChecks<ISeeModel_Constants, ISee_Solution> {

  @Override
  public boolean isValid(ISeeModel_Constants constants, ISee_Solution solution) {
    Boolean[][][] x = solution.get_x();
    for (int k : constants.get_N()) {
      int totalCapacityRequested = 0;
      for (int i : constants.get_A()) {
        for (int m : constants.get_M_i(i)) {
          for (int e : constants.get_E()) {
            if (x[i][m][e]) {
              totalCapacityRequested += constants.get_w_imk(i, m, k);
            }
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
