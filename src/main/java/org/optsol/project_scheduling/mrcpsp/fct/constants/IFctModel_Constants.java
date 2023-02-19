package org.optsol.project_scheduling.mrcpsp.fct.constants;

import org.optsol.project_scheduling.mrcpsp.constants.IMrcpsp_Constants;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_H_G;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_V;
import org.optsol.project_scheduling.mrcpsp.fct.constants.sub.IConstants_W;
import org.optsol.project_scheduling.mrcpsp.fct.constants.sub.IConstants_Wbeta_ijk;
import org.optsol.project_scheduling.mrcpsp.fct.constants.sub.IConstants_Wf_ijk;
import org.optsol.project_scheduling.mrcpsp.fct.constants.sub.IConstants_Ws_ij;
import org.optsol.project_scheduling.mrcpsp.fct.constants.sub.IConstants_b_tilde_imk;

public interface IFctModel_Constants extends
                                     IMrcpsp_Constants,
    IConstants_H_G,
    IConstants_V,
    IConstants_b_tilde_imk,
    IConstants_W,
    IConstants_Ws_ij,
    IConstants_Wf_ijk,
    IConstants_Wbeta_ijk {

}
