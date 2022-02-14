package org.optsol.project_scheduling.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.optsol.jdecor.core.SolutionState;
import org.optsol.jdecor.ortools.OrtoolsModel;
import org.optsol.jdecor.ortools.SolverEngine;
import org.optsol.project_scheduling.model.Model;
import org.optsol.project_scheduling.model.constants.Constants;
import org.optsol.project_scheduling.solver.Solution;
import org.optsol.project_scheduling.solver.Solver;
import org.optsol.project_scheduling.utils.Utils;

@Slf4j
public class SolverTests {

  @Test
  public void testBuildAndExportTemplateModelToLpFile() {
    Constants constants = Utils.generateConstants();

    try {
      OrtoolsModel<Constants> model =
          new Model(SolverEngine.SCIP).buildModel(constants);

      log.info(model.getSolver().exportModelAsLpFormat());

    } catch (Exception ex) {
      fail(ex);
    }
  }

  @Test
  public void testSolveTemplateModelWithCBC() {
    Constants constants = Utils.generateConstants();

    Solution solution = null;
    try {
      solution =
          new Solver(SolverEngine.CBC)
              .generateSolution(constants);
    } catch (Exception ex) {
      fail(ex);
    }

    assertEquals(SolutionState.OPTIMAL, solution.getSolutionState());

    Utils.printSolution(solution, constants);
  }

  @Test
  public void testSolveTepmlateModelWithSCIP() {
    Constants constants = Utils.generateConstants();

    Solution solution = null;
    try {
      solution =
          new Solver(SolverEngine.SCIP)
              .generateSolution(constants);
    } catch (Exception ex) {
      fail(ex);
    }

    assertEquals(SolutionState.OPTIMAL, solution.getSolutionState());

    Utils.printSolution(solution, constants);
  }
}
