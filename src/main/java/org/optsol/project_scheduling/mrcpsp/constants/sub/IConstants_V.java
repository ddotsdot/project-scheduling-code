package org.optsol.project_scheduling.mrcpsp.constants.sub;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface IConstants_V extends IConstants_A {

  //index of dummy activity representing project start
  default int get_dummy_activity_project_start() {
    return 0;
  }

  //index of dummy activity representing project start
  default int get_A_bar() {
    return get_card_A() + 1;
  }

  // dummy source and sink node
  default List<Integer> get_dummies() {
    return List.of(get_dummy_activity_project_start(), get_A_bar());
  }

  // set of activities (incl. dummy start 0 and terminal activity \bar{A})
  default List<Integer> get_V() {
    return IntStream.rangeClosed(get_dummy_activity_project_start(), get_A_bar()).boxed()
        .collect(Collectors.toList());
  }
}
