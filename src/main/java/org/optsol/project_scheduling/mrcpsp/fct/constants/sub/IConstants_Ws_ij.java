package org.optsol.project_scheduling.mrcpsp.fct.constants.sub;

import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_E_i;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_L_i;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_p_im;

public interface IConstants_Ws_ij
    extends IConstants_E_i, IConstants_L_i, IConstants_p_im {

  default double get_Ws_ij(int i, int j) {
    return get_L_i(i) - get_E_i(j) + get_p_i_max(i);
  }
}
