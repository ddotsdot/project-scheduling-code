package org.optsol.project_scheduling.tests.utils.io.instance;

import java.util.Collections;
import org.optsol.project_scheduling.mrcpsp.constants.IMrcpsp_Constants;
import org.optsol.project_scheduling.mrcpsp.constants.Mrcpsp_Constants;

public class RenewableResourceRemover {

  public IMrcpsp_Constants modifyConstants(IMrcpsp_Constants constants) {
    return new Mrcpsp_Constants(
        constants.get_card_A(),
        Collections.emptyMap(),
        constants.get_w_imk(),
        Collections.emptyMap(),
        constants.get_W_k(),
        constants.get_M_i(),
        constants.get_P_bar(),
        constants.get_p_im(),
        0,
        constants.get_card_N()
    );
  }
}
