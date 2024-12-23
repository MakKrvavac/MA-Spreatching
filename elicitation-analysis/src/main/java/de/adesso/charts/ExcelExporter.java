package de.adesso.charts;

import de.adesso.model.Results;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static de.adesso.ElicitationAnalysisApp.SEPARATOR;
import static de.adesso.model.Results.*;
import static de.adesso.statistic.DemographicsCalculator.*;
import static de.adesso.statistic.QuestionnaireCalculator.ANSWER1_MAP_FREQUENCY;
import static de.adesso.statistic.QuestionnaireCalculator.ANSWER2_MAP_FREQUENCY;

public class ExcelExporter {

    private static final String BASE_PATH = "src/main/resources/stats/xlsx/";

    public static void startExcelExporter() {
        System.out.println("Starting Excel Exporter...");
        exportAllToExcel();
        System.out.println("Finished Excel Exporter!");
        System.out.println(SEPARATOR);
    }

    public static void exportMapToExcel(Map<?, ?> map, String fileName) throws IOException {
        Workbook workbook = new XSSFWorkbook(); // Erstellt eine neue Excel-Arbeitsmappe
        Sheet sheet = workbook.createSheet("Data"); // Erstellt ein neues Arbeitsblatt

        int rownum = 0;
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            Row row = sheet.createRow(rownum++);
            row.createCell(0).setCellValue(entry.getKey().toString());
            row.createCell(1).setCellValue(entry.getValue().toString());
        }

        // Anpassung der Spaltenbreite
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);

        // Speichern der Arbeitsmappe als Datei
        try (FileOutputStream out = new FileOutputStream(BASE_PATH + fileName)) {
            workbook.write(out);
        }

        workbook.close(); // Schließt die Arbeitsmappe und gibt Ressourcen frei
    }

    public static void exportNestedMapToExcel(Map<Integer, Map<String, Integer>> nestedMap, String fileName) throws IOException {
        Workbook workbook = new XSSFWorkbook(); // Erstellt eine neue Excel-Arbeitsmappe
        Sheet sheet = workbook.createSheet("Data"); // Erstellt ein neues Arbeitsblatt

        int rownum = 0;
        for (Map.Entry<Integer, Map<String, Integer>> entry : nestedMap.entrySet()) {
            for (Map.Entry<String, Integer> nestedEntry : entry.getValue().entrySet()) {
                Row row = sheet.createRow(rownum++);
                row.createCell(0).setCellValue(String.valueOf(entry.getKey()));
                row.createCell(1).setCellValue(nestedEntry.getKey());
                row.createCell(2).setCellValue(nestedEntry.getValue());
            }
        }

        // Anpassung der Spaltenbreite
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);

        // Speichern der Arbeitsmappe als Datei
        try (FileOutputStream out = new FileOutputStream(BASE_PATH + fileName)) {
            workbook.write(out);
        }

        workbook.close(); // Schließt die Arbeitsmappe und gibt Ressourcen frei
    }

    public static void exportNestedMapToExcel2(Map<Integer, List<Integer>> nestedMap, String fileName) throws IOException {
        Workbook workbook = new XSSFWorkbook(); // Erstellt eine neue Excel-Arbeitsmappe
        Sheet sheet = workbook.createSheet("Data"); // Erstellt ein neues Arbeitsblatt

        int rownum = 0;
        for (Map.Entry<Integer, List<Integer>> entry : nestedMap.entrySet()) {
            Row row = sheet.createRow(rownum++);
            row.createCell(0).setCellValue(String.valueOf(entry.getKey()));
            int cellnum = 1;
            for (Integer nestedEntry : entry.getValue()) {
                row.createCell(cellnum).setCellValue(nestedEntry);
                cellnum++;
            }
        }

        // Anpassung der Spaltenbreite
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);

        // Speichern der Arbeitsmappe als Datei
        try (FileOutputStream out = new FileOutputStream(BASE_PATH + fileName)) {
            workbook.write(out);
        }

        workbook.close(); // Schließt die Arbeitsmappe und gibt Ressourcen frei
    }

    public static void exportNestedMapToExcel3(Map<Integer, List<String>> nestedMap, String fileName) throws IOException {
        Workbook workbook = new XSSFWorkbook(); // Erstellt eine neue Excel-Arbeitsmappe
        Sheet sheet = workbook.createSheet("Data"); // Erstellt ein neues Arbeitsblatt

        int rownum = 0;
        for (Map.Entry<Integer, List<String>> entry : nestedMap.entrySet()) {
            Row row = sheet.createRow(rownum++);
            row.createCell(0).setCellValue(String.valueOf(entry.getKey()));
            int cellnum = 1;
            for (String nestedEntry : entry.getValue()) {
                row.createCell(cellnum).setCellValue(nestedEntry);
                cellnum++;
            }
        }

        // Anpassung der Spaltenbreite
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);

        // Speichern der Arbeitsmappe als Datei
        try (FileOutputStream out = new FileOutputStream(BASE_PATH + fileName)) {
            workbook.write(out);
        }

        workbook.close(); // Schließt die Arbeitsmappe und gibt Ressourcen frei
    }


        // Methode zum Exportieren aller Daten als Excel
    public static void exportAllToExcel() {
        try {
            exportMapToExcel(GENDER_MAP_FREQUENCY, "gender_frequency.xlsx");
            exportMapToExcel(AGE_MAP_FREQUENCY, "age_frequency.xlsx");
            exportMapToExcel(HANDEDNESS_MAP_FREQUENCY, "handedness_frequency.xlsx");
            exportMapToExcel(OWN_A_TABLET_MAP_FREQUENCY, "own_a_tablet_frequency.xlsx");
            exportMapToExcel(USE_TABLET_MAP_FREQUENCY, "use_tablet_frequency.xlsx");
            exportMapToExcel(USE_PENCIL_MAP_FREQUENCY, "use_pencil_frequency.xlsx");
            exportMapToExcel(USE_SPREADSHEETS_MAP_FREQUENCY, "use_spreadsheets_frequency.xlsx");
            exportMapToExcel(ANY_EXPERIENCE_MAP_FREQUENCY, "any_experience_frequency.xlsx");
            exportMapToExcel(USE_SPREADSHEETS_MULTI_TOUCH_MAP_FREQUENCY, "use_spreadsheets_multi_touch_frequency.xlsx");

            exportNestedMapToExcel(ANSWER1_MAP_FREQUENCY, "answer1_frequency_task.xlsx");
            exportNestedMapToExcel(ANSWER2_MAP_FREQUENCY, "answer2_frequency_task.xlsx");

            exportNestedMapToExcel2(RESETS_MAP, "resets_frequency_task.xlsx");
            exportNestedMapToExcel2(TIME_WATCHING_MAP, "time_watching_frequency_task.xlsx");
            exportNestedMapToExcel2(TIME_DRAWING_MAP, "time_drawing_frequency_task.xlsx");

            exportNestedMapToExcel3(Results.DEMOGRAPHICS_MAPS, "demographics.xlsx");
            exportNestedMapToExcel3(ANSWER1_MAP, "answer1_task.xlsx");
            exportNestedMapToExcel3(ANSWER2_MAP, "answer2_task.xlsx");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
