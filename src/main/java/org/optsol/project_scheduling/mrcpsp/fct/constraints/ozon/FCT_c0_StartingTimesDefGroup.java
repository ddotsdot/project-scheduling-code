package org.optsol.project_scheduling.mrcpsp.fct.constraints.ozon;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.optsol.jdecor.ortools.AbstractOrtoolsConstraintManager;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_V;

public abstract class FCT_c0_StartingTimesDefGroup<CONST extends IConstants_V>
    extends AbstractOrtoolsConstraintManager<CONST> {

  public FCT_c0_StartingTimesDefGroup() {
    super("i", "j");
  }

  @Override
  protected Collection<ConstraintKey> createKeys(CONST constants) {
    Set<ConstraintKey> indexes = new HashSet<>();
    for (int i : constants.get_V()) {
      for (int j : constants.get_V()) {
        indexes.add(new ConstraintKey(i, j));
      }
    }
    return indexes;
  }
}
