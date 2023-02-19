package org.optsol.project_scheduling.mrcpsp.validation.ooe;

import static org.optsol.project_scheduling.utils.numerics.DoubleCompareWithPrecision.isPositivAndGreaterEpsilon;


import lombok.extern.slf4j.Slf4j;
import org.optsol.project_scheduling.mrcpsp.ooe.constants.IOoeModel_Constants;
import org.optsol.project_scheduling.mrcpsp.ooe.solution.IOoe_Solution;
import org.optsol.project_scheduling.mrcpsp.validation.commons.ValidationHelpers;
import org.optsol.project_scheduling.utils.Precedence;
import org.optsol.project_scheduling.utils.validation.IValidityChecks;

@Slf4j
public class OoeFinishToStartPrecedence implements
    IValidityChecks<IOoeModel_Constants, IOoe_Solution> {

  @Override
  public boolean isValid(IOoeModel_Constants constants, IOoe_Solution solution) {
    Double[] s = solution.get_s();
    Boolean[][][] z = solution.get_z();

    for (Precedence precedence : constants.get_P_bar()) {
      try {
        int m_i = ValidationHelpers.getChosenMode_OOE(precedence.get_i(), z, constants);
        int m_j = ValidationHelpers.getChosenMode_OOE(precedence.get_j(), z, constants);
        int on_off_event_i =
            ValidationHelpers.getFirstOnOffEvent(precedence.get_i(), m_i, z, constants);
        int on_off_event_j =
            ValidationHelpers.getFirstOnOffEvent(precedence.get_j(), m_j, z, constants);

        double diff =
            s[on_off_event_i] + constants.get_p_im(precedence.get_i(), m_i) - s[on_off_event_j];

        if (isPositivAndGreaterEpsilon(diff)) {

          log.error("\nactivity " + precedence.get_i() + " finishes after ("
              + s[precedence.get_i()] + constants.get_p_im(precedence.get_i(), m_i)
              + ")  its successor " + precedence.get_j() + " starts (" + s[precedence.get_j()]
              + ")");

          return false;
        }
      } catch (Exception e) {
        log.error(e.getMessage());
        return false;
      }
    }
    return true;
  }
}
