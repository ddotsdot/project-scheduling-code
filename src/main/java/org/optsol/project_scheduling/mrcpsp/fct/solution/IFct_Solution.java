/*
 * @author Fath, Philipp
 * @author Sayah, David
 */

package org.optsol.project_scheduling.mrcpsp.fct.solution;

import org.optsol.jdecor.core.ISolution;
import org.optsol.project_scheduling.mrcpsp.fct.solution.sub.IVar_alpha;
import org.optsol.project_scheduling.mrcpsp.fct.solution.sub.IVar_beta;
import org.optsol.project_scheduling.mrcpsp.fct.solution.sub.IVar_delta;
import org.optsol.project_scheduling.mrcpsp.fct.solution.sub.IVar_f;
import org.optsol.project_scheduling.mrcpsp.fct.solution.sub.IVar_gamma;
import org.optsol.project_scheduling.mrcpsp.fct.solution.sub.IVar_s;
import org.optsol.project_scheduling.mrcpsp.fct.solution.sub.IVar_x;
import org.optsol.project_scheduling.mrcpsp.fct.solution.sub.IVar_y;

public interface IFct_Solution extends ISolution,
    IVar_s,
    IVar_x,
    IVar_y,
    IVar_f,
    IVar_alpha,
    IVar_beta,
    IVar_gamma,
    IVar_delta {

}
