/*
 * @author Fath, Philipp
 * @author Sayah, David
 */

package org.optsol.project_scheduling.tests.utils.io.yaml;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class ReadYamlResourceFileToObject<T> {

  public T read(Class<T> clazz, String resourceFilePathAndName) {
    try {
      return
          new ObjectMapper(new YAMLFactory()).readValue(
              new FileReader(
                  getClass().getClassLoader().getResource(resourceFilePathAndName).getFile()),
              clazz);
    } catch (IOException e) {
      throw new Error(e.getMessage());
    }
  }

  public T read(Class<T> clazz, Reader reader) {
    try {
      return new ObjectMapper(new YAMLFactory()).readValue(reader, clazz);
    } catch (IOException e) {
      throw new Error(e.getMessage());
    }
  }
}
