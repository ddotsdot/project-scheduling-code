package org.optsol.project_scheduling.solver;

import org.optsol.jdecor.ortools.OrtoolsSolver;
import org.optsol.jdecor.ortools.SolverEngine;
import org.optsol.project_scheduling.model.Model;
import org.optsol.project_scheduling.model.constants.Constants;

public class Solver
    extends OrtoolsSolver<Constants, Solution> {

  public Solver(
      SolverEngine solverEngine) {
    super(
        new Model(solverEngine),
        Solution.class);
  }

  public Solver(
      SolverEngine solverEngine,
      int timelimitSeconds) {
    super(
        timelimitSeconds,
        new Model(solverEngine),
        Solution.class);
  }
}
