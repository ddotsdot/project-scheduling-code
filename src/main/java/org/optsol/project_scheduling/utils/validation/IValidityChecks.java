package org.optsol.project_scheduling.utils.validation;

public interface IValidityChecks<CONST, SOLN> {

  boolean isValid(CONST constants, SOLN solution);
}
