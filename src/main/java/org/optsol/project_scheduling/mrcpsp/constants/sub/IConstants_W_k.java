package org.optsol.project_scheduling.mrcpsp.constants.sub;

import java.util.Map;

public interface IConstants_W_k {

  Map<Integer, Integer> get_W_k();

  default int get_W_k(int k) {
    if (get_W_k().get(k) == null) {
      System.out.println("stop");
    }
    return get_W_k().get(k);
  }
}
