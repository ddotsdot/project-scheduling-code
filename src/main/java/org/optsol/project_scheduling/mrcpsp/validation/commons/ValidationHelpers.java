package org.optsol.project_scheduling.mrcpsp.validation.commons;

import java.util.Objects;
import java.util.Optional;
import org.optsol.project_scheduling.mrcpsp.fct.constants.IFctModel_Constants;
import org.optsol.project_scheduling.mrcpsp.ooe.constants.IOoeModel_Constants;
import org.optsol.project_scheduling.mrcpsp.see.constants.ISeeModel_Constants;
import org.optsol.project_scheduling.utils.numerics.DoubleCompareWithPrecision;

public class ValidationHelpers {

  public static int getFirstOnOffEvent(
      int i,
      int m,
      Boolean[][][] z,
      IOoeModel_Constants constants) {

    Optional<Integer> any = constants.get_E()
        .stream()
        .filter(e -> e != constants.get_card_A())
        .filter(e -> z[i][m][e] != null && z[i][m][e])
        .findFirst();

    if (any.isEmpty()) {
      throw new Error("No OO event found for activity " + i + " and mode" + m);
    }
    return any.get();
  }

  public static int getStartEvent_SEE(
      int i,
      int m,
      Boolean[][][] x,
      ISeeModel_Constants constants) {

    Optional<Integer> any = constants.get_E()
        .stream()
        .filter(e -> x[i][m][e] != null && x[i][m][e])
        .findAny();

    if (any.isEmpty()) {
      throw new Error("No start event found for activity " + i + " and mode" + m);
    }
    return any.get();
  }

  public static int getStartEvent_RSEE(
      int i,
      int m,
      Boolean[][][] x_tilde,
      ISeeModel_Constants constants) {
    return getStartEvent_SEE(i, m, x_tilde, constants);
  }

  public static int getChosenMode_OOE(
      int i,
      Boolean[][][] z,
      IOoeModel_Constants constants) {

    Optional<Integer> any = constants.get_M_i(i)
        .stream()
        .filter(m ->
            constants.get_E()
                .stream()
                .filter(e -> e != constants.get_card_A())
                .map(e -> z[i][m][e])
                .filter(Objects::nonNull)
                .anyMatch(z_ime -> z_ime))
        .findAny();

    if (any.isEmpty()) {
      throw new Error("No mode for activity " + i + " selected");
    }

    return any.get();
  }

  public static int getChosenMode_SEE(
      int i,
      Boolean[][][] x,
      ISeeModel_Constants constants) {

    Optional<Integer> any = constants.get_M_i(i)
        .stream()
        .filter(m ->
            constants.get_E()
                .stream()
                .filter(e -> e != constants.get_card_A())
                .map(e -> x[i][m][e])
                .filter(Objects::nonNull)
                .anyMatch(x_ime -> x_ime))
        .findAny();

    if (any.isEmpty()) {
      throw new Error("No mode for activity " + i + " selected");
    }

    return any.get();
  }

  public static int getChosenMode_RSEE(
      int i,
      Boolean[][][] x_tilde,
      ISeeModel_Constants constants) {
    return getChosenMode_SEE(i, x_tilde, constants);
  }

  public static int getChosenMode_FCT(
      int i,
      Boolean[][] x,
      IFctModel_Constants constants) {

    Optional<Integer> any = constants.get_M_i(i)
        .stream()
        .filter(m -> x[i][m])
        .findAny();

    if (any.isEmpty()) {
      throw new Error("No mode for activity " + i + " selected");
    }

    return any.get();
  }

  public static boolean isActivityProcessingInPeriod(
      int start_i,
      int completion_i,
      int period) {
    return start_i <= period && completion_i > period;
  }

  public static boolean doActivitiesOverlap(
      double start_i,
      double start_j,
      int p_im,
      int p_jm) {

    boolean j_starts_before_i_ends =
        DoubleCompareWithPrecision.isGreaterZeroAndGreaterEpsilon(start_i + p_im - start_j);

    boolean i_starts_before_j_ends =
        DoubleCompareWithPrecision.isGreaterZeroAndGreaterEpsilon(start_j + p_jm - start_i);

    return j_starts_before_i_ends && i_starts_before_j_ends;
  }
}
