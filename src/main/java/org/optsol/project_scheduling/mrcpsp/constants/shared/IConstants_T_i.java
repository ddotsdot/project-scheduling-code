package org.optsol.project_scheduling.mrcpsp.constants.shared;

import java.util.List;
import java.util.stream.Collectors;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_E_i;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_L_i;

public interface IConstants_T_i extends
        IConstants_T,
    IConstants_E_i,
    IConstants_L_i {

  //feasible starting times of activity i
  default List<Integer> get_T_i(int i) {
    return
        get_T().stream()
            .filter(t -> t >= get_E_i(i))
            .filter(t -> t <= get_L_i(i))
            .collect(Collectors.toList());
  }
}
