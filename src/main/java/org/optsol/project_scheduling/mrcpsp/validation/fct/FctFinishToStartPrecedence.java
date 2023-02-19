package org.optsol.project_scheduling.mrcpsp.validation.fct;

import static org.optsol.project_scheduling.utils.numerics.DoubleCompareWithPrecision.isPositivAndGreaterEpsilon;


import lombok.extern.slf4j.Slf4j;
import org.optsol.project_scheduling.mrcpsp.fct.constants.IFctModel_Constants;
import org.optsol.project_scheduling.mrcpsp.fct.solution.IFct_Solution;
import org.optsol.project_scheduling.mrcpsp.validation.commons.ValidationHelpers;
import org.optsol.project_scheduling.utils.Precedence;
import org.optsol.project_scheduling.utils.validation.IValidityChecks;

@Slf4j
public class FctFinishToStartPrecedence
    implements IValidityChecks<IFctModel_Constants, IFct_Solution> {

  @Override
  public boolean isValid(IFctModel_Constants constants, IFct_Solution solution) {
    Double[] s = solution.get_s();
    Boolean[][] x = solution.get_x();

    for (Precedence precedence : constants.get_P_bar()) {

      int m_i = ValidationHelpers.getChosenMode_FCT(precedence.get_i(), x, constants);
      double diff =
          s[precedence.get_i()] + constants.get_p_im(precedence.get_i(), m_i)
              - s[precedence.get_j()];

      if (isPositivAndGreaterEpsilon(diff)) {
        return false;
      }
    }
    return true;
  }
}
