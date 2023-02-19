package org.optsol.project_scheduling.mrcpsp.fct.constraints.strong;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import org.optsol.jdecor.ortools.AbstractOrtoolsConstraintManager;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_R;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_V;

public abstract class FCT_f0_FlowUpperBoundGroup_Strong<CONST extends IConstants_V & IConstants_R>
    extends AbstractOrtoolsConstraintManager<CONST> {

  public FCT_f0_FlowUpperBoundGroup_Strong() {
    super("i", "j", "k");
  }

  @Override
  protected Collection<ConstraintKey> createKeys(CONST constants) {
    HashSet<ConstraintKey> indexes = new HashSet<>();
    List<Integer> A_0 =
        constants.get_V().stream()
            .filter(i -> i < constants.get_A_bar())
            .collect(Collectors.toList());

    List<Integer> A_A_bar =
        constants.get_V().stream().filter(j -> j > 0).collect(Collectors.toList());

    for (int i : A_0) {
      for (int j : A_A_bar) {
        if (i != j) {
          for (int k : constants.get_R()) {
            indexes.add(new ConstraintKey(i, j, k));
          }
        }
      }
    }
    return indexes;
  }
}
