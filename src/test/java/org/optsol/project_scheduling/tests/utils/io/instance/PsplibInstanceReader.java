package org.optsol.project_scheduling.tests.utils.io.instance;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.Getter;
import org.optsol.project_scheduling.mrcpsp.constants.IMrcpsp_Constants;
import org.optsol.project_scheduling.mrcpsp.constants.Mrcpsp_Constants;
import org.optsol.project_scheduling.utils.Precedence;

public class PsplibInstanceReader {

  // Key strings in instance files
  private final static String PRECEDENCE_RELATIONS_KEY = "PRECEDENCERELATIONS";
  private final static String REQUESTS_AND_DURATIONS_KEY = "REQUESTS/DURATIONS";
  private final static String RESOURCE_AVAILABILITIES_KEY = "RESOURCEAVAILABILITIES";
  private final static String NUMBER_OF_JOBS_KEY = "jobs(incl.supersource/sink)";
  private final static String TIME_HORIZON_KEY = "horizon";
  private final static String PROJECT_INFORMATION_KEY = "PROJECTINFORMATION";
  private final static String NUMBER_OF_RENEWABLE_RESOURCES_KEY = "-renewable";
  private final static String NUMBER_OF_NONRENEWABLE_RESOURCES_KEY = "-nonrenewable";

  // Auxiliary maps
  public final List<Precedence> PRECEDENCE_RELATION = new ArrayList<>();
  public final Map<String, Integer> JOBNRSTR_TO_NUMBER_OF_MODES_MAP = new HashMap<>();
  public final Map<String, Integer> MODEKEY_TO_DURATION_MAP = new HashMap<>();
  public final Map<String, Map<String, Integer>> MODEKEY_TO_RENEWABLE_RESOURCE_CONSUMPTION =
      new HashMap<>();
  public final Map<String, Map<String, Integer>> MODEKEY_TO_NONRENEWABLE_RESOURCE_CONSUMPTION =
      new HashMap<>();
  public final Map<String, Integer> RENEWABLE_RESOURCE_CAPACITY = new HashMap<>();
  public final Map<String, Integer> NONRENEWABLE_RESOURCE_CAPACITY = new HashMap<>();
  @Getter
  private final int numberOfModes;
  // Auxiliary variables
  @Getter
  private int numberOfJobs;
  @Getter
  private int numberOfRenewableResources;
  @Getter
  private int numberOfNonrenewableResources;
  @Getter
  private int timeHorizon; // upper bound duration
  @Getter
  private int dueDate;  // upper bound duration

  public PsplibInstanceReader(BufferedReader bufferedReader) {
    List<List<String>> records = readCsvToNestedList(bufferedReader);
    initializeFieldsFromCsvRecords(records);
    numberOfModes = MODEKEY_TO_DURATION_MAP.size();
  }

