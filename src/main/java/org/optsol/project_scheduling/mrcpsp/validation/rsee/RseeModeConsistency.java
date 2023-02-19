package org.optsol.project_scheduling.mrcpsp.validation.rsee;

import org.optsol.project_scheduling.mrcpsp.see.constants.ISeeModel_Constants;
import org.optsol.project_scheduling.mrcpsp.see.solution.IRsee_Solution;
import org.optsol.project_scheduling.utils.validation.IValidityChecks;

public class RseeModeConsistency implements IValidityChecks<ISeeModel_Constants, IRsee_Solution> {

  @Override
  public boolean isValid(ISeeModel_Constants constants, IRsee_Solution solution) {
    Boolean[][][] x_tilde = solution.get_x_tilde();
    Boolean[][][] y_tilde = solution.get_y_tilde();
    for (int i : constants.get_A()) {
      for (int m : constants.get_M_i(i)) {
        for (int e : constants.get_E()) {
          if (e != constants.get_card_A() && x_tilde[i][m][e]) {
            for (int m2 : constants.get_M_i(i)) {
              for (int f : constants.get_E()) {
                if (f > e && m2 != m && y_tilde[i][m2][f]) {
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
