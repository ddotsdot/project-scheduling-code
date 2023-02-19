package org.optsol.project_scheduling.mrcpsp.validation.ooe;

import lombok.extern.slf4j.Slf4j;
import org.optsol.project_scheduling.mrcpsp.ooe.constants.IOoeModel_Constants;
import org.optsol.project_scheduling.mrcpsp.ooe.solution.IOoe_Solution;
import org.optsol.project_scheduling.utils.validation.IValidityChecks;

@Slf4j
public class OoeModeConsistency implements IValidityChecks<IOoeModel_Constants, IOoe_Solution> {

  @Override
  public boolean isValid(IOoeModel_Constants constants, IOoe_Solution solution) {
    Boolean[][][] z = solution.get_z();
    for (int i : constants.get_A()) {
      for (int m : constants.get_M_i(i)) {
        for (int e : constants.get_E()) {
          if (e != constants.get_card_A() && z[i][m][e]) {
            for (int m2 : constants.get_M_i(i)) {
              for (int f : constants.get_E()) {
                if (e != constants.get_card_A() && f != constants.get_card_A()) {
                  if (m2 != m && z[i][m2][f]) {
                    // invalid if activity i is processed in mode m at event e and in mode m2 at f
                    log.error(
                        "At least two modes are active for activity " + i
                            + ": m = " + m + " at event " + e
                            + " and m = " + m2 + " at event " + f);
                    return false;
                  }
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