  public IMrcpsp_Constants createConstants() {

    Map<String, Integer> modekey_StringToInt = new HashMap<>();
    Map<Integer, String> modekey_IntToString = new HashMap<>();
    Map<Integer, List<Integer>> jobNrToListOfModeNrMap = new HashMap<>();

    for (String mode : MODEKEY_TO_DURATION_MAP.keySet()) {
      modekey_StringToInt.put(mode, modekey_StringToInt.size());
      modekey_IntToString.put(modekey_IntToString.size(), mode);

      int jobNr = Integer.parseInt(mode.split("_")[0]);
      int modeNr = Integer.parseInt(mode.split("_")[1]);
      jobNrToListOfModeNrMap.putIfAbsent(jobNr, new ArrayList<>());
      jobNrToListOfModeNrMap.get(jobNr).add(modeNr);
    }

    Map<String, Integer> renewableResource_StringToInt = new HashMap<>();
    Map<Integer, String> renewableResourceId_IntToString = new HashMap<>();
    for (String resId : RENEWABLE_RESOURCE_CAPACITY.keySet()) {
      int resNr = Integer.parseInt(resId.substring(1));
      renewableResourceId_IntToString.put(resNr, resId);
      renewableResource_StringToInt.put(resId, resNr);
    }

    Map<String, Integer> nonrenewableResourceId_StringToInt = new HashMap<>();
    Map<Integer, String> nonrenewableResourceId_IntToString = new HashMap<>();
    for (String resId : NONRENEWABLE_RESOURCE_CAPACITY.keySet()) {
      int resNr = Integer.parseInt(resId.substring(1));
      nonrenewableResourceId_IntToString.put(resNr, resId);
      nonrenewableResourceId_StringToInt.put(resId, resNr);
    }

    // create lists of resource strings
    List<String> renewableResourceStrings = new ArrayList<>();
    for (Map<String, Integer> modeToResourceConsumptionMap :
        MODEKEY_TO_RENEWABLE_RESOURCE_CONSUMPTION.values()) {
      renewableResourceStrings.addAll(modeToResourceConsumptionMap.keySet());
    }
    List<String> nonrenewableResourceStrings = new ArrayList<>();
    for (Map<String, Integer> modeToResourceConsumptionMap :
        MODEKEY_TO_NONRENEWABLE_RESOURCE_CONSUMPTION.values()) {
      nonrenewableResourceStrings.addAll(modeToResourceConsumptionMap.keySet());
    }

    // create resource consumption maps (using activity/mode/resource indexes)
    Map<Integer, Map<Integer, Integer>> renewableResourceConsumptionMap = new HashMap<>();
    for (String mode : MODEKEY_TO_DURATION_MAP.keySet()) {
      int modeIndex = modekey_StringToInt.get(mode);
      renewableResourceConsumptionMap.putIfAbsent(modeIndex, new HashMap<>());

      for (String renewableResource : renewableResourceStrings) {
        if (MODEKEY_TO_RENEWABLE_RESOURCE_CONSUMPTION.get(mode).containsKey(renewableResource)) {
          renewableResourceConsumptionMap.get(modeIndex)
              .put(
                  renewableResource_StringToInt.get(renewableResource),
                  MODEKEY_TO_RENEWABLE_RESOURCE_CONSUMPTION.get(mode).get(renewableResource));
        }
      }
    }

    Map<Integer, Map<Integer, Integer>> nonrenewableResourceConsumptionMap = new HashMap<>();
    for (String mode : MODEKEY_TO_DURATION_MAP.keySet()) {
      int modeIndex = modekey_StringToInt.get(mode);
      nonrenewableResourceConsumptionMap.putIfAbsent(modeIndex, new HashMap<>());
      for (String nonrenewableResource : nonrenewableResourceStrings) {
        if (MODEKEY_TO_NONRENEWABLE_RESOURCE_CONSUMPTION.get(mode)
            .containsKey(nonrenewableResource)) {
          nonrenewableResourceConsumptionMap.get(modeIndex)
              .put(
                  nonrenewableResourceId_StringToInt.get(nonrenewableResource),
                  MODEKEY_TO_NONRENEWABLE_RESOURCE_CONSUMPTION.get(mode).get(nonrenewableResource));
        }
      }
    }

    // set of all job nrs
    Set<Integer> jobNrs =
        IntStream.rangeClosed(1, numberOfJobs)
            .boxed()
            .collect(Collectors.toSet());

    // create b_imk
    Map<Integer, Map<Integer, Map<Integer, Integer>>> b_imk = new HashMap<>();
    Map<Integer, Map<Integer, Map<Integer, Integer>>> w_imk = new HashMap<>();
    for (int i : jobNrs) {
      if (i != 1 && i != getNumberOfJobs()) {
        b_imk.putIfAbsent(i - 1, new HashMap<>());
        w_imk.putIfAbsent(i - 1, new HashMap<>());

        for (int m : jobNrToListOfModeNrMap.get(i)) {
          b_imk.get(i - 1).putIfAbsent(m, new HashMap<>());
          w_imk.get(i - 1).putIfAbsent(m, new HashMap<>());

          for (int k : renewableResource_StringToInt.values()) {
            if (MODEKEY_TO_RENEWABLE_RESOURCE_CONSUMPTION
                .get(getModeKey(String.valueOf(i), String.valueOf(m)))
                .containsKey(renewableResourceId_IntToString.get(k))) {

              b_imk.get(i - 1).get(m)
                  .put(
                      k,
                      MODEKEY_TO_RENEWABLE_RESOURCE_CONSUMPTION
                          .get(getModeKey(String.valueOf(i), String.valueOf(m)))
                          .get(renewableResourceId_IntToString.get(k)));
            }
          }

          for (int k : nonrenewableResourceId_StringToInt.values()) {
            if (MODEKEY_TO_NONRENEWABLE_RESOURCE_CONSUMPTION
                .get(getModeKey(String.valueOf(i), String.valueOf(m)))
                .containsKey(nonrenewableResourceId_IntToString.get(k))) {

              w_imk.get(i - 1).get(m)
                  .put(
                      k,
                      MODEKEY_TO_NONRENEWABLE_RESOURCE_CONSUMPTION
                          .get(getModeKey(String.valueOf(i), String.valueOf(m)))
                          .get(nonrenewableResourceId_IntToString.get(k)));
            }
          }
        }
      }
    }

    // create resource availability map
    Map<Integer, Integer> B_k = new HashMap<>();
    for (int k : renewableResourceId_IntToString.keySet()) {
      B_k.put(k, RENEWABLE_RESOURCE_CAPACITY.get(renewableResourceId_IntToString.get(k)));
    }
    Map<Integer, Integer> W_k = new HashMap<>();
    for (int k : nonrenewableResourceId_IntToString.keySet()) {
      W_k.put(k, NONRENEWABLE_RESOURCE_CAPACITY.get(nonrenewableResourceId_IntToString.get(k)));
    }

    // create activity index to mode indices map
    // create mode duration map
    Map<Integer, Set<Integer>> activityIndexToModeSetMap = new HashMap<>();
    Map<Integer, Map<Integer, Integer>> modeDurationMap = new HashMap<>();
    for (int i : jobNrs) {
      if (i != 1 && i != getNumberOfJobs()) {
        activityIndexToModeSetMap.put(i - 1, new HashSet<>(jobNrToListOfModeNrMap.get(i)));

        modeDurationMap.putIfAbsent(i - 1, new HashMap<>());
        for (int m : activityIndexToModeSetMap.get(i - 1)) {
          modeDurationMap.get(i - 1)
              .put(
                  m,
                  MODEKEY_TO_DURATION_MAP.get(getModeKey(String.valueOf(i), String.valueOf(m))));
        }
      }
    }

    // remove dummy activities and renumber non-dummy activities
    Set<Precedence> P_hat =
        PRECEDENCE_RELATION
            .stream()
            .filter(precedence -> precedence.get_i() != 1)
            .filter(precedence -> precedence.get_j() != getNumberOfJobs())
            .map(precedence -> new Precedence(precedence.get_i() - 1, precedence.get_j() - 1))
            .collect(Collectors.toSet());

    return
        new Mrcpsp_Constants(
            getNumberOfJobs() - 2,
            b_imk,
            w_imk,
            B_k,
            W_k,
            activityIndexToModeSetMap,
            P_hat,
            modeDurationMap,
            numberOfRenewableResources,
            numberOfNonrenewableResources);
  }

