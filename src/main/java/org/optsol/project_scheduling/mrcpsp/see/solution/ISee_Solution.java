/*
 * @author Fath, Philipp
 * @author Sayah, David
 */

package org.optsol.project_scheduling.mrcpsp.see.solution;

import org.optsol.jdecor.core.ISolution;
import org.optsol.project_scheduling.mrcpsp.see.solution.sub.IVar_r;
import org.optsol.project_scheduling.mrcpsp.see.solution.sub.IVar_s;
import org.optsol.project_scheduling.mrcpsp.see.solution.sub.IVar_x;
import org.optsol.project_scheduling.mrcpsp.see.solution.sub.IVar_y;

public interface ISee_Solution extends ISolution, IVar_s, IVar_x, IVar_y, IVar_r {

}
