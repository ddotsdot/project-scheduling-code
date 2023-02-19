package org.optsol.project_scheduling.mrcpsp.validation;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.optsol.project_scheduling.mrcpsp.see.constants.ISeeModel_Constants;
import org.optsol.project_scheduling.mrcpsp.see.solution.ISee_Solution;
import org.optsol.project_scheduling.mrcpsp.validation.see.SeeFinishToStartPrecedence;
import org.optsol.project_scheduling.mrcpsp.validation.see.SeeModeConsistency;
import org.optsol.project_scheduling.mrcpsp.validation.see.SeeNonRenewableCapacityUsage;
import org.optsol.project_scheduling.mrcpsp.validation.see.SeeRenewableCapacityUsage;
import org.optsol.project_scheduling.utils.validation.ValidityChecksAggregator;

@Slf4j
public class SeeSolutionValidityChecks extends
    ValidityChecksAggregator<ISeeModel_Constants, ISee_Solution> {

  public SeeSolutionValidityChecks() {
    super(List.of(
        new SeeFinishToStartPrecedence(),
        new SeeModeConsistency(),
        new SeeNonRenewableCapacityUsage(),
        new SeeRenewableCapacityUsage()
    ));
  }
}
