package org.optsol.project_scheduling.mrcpsp.validation.rsee;

import org.optsol.project_scheduling.mrcpsp.see.constants.ISeeModel_Constants;
import org.optsol.project_scheduling.mrcpsp.see.solution.IRsee_Solution;
import org.optsol.project_scheduling.utils.validation.IValidityChecks;

public class RseeNonRenewableCapacityUsage implements
    IValidityChecks<ISeeModel_Constants, IRsee_Solution> {

  @Override
  public boolean isValid(ISeeModel_Constants constants, IRsee_Solution solution) {
    Boolean[][][] x_tilde = solution.get_x_tilde();
    for (int k : constants.get_N()) {
      int totalCapacityRequested = 0;
      for (int i : constants.get_A()) {
        for (int m : constants.get_M_i(i)) {
          if (x_tilde[i][m][constants.get_card_A() - 1]) {
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
