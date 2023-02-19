package org.optsol.project_scheduling.mrcpsp.constants.shared;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface IConstants_T extends IConstants_MakespanUpperBound {

  //length of planning horizon (num time periods)
  default int get_card_T() {
    return get_makespan_upperBound();
  }

  // set of time periods
  default List<Integer> get_T() {
    return IntStream.rangeClosed(0, get_card_T()).boxed().collect(Collectors.toList());
  }
}
