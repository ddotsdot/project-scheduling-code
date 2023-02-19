package org.optsol.project_scheduling.mrcpsp.constants.sub;

import java.util.Map;

public interface IConstants_w_imk extends IConstants_V, IConstants_M_i {

  Map<Integer, Map<Integer, Map<Integer, Integer>>> get_w_imk();

  default int get_w_imk(int i, int m, int k) {

    // resource consumption in case of dummy activities
    if (i == get_dummy_activity_project_start() || i == get_A_bar()) {
      return 0;
    }
    return get_w_imk().get(i).get(m).getOrDefault(k, 0);
  }

  default int get_w_max_ik(int i, int k) {
    // max resource consumption in case of dummy activities
    if (i == get_dummy_activity_project_start() || i == get_A_bar()) {
      return 0;
    }
    return get_M_i(i).stream().mapToInt(m -> get_w_imk(i, m, k)).max().getAsInt();
  }

  default boolean isNonrenewableResourceUsedBy(int i, int m, int k) {
    return
        get_w_imk().containsKey(i)
            && get_w_imk().get(i).containsKey(m)
            && get_w_imk().get(i).get(m).containsKey(k);
  }

  default boolean isNonrenewableResourceUsedBy(int i, int k) {
    return
        get_M_i(i).stream().anyMatch(m ->
            get_w_imk().containsKey(i)
                && get_w_imk().get(i).containsKey(m)
                && get_w_imk().get(i).get(m).containsKey(k)
        );
  }
}
