package org.optsol.project_scheduling.mrcpsp.fct.constants.sub;

import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_B_k;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_b_imk;

public interface IConstants_b_tilde_imk extends IConstants_b_imk, IConstants_B_k {

  default int get_b_tilde_imk(int i, int m, int k) {
    if (get_A().contains(i)) {
      return get_b_imk(i, m, k);
    }

    if ((i == get_dummy_activity_project_start() || i == get_A_bar())
        && get_M_i(i).contains(m)) {

      return get_B_k(k);
    }
    throw new Error("Unexpected activity-mode combination: i=" + i + " | m=" + m);
  }

  default int get_b_tilde_max_ik(int i, int k) {
    return get_M_i(i).stream().mapToInt(m -> get_b_tilde_imk(i, m, k)).max().getAsInt();
  }
}
