package org.optsol.project_scheduling.tests.config;

import java.util.EnumSet;
import lombok.extern.slf4j.Slf4j;
import org.optsol.project_scheduling.mrcpsp.MrcpspModelType;

@Slf4j
public class ModelConfig {

  public static EnumSet<MrcpspModelType> getModelTypes() {
    return EnumSet.of(MrcpspModelType.MCTAB_EXT);
//    return EnumSet.allOf(MrcpspModelType.class);
  }
}