  private void initializeFieldsFromCsvRecords(List<List<String>> records) {
    readNumberOfRenewableResources(records);
    readNumberOfNonrenewableResources(records);
    readNumberOfJobs(records);
    readTimeHorizon(records);
    readDueDate(records);
    readPrecedenceRelation(records);
    readNumberOfModes(records);
    readModeDurations(records);
    readResourceConsumption(records);
    readResourceCapacity(records);
  }

  private void readNumberOfRenewableResources(List<List<String>> records) {
    numberOfRenewableResources = Integer.parseInt(
        records.get(getSingleLineFromRecords(records, NUMBER_OF_RENEWABLE_RESOURCES_KEY)).get(1));
  }

  private void readNumberOfNonrenewableResources(List<List<String>> records) {
    numberOfNonrenewableResources = Integer.parseInt(
        records.get(getSingleLineFromRecords(records, NUMBER_OF_NONRENEWABLE_RESOURCES_KEY))
            .get(1));
  }

  private void readNumberOfJobs(List<List<String>> records) {
    numberOfJobs = Integer.parseInt(
        records.get(getSingleLineFromRecords(records, NUMBER_OF_JOBS_KEY)).get(1));
  }

  private void readTimeHorizon(List<List<String>> records) {
    timeHorizon = Integer.parseInt(
        records.get(getSingleLineFromRecords(records, TIME_HORIZON_KEY)).get(1));
  }

