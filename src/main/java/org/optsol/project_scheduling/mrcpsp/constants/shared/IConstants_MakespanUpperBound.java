package org.optsol.project_scheduling.mrcpsp.constants.shared;

import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_A;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_M_i;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_p_im;

public interface IConstants_MakespanUpperBound extends
    IConstants_p_im,
    IConstants_A,
    IConstants_M_i {

  // upper bound on overall project duration
  default int get_makespan_upperBound() {
    return get_A().stream().mapToInt(this::getMaxDuration).sum();
  }

  private int getMaxDuration(Integer i) {
    return get_M_i(i).stream().mapToInt(m -> get_p_im(i, m)).max().getAsInt();
  }
}
