package org.optsol.project_scheduling.mrcpsp.fct.models;

import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import java.util.ArrayList;
import java.util.List;
import org.optsol.jdecor.core.IConstraintManager;
import org.optsol.jdecor.ortools.SolverEngine;
import org.optsol.project_scheduling.mrcpsp.fct.constants.IFctModel_Constants;
import org.optsol.project_scheduling.mrcpsp.fct.constraints.FCT_RC_EnforceSequentialProcessing;

public class MrcpspModel_Fct_Weak_TW_RC extends MrcpspModel_Fct_Weak_TW {

  public MrcpspModel_Fct_Weak_TW_RC(SolverEngine solverEngine) {
    super(solverEngine);
  }

  @Override
  protected List<IConstraintManager<? super IFctModel_Constants, MPVariable, MPSolver>> generateConstraints() {
    ArrayList<IConstraintManager<? super IFctModel_Constants, MPVariable, MPSolver>>
        constraints = new ArrayList<>(super.generateConstraints());

    constraints.add(new FCT_RC_EnforceSequentialProcessing<>());

    return constraints;
  }
}
