package org.optsol.project_scheduling.mrcpsp.fct.constants.sub;

import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_B_k;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_V;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_b_imk;

public interface IConstants_Wbeta_ijk extends IConstants_V, IConstants_B_k, IConstants_b_imk {

  default double get_Wbeta_ijk(int i, int j, int k) {
    if (get_A().contains(i) && get_A().contains(j)) {
      return Math.max(get_b_max_ik(i, k), get_b_max_ik(j, k));
    }

    if (get_A().contains(i) && j == get_A_bar()) {
      return Math.max(get_b_max_ik(i, k), get_B_k(k));
    }

    if (i == get_dummy_activity_project_start() && get_A().contains(j)) {
      return Math.max(get_b_max_ik(j, k), get_B_k(k));
    }

    return get_B_k(k);
  }
}
