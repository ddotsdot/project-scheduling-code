package org.optsol.project_scheduling.mrcpsp.validation.fct;

import java.util.HashSet;
import java.util.Set;
import org.optsol.project_scheduling.mrcpsp.fct.constants.IFctModel_Constants;
import org.optsol.project_scheduling.mrcpsp.fct.solution.IFct_Solution;
import org.optsol.project_scheduling.mrcpsp.validation.commons.ValidationHelpers;
import org.optsol.project_scheduling.utils.validation.IValidityChecks;

public class FctRenewableCapacityUsage implements
    IValidityChecks<IFctModel_Constants, IFct_Solution> {

  private static int getTotalResourceUsage(
      int k,
      Set<Integer> overlappingItems,
      Boolean[][] x,
      IFctModel_Constants constants) {
    return
        overlappingItems
            .stream()
            .mapToInt(i ->
                constants.get_b_imk(i, ValidationHelpers.getChosenMode_FCT(i, x, constants), k))
            .sum();
  }

  @Override
  public boolean isValid(IFctModel_Constants constants, IFct_Solution solution) {
    Boolean[][] x = solution.get_x();
    Double[] s = solution.get_s();
    for (int k : constants.get_R()) {
      int maxResourceUsagePerPeriod = 0;
      for (int t = 0; t <= constants.get_makespan_upperBound(); t++) {
        Set<Integer> overlappingItems = new HashSet<>();

        for (int i : constants.get_A()) {

          int s_i = (int) Math.round(s[i]);
          int m_i = ValidationHelpers.getChosenMode_FCT(i, x, constants);
          int p_im = constants.get_p_im(i, m_i);

          if (constants.isRenewableResourceUsedBy(i, m_i, k)
              && ValidationHelpers.isActivityProcessingInPeriod(s_i, s_i + p_im, t)) {

            for (int j = i + 1; j <= constants.get_card_A(); j++) {

              int s_j = (int) Math.round(s[j]);
              int m_j = ValidationHelpers.getChosenMode_FCT(j, x, constants);
              int p_jm = constants.get_p_im(j, m_j);

              if (ValidationHelpers.isActivityProcessingInPeriod(s_j, s_j + p_jm, t)) {

                if (constants.isRenewableResourceUsedBy(j, m_j, k)
                    && ValidationHelpers.doActivitiesOverlap(s_i, s_j, p_im, p_jm)) {

                  overlappingItems.add(i);
                  overlappingItems.add(j);
                }
              }
            }
          }
        }

        int resourceUsage = getTotalResourceUsage(k, overlappingItems, x, constants);
        if (resourceUsage > maxResourceUsagePerPeriod) {
          maxResourceUsagePerPeriod = resourceUsage;
        }
      }

      if (maxResourceUsagePerPeriod > constants.get_B_k(k)) {
        return false;
      }
    }
    return true;
  }
}