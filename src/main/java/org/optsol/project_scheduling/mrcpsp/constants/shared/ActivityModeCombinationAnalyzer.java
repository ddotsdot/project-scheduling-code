package org.optsol.project_scheduling.mrcpsp.constants.shared;

import java.util.HashSet;
import java.util.Set;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_A;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_B_k;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_M_i;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_R;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_b_imk;
import org.optsol.project_scheduling.utils.ImmutableIntQuadruple;

public class ActivityModeCombinationAnalyzer {

  public static <CONST extends IConstants_A & IConstants_M_i & IConstants_R & IConstants_b_imk & IConstants_B_k>
  Set<ImmutableIntQuadruple> findDistinctCombinationsWithBottleneck(CONST constants) {

    Set<ImmutableIntQuadruple> quadruples = new HashSet<>();
    for (int i : constants.get_A()) {
      here:
      for (int j : constants.get_A()) {
        if (i < j) {
          for (int m_i : constants.get_M_i(i)) {
            for (int m_j : constants.get_M_i(j)) {

              if (constants.get_R().stream()
                  .anyMatch(k ->
                      constants.get_b_imk(i, m_i, k) + constants.get_b_imk(j, m_j, k)
                          > constants.get_B_k(k))) {
                quadruples.add(new ImmutableIntQuadruple(i, m_i, j, m_j));
                break here;
              }
            }
          }
        }
      }
    }
    return quadruples;
  }

}
