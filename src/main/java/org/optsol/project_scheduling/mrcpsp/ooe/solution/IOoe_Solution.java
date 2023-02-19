/*
 * @author Fath, Philipp
 * @author Sayah, David
 */

package org.optsol.project_scheduling.mrcpsp.ooe.solution;

import org.optsol.jdecor.core.ISolution;
import org.optsol.project_scheduling.mrcpsp.ooe.solution.sub.IVar_r;
import org.optsol.project_scheduling.mrcpsp.ooe.solution.sub.IVar_s;
import org.optsol.project_scheduling.mrcpsp.ooe.solution.sub.IVar_z;

public interface IOoe_Solution extends ISolution, IVar_r, IVar_s, IVar_z {

}
