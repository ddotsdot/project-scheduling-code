package org.optsol.project_scheduling.mrcpsp.fct.constraints.ozon;

import java.util.Collection;
import java.util.HashSet;
import org.optsol.jdecor.ortools.AbstractOrtoolsConstraintManager;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_R;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_V;

public abstract class FCT_f0_FlowUpperBoundGroup<CONST extends IConstants_V & IConstants_R>
    extends AbstractOrtoolsConstraintManager<CONST> {

  public FCT_f0_FlowUpperBoundGroup() {
    super("i", "j", "k");
  }

  @Override
  protected Collection<ConstraintKey> createKeys(CONST constants) {
    HashSet<ConstraintKey> indexes = new HashSet<>();
    for (int i : constants.get_V()) {
      for (int j : constants.get_V()) {
        for (int k : constants.get_R()) {
          indexes.add(new ConstraintKey(i, j, k));
        }
      }
    }
    return indexes;
  }
}
