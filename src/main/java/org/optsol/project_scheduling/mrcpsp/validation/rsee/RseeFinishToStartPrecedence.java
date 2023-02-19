package org.optsol.project_scheduling.mrcpsp.validation.rsee;

import static org.optsol.project_scheduling.utils.numerics.DoubleCompareWithPrecision.isPositivAndGreaterEpsilon;


import lombok.extern.slf4j.Slf4j;
import org.optsol.project_scheduling.mrcpsp.see.constants.ISeeModel_Constants;
import org.optsol.project_scheduling.mrcpsp.see.solution.IRsee_Solution;
import org.optsol.project_scheduling.mrcpsp.validation.commons.ValidationHelpers;
import org.optsol.project_scheduling.utils.Precedence;
import org.optsol.project_scheduling.utils.validation.IValidityChecks;

@Slf4j
public class RseeFinishToStartPrecedence implements
    IValidityChecks<ISeeModel_Constants, IRsee_Solution> {

  @Override
  public boolean isValid(ISeeModel_Constants constants, IRsee_Solution solution) {
    Double[] s = solution.get_s();
    Boolean[][][] x_tilde = solution.get_x_tilde();

    for (Precedence precedence : constants.get_P_bar()) {
      int m_i = ValidationHelpers.getChosenMode_RSEE(precedence.get_i(), x_tilde, constants);
      int m_j = ValidationHelpers.getChosenMode_RSEE(precedence.get_j(), x_tilde, constants);

      int start_event_i =
          ValidationHelpers.getStartEvent_RSEE(precedence.get_i(), m_i, x_tilde, constants);
      int start_event_j =
          ValidationHelpers.getStartEvent_RSEE(precedence.get_j(), m_j, x_tilde, constants);

      double diff =
          s[start_event_i] + constants.get_p_im(precedence.get_i(), m_i) - s[start_event_j];

      if (isPositivAndGreaterEpsilon(diff)) {

        log.error("\nactivity " + precedence.get_i() + " finishes after ("
            + s[precedence.get_i()] + constants.get_p_im(precedence.get_i(), m_i)
            + ")  its successor " + precedence.get_j() + " starts (" + s[precedence.get_j()]
            + ")");

        return false;
      }
    }
    return true;
  }
}
