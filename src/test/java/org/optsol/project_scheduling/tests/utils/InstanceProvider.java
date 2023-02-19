package org.optsol.project_scheduling.tests.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.optsol.project_scheduling.mrcpsp.constants.IMrcpsp_Constants;
import org.optsol.project_scheduling.mrcpsp.constants.Mrcpsp_Constants;
import org.optsol.project_scheduling.tests.utils.io.PathsAndFiles;
import org.optsol.project_scheduling.tests.utils.io.instance.MmlibInstanceReader;
import org.optsol.project_scheduling.tests.utils.io.instance.MrcpspInstanceClass;
import org.optsol.project_scheduling.tests.utils.io.instance.PsplibInstanceReader;
import org.optsol.project_scheduling.tests.utils.io.yaml.ReadYamlResourceFileToObject;

@RequiredArgsConstructor
public class InstanceProvider {

  private final String pathToPsplib = "instances/mrcpsp/psplib/";
  private final String pathToMmlib = "instances/mrcpsp/mmlib/";

  @Getter
  private final EnumSet<MrcpspInstanceClass> instanceClasses;

  private static IMrcpsp_Constants getMrcpspConstantsBaseFromMMFile(
      MrcpspInstanceClass instanceClass,
      String instanceFileName,
      String pathToResourceFolder) {
    InputStream in =
        InstanceProvider.class
            .getClassLoader()
            .getResourceAsStream(
                pathToResourceFolder + instanceClass.className + "/" + instanceFileName);

    if (instanceClass.isPspLib()) {
      return
          new PsplibInstanceReader(new BufferedReader(new InputStreamReader(in)))
              .createConstants();
    }
    return
        new MmlibInstanceReader(new BufferedReader(new InputStreamReader(in)))
            .createConstants();
  }

  public List<String> provideInstanceFileList(MrcpspInstanceClass instanceClass) {
    if (instanceClass.isPspLib()) {
      return getFileList(instanceClass, pathToPsplib);
    } else if (instanceClass.isMmLib()) {
      return getFileList(instanceClass, pathToMmlib);
    }
    return Collections.emptyList();
  }

  private List<String> getFileList(
      MrcpspInstanceClass instanceClass,
      String pathToResourceFolder) {
    try {
      return PathsAndFiles
          .getFileListFromResourceDir(pathToResourceFolder + instanceClass.className)
          .stream()
          .filter(s -> !s.contains("opt"))
          .collect(Collectors.toList());
    } catch (IOException e) {
      e.printStackTrace();
      throw new Error("something went wrong when retrieving file list...");
    }
  }

  public IMrcpsp_Constants provideConstants(
      MrcpspInstanceClass instanceClass,
      String instanceFileName) {

    if (instanceClass.isDerived()) {
      return
          getMrcpspConstantsBaseFromYamlFile(instanceClass, instanceFileName);
    } else if (instanceClass.isPspLib()) {
      return
          getMrcpspConstantsBaseFromMMFile(instanceClass, instanceFileName, pathToPsplib);
    }
    return
        getMrcpspConstantsBaseFromMMFile(instanceClass, instanceFileName, pathToMmlib);
  }

  private Mrcpsp_Constants getMrcpspConstantsBaseFromYamlFile(
      MrcpspInstanceClass instanceClass,
      String instanceFileName) {

    String folderName = instanceClass.isPspLib() ? pathToPsplib : pathToMmlib;
    folderName = folderName + instanceClass.className;

    InputStream in =
        getClass()
            .getClassLoader()
            .getResourceAsStream(folderName + "/" + instanceFileName);

    return new ReadYamlResourceFileToObject<Mrcpsp_Constants>()
        .read(Mrcpsp_Constants.class, new BufferedReader(new InputStreamReader(in)));
  }
}
