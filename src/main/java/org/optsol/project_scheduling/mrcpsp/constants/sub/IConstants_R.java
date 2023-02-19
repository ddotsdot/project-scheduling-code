package org.optsol.project_scheduling.mrcpsp.constants.sub;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface IConstants_R {

  //num renewable resources
  int get_card_R();

  // set of renewable resources
  default List<Integer> get_R() {
    return IntStream.rangeClosed(1, get_card_R()).boxed().collect(Collectors.toList());
  }
}
