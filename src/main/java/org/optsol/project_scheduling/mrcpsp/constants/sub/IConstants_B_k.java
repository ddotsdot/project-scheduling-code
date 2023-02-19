package org.optsol.project_scheduling.mrcpsp.constants.sub;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface IConstants_B_k {

  Map<Integer, Integer> get_B_k();

  default int get_B_k(int k) {
    if (get_B_k().get(k) == null) {
      System.out.println("stop");
    }
    return get_B_k().get(k);
  }

  default List<Integer> get_B_k_asList(int k) {
    //B_k assumed integer-valued
    return IntStream.rangeClosed(1, get_B_k(k)).boxed().collect(Collectors.toList());
  }
}
