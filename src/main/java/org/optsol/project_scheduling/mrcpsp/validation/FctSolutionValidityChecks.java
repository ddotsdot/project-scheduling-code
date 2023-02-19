package org.optsol.project_scheduling.mrcpsp.validation;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.optsol.project_scheduling.mrcpsp.fct.constants.IFctModel_Constants;
import org.optsol.project_scheduling.mrcpsp.fct.solution.IFct_Solution;
import org.optsol.project_scheduling.mrcpsp.validation.fct.FctFinishToStartPrecedence;
import org.optsol.project_scheduling.mrcpsp.validation.fct.FctModeConsistency;
import org.optsol.project_scheduling.mrcpsp.validation.fct.FctNonRenewableCapacityUsage;
import org.optsol.project_scheduling.mrcpsp.validation.fct.FctRenewableCapacityUsage;
import org.optsol.project_scheduling.utils.validation.ValidityChecksAggregator;

@Slf4j
public class FctSolutionValidityChecks extends
    ValidityChecksAggregator<IFctModel_Constants, IFct_Solution> {

  public FctSolutionValidityChecks() {
    super(List.of(
        new FctFinishToStartPrecedence(),
        new FctModeConsistency(),
        new FctNonRenewableCapacityUsage(),
        new FctRenewableCapacityUsage()
    ));
  }
}