  private void readDueDate(List<List<String>> records) {
    dueDate = Integer.parseInt(
        records.get(getSingleLineFromRecords(records, PROJECT_INFORMATION_KEY) + 2).get(3));
  }

  private void readPrecedenceRelation(List<List<String>> records) {
    int startLine = getStartLineFromRecords(records, PRECEDENCE_RELATIONS_KEY);
    int endLine = getEndLineFromRecords(records, REQUESTS_AND_DURATIONS_KEY);
    for (int line = startLine; line <= endLine; line++) {
      int numSuccessors = Integer.parseInt(records.get(line).get(2));
      for (int column = 3; column < 3 + numSuccessors; column++) {
        PRECEDENCE_RELATION.add(
            new Precedence(
                Integer.parseInt(records.get(line).get(0)),
                Integer.parseInt(records.get(line).get(column))));
      }
    }
  }

  private void readNumberOfModes(List<List<String>> records) {
    int startLine = getStartLineFromRecords(records, PRECEDENCE_RELATIONS_KEY);
    int endLine = getEndLineFromRecords(records, REQUESTS_AND_DURATIONS_KEY);
    for (int line = startLine; line <= endLine; line++) {
      JOBNRSTR_TO_NUMBER_OF_MODES_MAP.put(
          records.get(line).get(0),
          Integer.parseInt(records.get(line).get(1)));
    }
  }

  private void readModeDurations(List<List<String>> records) {
    int line = getStartLineFromRecords(records, REQUESTS_AND_DURATIONS_KEY);
    while (line <= getEndLineFromRecords(records, RESOURCE_AVAILABILITIES_KEY)) {
      MODEKEY_TO_DURATION_MAP.put(
          getModeKey(records.get(line).get(0), records.get(line).get(1)),
          Integer.parseInt(records.get(line).get(2)));

      for (int i = line + 1;
           i < line + JOBNRSTR_TO_NUMBER_OF_MODES_MAP.get(records.get(line).get(0)); i++) {
        // mode id = <job number>_<mode number>
        MODEKEY_TO_DURATION_MAP.put(
            getModeKey(records.get(line).get(0), records.get(i).get(0)),
            Integer.parseInt(records.get(i).get(1)));
      }
      line += JOBNRSTR_TO_NUMBER_OF_MODES_MAP.get(records.get(line).get(0));
    }
  }

