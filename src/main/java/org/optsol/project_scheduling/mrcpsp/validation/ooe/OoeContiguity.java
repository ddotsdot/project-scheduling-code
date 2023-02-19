package org.optsol.project_scheduling.mrcpsp.validation.ooe;

import lombok.extern.slf4j.Slf4j;
import org.optsol.project_scheduling.mrcpsp.ooe.constants.IOoeModel_Constants;
import org.optsol.project_scheduling.mrcpsp.ooe.solution.IOoe_Solution;
import org.optsol.project_scheduling.utils.validation.IValidityChecks;

@Slf4j
public class OoeContiguity implements IValidityChecks<IOoeModel_Constants, IOoe_Solution> {

  @Override
  public boolean isValid(IOoeModel_Constants constants, IOoe_Solution solution) {
    Boolean[][][] z = solution.get_z();
    for (int i : constants.get_A()) {
      for (int m : constants.get_M_i(i)) {
        for (int e : constants.get_E()) {
          if (e != constants.get_card_A() && z[i][m][e]) {
            for (int f = e + 1; f < constants.get_card_A(); f++) {
              if (!z[i][m][f]) {
                for (int g = f + 1; g < constants.get_card_A(); g++) {
                  if (z[i][m][g]) {
                    log.error("Non-contiguous execution of activity " + i + "/mode " + m + ":"
                        + " active at events " + e + ",...," + (f - 1) + " and " + g);
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