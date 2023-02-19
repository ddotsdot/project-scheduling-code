package org.optsol.project_scheduling.tests.utils.io;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PathsAndFiles {

  public static List<String> getFileListFromResourceDir(String relativePathToDir)
      throws IOException {
    List<String> fileNames = new ArrayList<>();
    final URL url = PathsAndFiles.class.getResource("/" + relativePathToDir);
    if (url != null) {
      try {
        final File apps = new File(url.toURI());
        fileNames = Arrays.stream(apps.list()).collect(Collectors.toList());
      } catch (URISyntaxException ex) {
        // never happens
      }
    }
    return fileNames.stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());
  }
}
