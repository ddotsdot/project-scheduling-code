package org.optsol.project_scheduling.mrcpsp.constants.sub;

import java.util.Map;
import java.util.Set;

public interface IConstants_M_i extends IConstants_A {

  default int get_dummy_mode() {
    return 0;
  }

  Map<Integer, Set<Integer>> get_M_i();

  // set of modes of activity i
  default Set<Integer> get_M_i(int i) {
    if (i == 0 || i == get_card_A() + 1) {
      return Set.of(get_dummy_mode());
    }
    return get_M_i().get(i);
  }

  default int get_card_M_i(int i) {
    return get_M_i(i).size();
  }
}