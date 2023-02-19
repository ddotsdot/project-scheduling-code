package org.optsol.project_scheduling.mrcpsp.constants.sub;

import java.util.Map;

public interface IConstants_b_imk extends IConstants_V, IConstants_M_i {

  Map<Integer, Map<Integer, Map<Integer, Integer>>> get_b_imk();

  default int get_b_imk(int i, int m, int k) {
    // resource consumption in case of dummy activities
    if (i == get_dummy_activity_project_start() || i == get_A_bar()) {
      return 0;
    }
    return get_b_imk().get(i).get(m).getOrDefault(k, 0);
  }

  default int get_b_max_ik(int i, int k) {
    // max resource consumption in case of dummy activities
    if (i == get_dummy_activity_project_start() || i == get_A_bar()) {
      return 0;
    }
    return get_M_i(i).stream().mapToInt(m -> get_b_imk(i, m, k)).max().getAsInt();
  }

  default int get_b_min_ik(int i, int k) {
    // min resource consumption in case of dummy activities
    if (i == get_dummy_activity_project_start() || i == get_A_bar()) {
      return 0;
    }
    return get_M_i(i).stream().mapToInt(m -> get_b_imk(i, m, k)).min().getAsInt();
  }

  default boolean isRenewableResourceUsedBy(int i, int m, int k) {
    return
        get_b_imk().containsKey(i)
            && get_b_imk().get(i).containsKey(m)
            && get_b_imk().get(i).get(m).containsKey(k);
  }
}
