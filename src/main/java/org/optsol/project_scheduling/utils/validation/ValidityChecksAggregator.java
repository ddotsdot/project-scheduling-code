package org.optsol.project_scheduling.utils.validation;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class ValidityChecksAggregator<CONST, SOL> implements IValidityChecks<CONST, SOL> {

  private final List<IValidityChecks<CONST, SOL>> checks;

  @Override
  public boolean isValid(CONST constants, SOL solution) {
    boolean valid = true;

    StringBuilder stringBuilder = new StringBuilder().append(System.getProperty("line.separator"));
    for (IValidityChecks<CONST, SOL> check : checks) {
      if (check.isValid(constants, solution)) {
        stringBuilder.append(check.getClass().getSimpleName())
            .append(": valid!")
            .append(System.getProperty("line.separator"));
      } else {
        stringBuilder.append(check.getClass().getSimpleName() + ": INVALID!")
            .append(System.getProperty("line.separator"));
        valid = false;
      }
    }

    if (valid) {
      log.info(stringBuilder.toString());
    } else {
      log.warn(stringBuilder.toString());
    }

    return valid;
  }
}
