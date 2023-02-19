package org.optsol.project_scheduling.mrcpsp.fct.constants.sub;

import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_V;

public interface IConstants_Wf_ijk
    extends IConstants_V, IConstants_b_tilde_imk /*IConstants_B_k, IConstants_b_imk*/ {

  default double get_Wf_ijk(int i, int j, int k) {
    return Math.min(get_b_tilde_max_ik(i, k), get_b_tilde_max_ik(j, k));
  }
//  default double get_Wf_ijk(int i, int j, int k) {
//    if (get_A().contains(i) && get_A().contains(j)) {
//      return Math.min(get_b_max_ik(i, k), get_b_max_ik(j, k));
//    }
//
//    if (get_A().contains(i) && j == get_A_bar()) {
//      return Math.min(get_b_max_ik(i, k), get_B_k(k));
//    }
//
//    if (i == get_dummy_activity_project_start() && get_A().contains(j)) {
//      return Math.min(get_b_max_ik(j, k), get_B_k(k));
//    }
//
//    return get_B_k(k);
//  }
}
