package de.adesso.statistic;

import de.adesso.core.JsonReader;
import de.adesso.model.Results;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static de.adesso.ElicitationAnalysisApp.SEPARATOR;
import static de.adesso.core.FileReader.STUDY_NUMBER;
import static de.adesso.core.JsonReader.*;
import static de.adesso.core.JsonReader.answer1Map;

public class QuestionnaireCalculator {

    public static Map<Integer, List<String>> ANSWER1_MAP = new HashMap<>();
    public static Map<Integer, List<String>> ANSWER2_MAP = new HashMap<>();
    public static Map<Integer, Map<String, Integer>> ANSWER1_MAP_FREQUENCY = new HashMap<>();
    public static Map<Integer, Map<String, Integer>> ANSWER2_MAP_FREQUENCY = new HashMap<>();
    public static Map<Integer, List<Double>> DURATION_MAP = new HashMap<>();

    public static void calculateQuestionnaire() {
        System.out.println("Analysis of the questionnaire data...");
        System.out.println(SEPARATOR);
        ANSWER1_MAP_FREQUENCY = calculateAnswer1Frequency();
        for (Map.Entry<Integer, Map<String, Integer>> entry : ANSWER1_MAP_FREQUENCY.entrySet()) {
            System.out.println("Answer1 Task " + entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("\n");
        ANSWER2_MAP_FREQUENCY = calculateAnswer2Frequency();
        for (Map.Entry<Integer, Map<String, Integer>> entry : ANSWER2_MAP_FREQUENCY.entrySet()) {
            System.out.println("Answer2 Task " + entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("\n");
        DURATION_MAP = calculateDurations();
        for (Map.Entry<Integer, List<Double>> entry : DURATION_MAP.entrySet()) {
            double average = calculateAverage(entry.getValue());
            double mean = calculateMean(entry.getValue());
            double median = calculateMedian(entry.getValue());
            double mode = calculateMode(entry.getValue());
            System.out.println("Duration Task " + entry.getKey() + ": " + entry.getValue());
            System.out.println("-> Average Task " + entry.getKey() + ": " + average + ", Mean Task " + entry.getKey() + ": " + mean + ", Median Task " + entry.getKey() + ": " + median + ", Mode Task " + entry.getKey() + ": " + mode);
        }
        System.out.println("\n");
        ANSWER1_MAP = calculateAnswer1Map();
        Results.ANSWER1_MAP = ANSWER1_MAP;
        System.out.println("Answer1 Map: " + ANSWER1_MAP);
        ANSWER2_MAP = calculateAnswer2Map();
        Results.ANSWER2_MAP = ANSWER2_MAP;
        System.out.println("Answer2 Map: " + ANSWER2_MAP);
        System.out.println(SEPARATOR);
        System.out.println("Finished analysis of the questionnaire data.");

    }

    public static Map<Integer, List<String>> calculateAnswer1Map() {
        return new HashMap<>(JsonReader.answer1Map);
    }

    public static Map<Integer, List<String>> calculateAnswer2Map() {
        return new HashMap<>(JsonReader.answer2Map);
    }

    public static Map<Integer, Map<String, Integer>> calculateAnswer1Frequency() {
        Map<String, Integer> answer1Mapping = new HashMap<>();
        answer1Mapping.put("very easy", 0);
        answer1Mapping.put("easy", 1);
        answer1Mapping.put("neutral", 2);
        answer1Mapping.put("difficult", 3);
        answer1Mapping.put("very difficult", 4);
        answer1Mapping.put("Sehr einfach", 0);
        answer1Mapping.put("Einfach", 1);
        answer1Mapping.put("Neutral", 2);
        answer1Mapping.put("Schwer", 3);
        answer1Mapping.put("Sehr schwer", 4);

        Map<Integer, Map<String, Integer>> frequenciesAll = new HashMap<>();
        int taskNumber = 1;
        for (List<String> answer1List: answer1Map.values()) {
            Map<String, Integer> frequencies = new HashMap<>();
            for (String answer : answer1List) {
                if (answer1Mapping.containsKey(answer)) {
                    String mappedCategory = getKeyByValue(answer1Mapping, answer1Mapping.get(answer));
                    frequencies.put(mappedCategory, frequencies.getOrDefault(mappedCategory, 0) + 1);
                }
            }
            frequenciesAll.put(taskNumber, frequencies);
            taskNumber++;
        }
        return frequenciesAll;
    }

    public static Map<Integer, Map<String, Integer>> calculateAnswer2Frequency() {
        Map<String, Integer> answer2Mapping = new HashMap<>();
        answer2Mapping.put("very satisfied", 0);
        answer2Mapping.put("satisfied", 1);
        answer2Mapping.put("neutral", 2);
        answer2Mapping.put("unsatisfied", 3);
        answer2Mapping.put("very unsatisfied", 4);
        answer2Mapping.put("Sehr zufrieden", 0);
        answer2Mapping.put("Zufrieden", 1);
        answer2Mapping.put("Neutral", 2);
        answer2Mapping.put("Unzufrieden", 3);
        answer2Mapping.put("Sehr unzufrieden", 4);

        Map<Integer, Map<String, Integer>> frequenciesAll = new HashMap<>();
        int taskNumber = 1;
        for (List<String> answer2List: answer2Map.values()) {
            Map<String, Integer> frequencies = new HashMap<>();
            for (String answer : answer2List) {
                if (answer2Mapping.containsKey(answer)) {
                    String mappedCategory = getKeyByValue(answer2Mapping, answer2Mapping.get(answer));
                    frequencies.put(mappedCategory, frequencies.getOrDefault(mappedCategory, 0) + 1);
                }
            }
            frequenciesAll.put(taskNumber, frequencies);
            taskNumber++;
        }
        return frequenciesAll;
    }

    // Hilfsmethode, um den Schlüssel (Kategorie) basierend auf dem Wert in der Zuordnungs-Map zu erhalten
    public static String getKeyByValue(Map<String, Integer> map, Integer value) {
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                return entry.getKey(); // Rückgabe des ersten gefundenen Schlüssels
            }
        }
        return null; // Falls kein passender Schlüssel gefunden wurde
    }

    public static Map<Integer, List<Double>> calculateDurations() {
        Map<Integer, List<Double>> durationAll = new HashMap<>();
        startTimeMap.forEach((taskId, startTimes) -> {
            List<Double> durations = new ArrayList<>();

            List<LocalDateTime> endTimes = endTimeMap.get(taskId);
            for (int i = 0; i < startTimes.size(); i++) {
                Duration duration = Duration.between(startTimes.get(i), endTimes.get(i));
                double seconds = duration.toNanos() / 1e9; // Konvertierung zu Sekunden
                // Runde auf 2 Nachkommastellen
                double roundedSeconds = BigDecimal.valueOf(seconds)
                        .setScale(2, RoundingMode.HALF_UP)
                        .doubleValue();
                durations.add(roundedSeconds);
            }
            durationAll.put(taskId, durations);
        });
        return durationAll;
    }

    public static double calculateAverage(List<Double> data) {
        return data.stream().mapToDouble(Number::doubleValue).average().orElse(Double.NaN);
    }

    public static double calculateMean(List<Double> durations) {
        if (durations.isEmpty()) return 0;
        double sum = 0;
        for (Double duration : durations) {
            sum += duration;
        }
        return sum / durations.size();
    }

    public static double calculateMedian(List<Double> durations) {
        if (durations.isEmpty()) return 0;
        Collections.sort(durations);
        int middle = durations.size() / 2;
        if (durations.size() % 2 == 0) {
            return (durations.get(middle - 1) + durations.get(middle)) / 2.0;
        } else {
            return durations.get(middle);
        }
    }

    public static Double calculateMode(List<Double> durations) {
        if (durations == null || durations.isEmpty()) {
            return null;
        }

        Map<Double, Integer> frequencyMap = new HashMap<>();
        for (Double duration : durations) {
            frequencyMap.put(duration, frequencyMap.getOrDefault(duration, 0) + 1);
        }

        int maxFrequency = Collections.max(frequencyMap.values());
        Double mode = null;
        for (Map.Entry<Double, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() == maxFrequency) {
                mode = entry.getKey();
                break;
            }
        }

        return mode;
    }
}
