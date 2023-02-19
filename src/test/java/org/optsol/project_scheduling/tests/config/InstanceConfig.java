package org.optsol.project_scheduling.tests.config;

import java.util.EnumSet;
import lombok.extern.slf4j.Slf4j;
import org.optsol.project_scheduling.tests.utils.io.instance.MrcpspInstanceClass;

@Slf4j
public class InstanceConfig {

  public static EnumSet<MrcpspInstanceClass> getInstanceClasses() {
    return EnumSet.of(MrcpspInstanceClass.PSPLIB_MRCPSP_C15);
//    return EnumSet.allOf(MrcpspInstanceClass.class);
  }
}
