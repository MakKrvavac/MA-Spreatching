package de.adesso.charts;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import static de.adesso.ElicitationAnalysisApp.SEPARATOR;
import static de.adesso.statistic.DemographicsCalculator.*;
import static de.adesso.statistic.QuestionnaireCalculator.ANSWER1_MAP_FREQUENCY;
import static de.adesso.statistic.QuestionnaireCalculator.ANSWER2_MAP_FREQUENCY;

public class CsvExporter {

    private static final String BASE_PATH = "src/main/resources/stats/csv/";

    public static void startCsvExporter() {
        System.out.println("Starting CSV Exporter...");
        exportAll();
        System.out.println("Finished CSV Exporter!");
        System.out.println(SEPARATOR);
    }

    public static void exportMapToCsv(Map<?, ?> map, String fileName) {
        String fullPath = BASE_PATH + fileName;
        ensureDirectoryExists(fullPath);
        try (FileWriter writer = new FileWriter(fullPath)) {
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                writer.append(entry.getKey().toString())
                        .append(",")
                        .append(entry.getValue().toString())
                        .append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exportNestedMapToCsv(Map<Integer, Map<String, Integer>> nestedMap, String fileName) {
        String fullPath = BASE_PATH + fileName;
        ensureDirectoryExists(fullPath);
        try (FileWriter writer = new FileWriter(fullPath)) {
            for (Map.Entry<Integer, Map<String, Integer>> entry : nestedMap.entrySet()) {
                for (Map.Entry<String, Integer> nestedEntry : entry.getValue().entrySet()) {
                    writer.append(String.valueOf(entry.getKey()))
                            .append(",")
                            .append(nestedEntry.getKey())
                            .append(",")
                            .append(nestedEntry.getValue().toString())
                            .append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exportAll() {
        exportMapToCsv(GENDER_MAP_FREQUENCY, "gender_frequency.csv");
        exportMapToCsv(AGE_MAP_FREQUENCY, "age_frequency.csv");
        exportMapToCsv(HANDEDNESS_MAP_FREQUENCY, "handedness_frequency.csv");
        exportMapToCsv(OWN_A_TABLET_MAP_FREQUENCY, "own_a_tablet_frequency.csv");
        exportMapToCsv(USE_TABLET_MAP_FREQUENCY, "use_tablet_frequency.csv");
        exportMapToCsv(USE_PENCIL_MAP_FREQUENCY, "use_pencil_frequency.csv");
        exportMapToCsv(USE_SPREADSHEETS_MAP_FREQUENCY, "use_spreadsheets_frequency.csv");
        exportMapToCsv(ANY_EXPERIENCE_MAP_FREQUENCY, "any_experience_frequency.csv");
        exportMapToCsv(USE_SPREADSHEETS_MULTI_TOUCH_MAP_FREQUENCY, "use_spreadsheets_multi_touch_frequency.csv");

        exportNestedMapToCsv(ANSWER1_MAP_FREQUENCY, "answer1_frequency.csv");
        exportNestedMapToCsv(ANSWER2_MAP_FREQUENCY, "answer2_frequency.csv");
    }

    private static void ensureDirectoryExists(String filePath) {
        File file = new File(filePath);
        File parentDirectory = file.getParentFile();
        if (!parentDirectory.exists()) {
            parentDirectory.mkdirs(); // Erstellt das Verzeichnis, einschließlich aller notwendigen übergeordneten Verzeichnisse
        }
    }

}
