package org.optsol.project_scheduling.mrcpsp.constants;

import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_A;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_B_k;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_E_i;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_L_i;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_M_i;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_N;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_P;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_R;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_W_k;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_b_imk;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_p_im;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_w_imk;

public interface IMrcpsp_Constants extends
    IConstants_A,
    IConstants_b_imk,
    IConstants_w_imk,
    IConstants_B_k,
    IConstants_W_k,
    IConstants_E_i,
    IConstants_L_i,
    IConstants_M_i,
    IConstants_P,
    IConstants_p_im,
    IConstants_R,
    IConstants_N {

}
