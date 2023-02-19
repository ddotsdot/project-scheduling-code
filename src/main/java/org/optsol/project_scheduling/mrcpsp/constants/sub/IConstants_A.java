package org.optsol.project_scheduling.mrcpsp.constants.sub;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface IConstants_A {

  //num non-dummy activities
  int get_card_A();

  // set of non-dummy activies
  default List<Integer> get_A() {
    return IntStream.rangeClosed(1, get_card_A()).boxed().collect(Collectors.toList());
  }
}
