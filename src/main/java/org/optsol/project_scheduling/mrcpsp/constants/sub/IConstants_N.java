package org.optsol.project_scheduling.mrcpsp.constants.sub;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface IConstants_N {

  //num non-renewable resources
  int get_card_N();

  // set of non-renewable resources
  default List<Integer> get_N() {
    return IntStream.rangeClosed(1, get_card_N()).boxed().collect(Collectors.toList());
  }
}
