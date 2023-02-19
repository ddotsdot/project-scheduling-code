package org.optsol.project_scheduling.mrcpsp.validation.see;

import org.optsol.project_scheduling.mrcpsp.see.constants.ISeeModel_Constants;
import org.optsol.project_scheduling.mrcpsp.see.solution.ISee_Solution;
import org.optsol.project_scheduling.utils.validation.IValidityChecks;

public class SeeModeConsistency implements IValidityChecks<ISeeModel_Constants, ISee_Solution> {

  @Override
  public boolean isValid(ISeeModel_Constants constants, ISee_Solution solution) {
    Boolean[][][] x = solution.get_x();
    Boolean[][][] y = solution.get_y();
    for (int i : constants.get_A()) {
      for (int m : constants.get_M_i(i)) {
        for (int e : constants.get_E()) {
          if (x[i][m][e]) {
            for (int m2 : constants.get_M_i(i)) {
              for (int f : constants.get_E()) {
                if (f > e && m2 != m && y[i][m2][f]) {
                  // invalid if activity i starts in mode m and finishes in mode m2 != m
                  return false;
                }
              }
            }
          }
        }
      }
    }
    return true;
  }
}
