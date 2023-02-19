package org.optsol.project_scheduling.mrcpsp.constants.sub;

import java.util.Map;

public interface IConstants_p_im extends IConstants_M_i, IConstants_V {

  Map<Integer, Map<Integer, Integer>> get_p_im();

  default int get_p_im(int i, int m) {
    if (i == get_dummy_activity_project_start() || i == get_A_bar()) {
      return 0;
    }
    return get_p_im().get(i).get(m);
  }

  default int get_p_i_max(int i) {
    return get_M_i(i).stream().mapToInt(m -> get_p_im(i, m)).max().getAsInt();
  }

  default int get_p_i_min(int i) {
    return get_M_i(i).stream().mapToInt(m -> get_p_im(i, m)).min().getAsInt();
  }
}
