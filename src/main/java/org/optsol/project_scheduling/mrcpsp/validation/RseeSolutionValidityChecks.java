package org.optsol.project_scheduling.mrcpsp.validation;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.optsol.project_scheduling.mrcpsp.see.constants.ISeeModel_Constants;
import org.optsol.project_scheduling.mrcpsp.see.solution.IRsee_Solution;
import org.optsol.project_scheduling.mrcpsp.validation.rsee.RseeFinishToStartPrecedence;
import org.optsol.project_scheduling.mrcpsp.validation.rsee.RseeModeConsistency;
import org.optsol.project_scheduling.mrcpsp.validation.rsee.RseeNonRenewableCapacityUsage;
import org.optsol.project_scheduling.mrcpsp.validation.rsee.RseeRenewableCapacityUsage;
import org.optsol.project_scheduling.utils.validation.ValidityChecksAggregator;

@Slf4j
public class RseeSolutionValidityChecks extends
    ValidityChecksAggregator<ISeeModel_Constants, IRsee_Solution> {

  public RseeSolutionValidityChecks() {
    super(List.of(
        new RseeFinishToStartPrecedence(),
        new RseeModeConsistency(),
        new RseeNonRenewableCapacityUsage(),
        new RseeRenewableCapacityUsage()
    ));
  }
}
