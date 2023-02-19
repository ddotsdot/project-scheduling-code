package org.optsol.project_scheduling.mrcpsp.constants.shared.events;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_A;

public interface IConstants_E extends IConstants_A {

  //num start-end events
  default int get_card_E() {
    return get_card_A() + 1;
  }

  // set of start-end events
  default List<Integer> get_E() {
    return IntStream.rangeClosed(0, get_card_A()).boxed().collect(Collectors.toList());
  }
}
