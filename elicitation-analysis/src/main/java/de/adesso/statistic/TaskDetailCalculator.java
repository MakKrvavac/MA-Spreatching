package de.adesso.statistic;

import de.adesso.model.Results;

import java.util.*;

import static de.adesso.ElicitationAnalysisApp.SEPARATOR;
import static de.adesso.core.JsonReader.*;

public class TaskDetailCalculator {

    public static Map<Integer, List<Integer>> RESETS_MAP = new HashMap<>();
    public static Map<Integer, List<Integer>> TIME_WATCHING_MAP = new HashMap<>();
    public static Map<Integer, List<Integer>> TIME_DRAWING_MAP = new HashMap<>();

    public static void calculateTaskDetail() {
        System.out.println("Analysis of the taskDetail data...");
        System.out.println(SEPARATOR);

        RESETS_MAP = calculateResets();
        Results.RESETS_MAP = RESETS_MAP;
        int i = 1;
        for (Map.Entry<Integer, List<Integer>> entry : RESETS_MAP.entrySet()) {
            double average = calculateAverageInteger(entry.getValue());
            double mean = calculateMeanInteger(entry.getValue());
            double median = calculateMedianInteger(entry.getValue());
            double mode = calculateModeInteger(entry.getValue());
            System.out.println("Resets Task " + entry.getKey() + ": " + entry.getValue());
            System.out.println("-> Average Task " + entry.getKey() + ": " + average + ", Mean Task " + entry.getKey() + ": " + mean + ", Median Task " + entry.getKey() + ": " + median + ", Mode Task " + entry.getKey() + ": " + mode);
            i++;
        }
        System.out.println("\n");

        TIME_WATCHING_MAP = calculateTimeWatching();
        Results.TIME_WATCHING_MAP = TIME_WATCHING_MAP;
        i = 1;
        for (Map.Entry<Integer, List<Integer>> entry : TIME_WATCHING_MAP.entrySet()) {
            List<Double> secondsList = convertMilisecondsListToSecondsList(entry.getValue());
            double average = calculateAverage(secondsList);
            double mean = calculateMean(secondsList);
            double median = calculateMedian(secondsList);
            double mode = calculateMode(secondsList);
            System.out.println("TimeWatching Task " + entry.getKey() + ": " + secondsList);
            System.out.println("-> Average Task " + entry.getKey() + ": " + average + ", Mean Task " + entry.getKey() + ": " + mean + ", Median Task " + entry.getKey() + ": " + median + ", Mode Task " + entry.getKey() + ": " + mode);
            i++;
        }
        System.out.println("\n");

        TIME_DRAWING_MAP = calculateTimeDrawing();
        Results.TIME_DRAWING_MAP = TIME_DRAWING_MAP;
        i = 1;
        for (Map.Entry<Integer, List<Integer>> entry : TIME_DRAWING_MAP.entrySet()) {
            List<Double> secondsList = convertMilisecondsListToSecondsList(entry.getValue());
            double average = calculateAverage(secondsList);
            double mean = calculateMean(secondsList);
            double median = calculateMedian(secondsList);
            double mode = calculateMode(secondsList);
            System.out.println("TimeDrawing Task " + entry.getKey() + ": " + secondsList);
            System.out.println("-> Average Task " + entry.getKey() + ": " + average + ", Mean Task " + entry.getKey() + ": " + mean + ", Median Task " + entry.getKey() + ": " + median + ", Mode Task " + entry.getKey() + ": " + mode);
            i++;
        }

        System.out.println(SEPARATOR);
        System.out.println("Finished analysis of the taskDetail data.");
    }

    public static Map<Integer, List<Integer>> calculateResets() {
        Map<Integer, List<Integer>> resetsAll = new HashMap<>();
        int taskNumber = 1;
        for (List<Integer> entries : resetsMap.values()) {
            resetsAll.put(taskNumber, entries);
            taskNumber++;
        }
        return resetsAll;
    }

    public static Map<Integer, List<Integer>> calculateTimeWatching() {
        Map<Integer, List<Integer>> timeWatchingAll = new HashMap<>();
        int taskNumber = 1;
        for (List<Integer> entries : timeWatchingMap.values()) {
            timeWatchingAll.put(taskNumber, entries);
            taskNumber++;
        }
        return timeWatchingAll;
    }

    public static Map<Integer, List<Integer>> calculateTimeDrawing() {
        Map<Integer, List<Integer>> timeDrawingAll = new HashMap<>();
        int taskNumber = 1;
        for (List<Integer> entries : timeDrawingMap.values()) {
            timeDrawingAll.put(taskNumber, entries);
            taskNumber++;
        }
        return timeDrawingAll;
    }

    private static List<Double> convertMilisecondsListToSecondsList(List<Integer> milisecondsList) {
        List<Double> secondsList = new ArrayList<>();
        for (Integer miliseconds : milisecondsList) {
            secondsList.add(miliseconds / 1000.0);
        }
        return secondsList;
    }

    public static double calculateAverageInteger(List<Integer> data) {
        return data.stream().mapToDouble(Number::doubleValue).average().orElse(Double.NaN);
    }

    public static double calculateAverage(List<Double> data) {
        return data.stream().mapToDouble(Number::doubleValue).average().orElse(Double.NaN);
    }

    public static double calculateMeanInteger(List<Integer> durations) {
        if (durations.isEmpty()) return 0;
        double sum = 0;
        for (Integer duration : durations) {
            sum += duration;
        }
        return sum / durations.size();
    }

    public static double calculateMean(List<Double> durations) {
        if (durations.isEmpty()) return 0;
        double sum = 0;
        for (Double duration : durations) {
            sum += duration;
        }
        return sum / durations.size();
    }

    public static double calculateMedianInteger(List<Integer> durations) {
        if (durations.isEmpty()) return 0;
        Collections.sort(durations);
        int middle = durations.size() / 2;
        if (durations.size() % 2 == 0) {
            return (durations.get(middle - 1) + durations.get(middle)) / 2.0;
        } else {
            return durations.get(middle);
        }
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

    public static Double calculateModeInteger(List<Integer> durations) {
        if (durations == null || durations.isEmpty()) {
            return null;
        }

        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (Integer duration : durations) {
            frequencyMap.put(duration, frequencyMap.getOrDefault(duration, 0) + 1);
        }

        int maxFrequency = Collections.max(frequencyMap.values());
        Double mode = null;
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() == maxFrequency) {
                mode = entry.getKey().doubleValue();
                break;
            }
        }

        return mode;
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
