package org.optsol.project_scheduling.tests;

import static org.junit.jupiter.api.Assertions.fail;


import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.EnumSet;
import java.util.Locale;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.optsol.jdecor.core.ISolution;
import org.optsol.jdecor.core.SolutionState;
import org.optsol.jdecor.ortools.SolverEngine;
import org.optsol.project_scheduling.mrcpsp.MrcpspModelType;
import org.optsol.project_scheduling.mrcpsp.OrtoolsSolverFactory;
import org.optsol.project_scheduling.mrcpsp.constants.IMrcpsp_Constants;
import org.optsol.project_scheduling.mrcpsp.validation.ValidityChecksResultFactory;
import org.optsol.project_scheduling.tests.config.InstanceConfig;
import org.optsol.project_scheduling.tests.config.ModelConfig;
import org.optsol.project_scheduling.tests.utils.InstanceProvider;
import org.optsol.project_scheduling.tests.utils.io.instance.MrcpspInstanceClass;
import org.optsol.project_scheduling.tests.utils.io.instance.RenewableResourceRemover;

@Slf4j
public class ComputationalExperiments {

  private static final InstanceProvider INSTANCE_PROVIDER =
      new InstanceProvider(
          InstanceConfig.getInstanceClasses()
      );

  private static final EnumSet<MrcpspModelType> MODEL_TYPES = ModelConfig.getModelTypes();

  private static final int TIME_LIMIT_SECONDS = 300;

  private static void trySolveModels(
      StringBuilder sb,
      MrcpspInstanceClass instanceClass,
      String instanceFileName) throws Exception {


    IMrcpsp_Constants constantsBase =
        INSTANCE_PROVIDER.provideConstants(instanceClass, instanceFileName);

    String instanceClassName = instanceClass.className;

    if (EnumSet.of(
        MrcpspInstanceClass.PSPLIB_MRCPSP_M2,
        MrcpspInstanceClass.PSPLIB_MRCPSP_M4,
        MrcpspInstanceClass.PSPLIB_MRCPSP_M5).contains(instanceClass)) {
      constantsBase = new RenewableResourceRemover().modifyConstants(constantsBase);
      instanceClassName = instanceClassName + "N";
    }

    String instanceName = instanceFileName.substring(0, instanceFileName.indexOf("."));

    for (MrcpspModelType modelType : MODEL_TYPES) {

      log.info("START: instance " + instanceName + " model " + modelType);

      ISolution solution =
          OrtoolsSolverFactory.generateMrcpspSolution(
              modelType,
              constantsBase,
              SolverEngine.SCIP,
              TIME_LIMIT_SECONDS);

      Integer solutionTime = (int) solution.getSolutionTime().toSeconds();
      String solutionState = String.valueOf(solution.getSolutionState());
      Double bestBound = solution.getBestObjectiveBound();

      Integer makespan = null;
      if (SolutionState.OPTIMAL.equals(solution.getSolutionState())
          || SolutionState.FEASIBLE.equals(solution.getSolutionState())) {

        makespan = (int) Math.round(solution.getObjectiveValue());

        log.info(
            "-----> makespan: " + makespan
                + " | best bound: " + bestBound
                + " | time (s.): " + solutionTime
                + " | status: " + solutionState);

        log.info("Starting validity checks...");
        Assertions.assertTrue(ValidityChecksResultFactory.getValidityChecksResult(
            modelType,
            constantsBase,
            solution));
      }

      NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.GERMANY);
      numberFormat.setMaximumFractionDigits(2);
      sb
          .append(instanceClassName).append(";")
          .append(instanceName).append(";")
          .append(modelType).append(";")
          .append(makespan == null ? "-" : makespan).append(";")
          .append(bestBound == null ? "-" : numberFormat.format(bestBound)).append(";")
          .append(numberFormat.format(solutionTime)).append(";")
          .append(solutionState)
          .append("\n");

      log.info("END: instance " + instanceName + " model " + modelType);
    }
  }

  @Test
  public void doExperiments() throws FileNotFoundException {

    StringBuilder sb =
        new StringBuilder()
            .append("class").append(";")
            .append("instance").append(";")
            .append("modelType").append(";")
            .append("makespan").append(";")
            .append("bestBound").append(";")
            .append("solutionTime").append(";")
            .append("solutionState")
            .append("\n");

    for (MrcpspInstanceClass instanceClass : INSTANCE_PROVIDER.getInstanceClasses()) {
      log.info("Instance classes: " + INSTANCE_PROVIDER.getInstanceClasses());

      for (String instanceFileName : INSTANCE_PROVIDER.provideInstanceFileList(instanceClass)) {
        log.info("START: class " + instanceClass.className);
        try {
          trySolveModels(sb, instanceClass, instanceFileName);
        } catch (Exception e) {
          fail();
        }
      }
    }

    PrintWriter out = new PrintWriter("results.csv");
    out.println(sb);
    out.close();
  }
}