  private void readResourceConsumption(List<List<String>> records) {
    int startLine = getStartLineFromRecords(records, REQUESTS_AND_DURATIONS_KEY);
    int line = startLine;
    while (line <= getEndLineFromRecords(records, RESOURCE_AVAILABILITIES_KEY)) {
      // column indices are reduced by 2 for startLine because separator is different in instance
      // files
      MODEKEY_TO_RENEWABLE_RESOURCE_CONSUMPTION.putIfAbsent(
          getModeKey(records.get(line).get(0), records.get(line).get(1)),
          new HashMap<>());

      for (int j = 3; j < 3 + numberOfRenewableResources; j++) {
        if (Double.parseDouble(records.get(line).get(j)) > 0) {
          MODEKEY_TO_RENEWABLE_RESOURCE_CONSUMPTION.get(
                  getModeKey(records.get(line).get(0), records.get(line).get(1)))
              .put(
                  records.get(startLine - 2).get(j - 2),
                  Integer.parseInt(records.get(line).get(j)));
        }
      }

      MODEKEY_TO_NONRENEWABLE_RESOURCE_CONSUMPTION.putIfAbsent(
          getModeKey(records.get(line).get(0), records.get(line).get(1)),
          new HashMap<>());

      for (int j = 3 + numberOfRenewableResources;
           j < 3 + numberOfRenewableResources + numberOfNonrenewableResources; j++) {
        if (Double.parseDouble(records.get(line).get(j)) > 0) {
          MODEKEY_TO_NONRENEWABLE_RESOURCE_CONSUMPTION.get(
                  getModeKey(records.get(line).get(0), records.get(line).get(1)))
              .put(
                  records.get(startLine - 2).get(j - 2),
                  Integer.parseInt(records.get(line).get(j)));
        }
      }

      for (int i = line + 1;
           i < line + JOBNRSTR_TO_NUMBER_OF_MODES_MAP.get(records.get(line).get(0)); i += 1) {
        MODEKEY_TO_RENEWABLE_RESOURCE_CONSUMPTION.putIfAbsent(
            getModeKey(records.get(line).get(0), records.get(i).get(0)), new HashMap<>());
        for (int j = 2; j < 2 + numberOfRenewableResources; j++) {
          if (Double.parseDouble(records.get(i).get(j)) > 0) {
            MODEKEY_TO_RENEWABLE_RESOURCE_CONSUMPTION.get(
                    getModeKey(records.get(line).get(0), records.get(i).get(0)))
                .put(
                    records.get(startLine - 2).get(j - 1),
                    Integer.parseInt(records.get(i).get(j)));
          }
        }
        MODEKEY_TO_NONRENEWABLE_RESOURCE_CONSUMPTION.putIfAbsent(
            getModeKey(records.get(line).get(0), records.get(i).get(0)), new HashMap<>());
        for (int j = 2 + numberOfRenewableResources;
             j < 2 + numberOfRenewableResources + numberOfNonrenewableResources; j++) {
          if (Double.parseDouble(records.get(i).get(j)) > 0) {
            MODEKEY_TO_NONRENEWABLE_RESOURCE_CONSUMPTION.get(
                    getModeKey(records.get(line).get(0), records.get(i).get(0)))
                .put(
                    records.get(startLine - 2).get(j - 1),
                    Integer.parseInt(records.get(i).get(j)));
          }
        }
      }
      line += JOBNRSTR_TO_NUMBER_OF_MODES_MAP.get(records.get(line).get(0));
    }
  }

  private void readResourceCapacity(List<List<String>> records) {
    int line = records.size() - 2;
    for (int column = 0; column < numberOfRenewableResources; column++) {
      RENEWABLE_RESOURCE_CAPACITY.put(
          records.get(line - 1).get(column),
          Integer.parseInt(records.get(line).get(column)));
    }
    for (int column = numberOfRenewableResources;
         column < numberOfRenewableResources + numberOfNonrenewableResources; column++) {
      NONRENEWABLE_RESOURCE_CAPACITY.put(
          records.get(line - 1).get(column),
          Integer.parseInt(records.get(line).get(column)));
    }
  }

  private List<List<String>> readCsvToNestedList(BufferedReader bufferedReader) {

    List<List<String>> records = new ArrayList<>();
    try {
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        String[] values = line.replaceFirst("^ ", "").split("  ");
        values = Arrays.stream(values).map(value -> value.replaceAll(":", ""))
            .map(value -> value.replaceAll(" ", "")).filter(value -> !value.equals(""))
            .collect(Collectors.toList()).toArray(new String[0]);
        records.add(Arrays.asList(values));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return records;
  }

  private int getSingleLineFromRecords(
      List<List<String>> records,
      String key) {
    int line = 0;
    while (!records.get(line).get(0).equals(key)) {
      line++;
      if (line >= records.size() - 1) {
        throw new IllegalArgumentException(
            "Key not found in records.");
      }
    }
    return line;
  }

  private int getStartLineFromRecords(
      List<List<String>> records,
      String startKey) {
    int line = 0;
    while (!records.get(line).get(0).equals(startKey)) {
      line++;
      if (line >= records.size() - 1) {
        throw new IllegalArgumentException(
            "Start key not found in records.");
      }
    }
    if (startKey.equals(REQUESTS_AND_DURATIONS_KEY)) {
      return line + 3;
    }
    return line + 2;
  }

  private int getEndLineFromRecords(
      List<List<String>> records,
      String endKey) {
    int line = 0;
    while (!records.get(line).get(0).equals(endKey)) {
      line++;
      if (line >= records.size() - 1) {
        throw new IllegalArgumentException("End key not found in records");
      }
    }
    return line - 2;
  }

  private String getModeKey(
      String jobId,
      String modeId) {
    return jobId + "_" + modeId;
  }
}
