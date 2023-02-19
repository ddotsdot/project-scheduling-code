package org.optsol.project_scheduling.utils.numerics;

public class DoubleCompareWithPrecision {

  private static final double DOUBLE_COMPARE_EPSILON = 0.001;

  public static boolean isPositivAndGreaterEpsilon(double value) {
    return Double.compare(value, 0.) > 0 && Double.compare(value, DOUBLE_COMPARE_EPSILON) > 0;
  }

  public static boolean isGreaterZeroAndGreaterEpsilon(double value) {
    if (Double.compare(value, 0.) == 0) {
      return false;
    }

    if (Double.compare(value, 0.) > 0) {
      return Double.compare(value, DOUBLE_COMPARE_EPSILON) > 0;
    }

    return false;
  }
}
