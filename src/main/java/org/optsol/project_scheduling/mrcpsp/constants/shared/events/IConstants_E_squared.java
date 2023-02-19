package org.optsol.project_scheduling.mrcpsp.constants.shared.events;

import java.util.ArrayList;
import java.util.List;
import org.optsol.project_scheduling.utils.ImmutableIntPair;

public interface IConstants_E_squared extends IConstants_E {

  default List<ImmutableIntPair> get_E_squared() {
    List<ImmutableIntPair> pairs = new ArrayList<>();
    get_E().forEach(e ->
        get_E()
            .stream()
            .filter(f -> e < f)
            .map(f -> new ImmutableIntPair(e, f))
            .forEach(pairs::add));
    return pairs;
  }
}
