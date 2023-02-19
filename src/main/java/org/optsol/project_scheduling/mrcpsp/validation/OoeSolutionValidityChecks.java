package org.optsol.project_scheduling.mrcpsp.validation;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.optsol.project_scheduling.mrcpsp.ooe.constants.IOoeModel_Constants;
import org.optsol.project_scheduling.mrcpsp.ooe.solution.IOoe_Solution;
import org.optsol.project_scheduling.mrcpsp.validation.ooe.OoeContiguity;
import org.optsol.project_scheduling.mrcpsp.validation.ooe.OoeFinishToStartPrecedence;
import org.optsol.project_scheduling.mrcpsp.validation.ooe.OoeModeConsistency;
import org.optsol.project_scheduling.mrcpsp.validation.ooe.OoeNonRenewableCapacityUsage;
import org.optsol.project_scheduling.mrcpsp.validation.ooe.OoeRenewableCapacityUsage;
import org.optsol.project_scheduling.utils.validation.ValidityChecksAggregator;

@Slf4j
public class OoeSolutionValidityChecks extends
    ValidityChecksAggregator<IOoeModel_Constants, IOoe_Solution> {

  public OoeSolutionValidityChecks() {
    super(List.of(
        new OoeFinishToStartPrecedence(),
        new OoeContiguity(),
        new OoeModeConsistency(),
        new OoeNonRenewableCapacityUsage(),
        new OoeRenewableCapacityUsage()
    ));
  }
}
