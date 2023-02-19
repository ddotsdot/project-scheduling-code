package org.optsol.project_scheduling.tests.utils.io.instance;

import lombok.Getter;

public enum MrcpspInstanceClass {
  PSPLIB_MRCPSP_C15("c15"),
  PSPLIB_MRCPSP_J20("j20"),
  MMLIB_MRCPSP_J50("J50"),

  MRCPSP_C15_D("c15_d"),
  MRCPSP_J20_D("j20_d"),
  MRCPSP_J50_D("J50_d"),

  PSPLIB_MRCPSP_M2("m2"),
  PSPLIB_MRCPSP_M4("m4"),
  PSPLIB_MRCPSP_M5("m5");

  public final String className;

  @Getter
  private boolean isPspLib;
  @Getter
  private boolean isMmLib;
  @Getter
  private boolean isDerived;

  static {
    PSPLIB_MRCPSP_C15.isPspLib = true;
    PSPLIB_MRCPSP_J20.isPspLib = true;
    PSPLIB_MRCPSP_M2.isPspLib = true;
    PSPLIB_MRCPSP_M4.isPspLib = true;
    PSPLIB_MRCPSP_M5.isPspLib = true;
    //
    MRCPSP_C15_D.isPspLib = true;
    MRCPSP_C15_D.isDerived = true;
    MRCPSP_J20_D.isPspLib = true;
    MRCPSP_J20_D.isDerived = true;
    MRCPSP_J50_D.isMmLib = true;
    MRCPSP_J50_D.isDerived = true;
    //
    MMLIB_MRCPSP_J50.isMmLib = true;
  }


  MrcpspInstanceClass(String className) {
    this.className = className;
  }
}
