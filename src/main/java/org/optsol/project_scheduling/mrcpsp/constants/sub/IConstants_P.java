package org.optsol.project_scheduling.mrcpsp.constants.sub;

import java.util.Set;
import org.optsol.project_scheduling.utils.Precedence;
import org.optsol.project_scheduling.utils.graph.PrecedenceGraphAugmentor;

public interface IConstants_P extends IConstants_P_bar, IConstants_A, IConstants_V {

  default Set<Precedence> get_P() {
    return
        PrecedenceGraphAugmentor.augmentDummyArcs(
            get_A(),
            get_P_bar(),
            get_dummy_activity_project_start(),
            get_A_bar());
  }
}
