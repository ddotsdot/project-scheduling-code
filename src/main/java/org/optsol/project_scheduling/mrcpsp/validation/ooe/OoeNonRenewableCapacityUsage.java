package org.optsol.project_scheduling.mrcpsp.validation.ooe;

import org.optsol.project_scheduling.mrcpsp.ooe.constants.IOoeModel_Constants;
import org.optsol.project_scheduling.mrcpsp.ooe.solution.IOoe_Solution;
import org.optsol.project_scheduling.utils.validation.IValidityChecks;

public class OoeNonRenewableCapacityUsage implements
    IValidityChecks<IOoeModel_Constants, IOoe_Solution> {

  @Override
  public boolean isValid(IOoeModel_Constants constants, IOoe_Solution solution) {
    Double[][] r = solution.get_r();
    for (int k : constants.get_N()) {
      int totalCapacityRequested = 0;
      for (int i : constants.get_A()) {
        totalCapacityRequested += r[i][k];
      }
      if (totalCapacityRequested > constants.get_W_k(k)) {
        return false;
      }
    }
    return true;
  }
}
