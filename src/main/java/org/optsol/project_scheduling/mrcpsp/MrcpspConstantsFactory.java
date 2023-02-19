package org.optsol.project_scheduling.mrcpsp;

import org.optsol.project_scheduling.mrcpsp.constants.IMrcpsp_Constants;
import org.optsol.project_scheduling.mrcpsp.ctab.constants.CtabModel_Constants;
import org.optsol.project_scheduling.mrcpsp.ctab.constants.ICtabModel_Constants;
import org.optsol.project_scheduling.mrcpsp.fct.constants.FctModel_Constants;
import org.optsol.project_scheduling.mrcpsp.fct.constants.IFctModel_Constants;
import org.optsol.project_scheduling.mrcpsp.ooe.constants.IOoeModel_Constants;
import org.optsol.project_scheduling.mrcpsp.ooe.constants.OoeModel_Constants;
import org.optsol.project_scheduling.mrcpsp.see.constants.ISeeModel_Constants;
import org.optsol.project_scheduling.mrcpsp.see.constants.SeeModel_Constants;

public class MrcpspConstantsFactory {

  public static IOoeModel_Constants createOoeConstants(IMrcpsp_Constants constantsBase) {
    return
        OoeModel_Constants
            .builder()
            ._card_A(constantsBase.get_card_A())
            ._card_R(constantsBase.get_card_R())
            ._card_N(constantsBase.get_card_N())
            ._b_imk(constantsBase.get_b_imk())
            ._w_imk(constantsBase.get_w_imk())
            ._p_im(constantsBase.get_p_im())
            ._B_k(constantsBase.get_B_k())
            ._W_k(constantsBase.get_W_k())
            ._M_i(constantsBase.get_M_i())
            ._P_bar(constantsBase.get_P_bar())
            .build();
  }

  public static ISeeModel_Constants createSeeConstants(IMrcpsp_Constants constantsBase) {
    return
        SeeModel_Constants
            .builder()
            ._card_A(constantsBase.get_card_A())
            ._card_R(constantsBase.get_card_R())
            ._card_N(constantsBase.get_card_N())
            ._b_imk(constantsBase.get_b_imk())
            ._w_imk(constantsBase.get_w_imk())
            ._p_im(constantsBase.get_p_im())
            ._B_k(constantsBase.get_B_k())
            ._W_k(constantsBase.get_W_k())
            ._M_i(constantsBase.get_M_i())
            ._P_bar(constantsBase.get_P_bar())
            .build();
  }

  public static IFctModel_Constants createFctConstants(IMrcpsp_Constants constantsBase) {
    return
        FctModel_Constants
            .builder()
            ._card_A(constantsBase.get_card_A())
            ._card_R(constantsBase.get_card_R())
            ._card_N(constantsBase.get_card_N())
            ._b_imk(constantsBase.get_b_imk())
            ._w_imk(constantsBase.get_w_imk())
            ._p_im(constantsBase.get_p_im())
            ._B_k(constantsBase.get_B_k())
            ._W_k(constantsBase.get_W_k())
            ._M_i(constantsBase.get_M_i())
            ._P_bar(constantsBase.get_P_bar())
            .build();
  }

  public static ICtabModel_Constants createCtabConstants(IMrcpsp_Constants constantsBase) {
    return
        CtabModel_Constants
            .builder()
            ._card_A(constantsBase.get_card_A())
            ._card_R(constantsBase.get_card_R())
            ._card_N(constantsBase.get_card_N())
            ._b_imk(constantsBase.get_b_imk())
            ._w_imk(constantsBase.get_w_imk())
            ._p_im(constantsBase.get_p_im())
            ._B_k(constantsBase.get_B_k())
            ._W_k(constantsBase.get_W_k())
            ._M_i(constantsBase.get_M_i())
            ._P_bar(constantsBase.get_P_bar())
            .build();
  }
}
