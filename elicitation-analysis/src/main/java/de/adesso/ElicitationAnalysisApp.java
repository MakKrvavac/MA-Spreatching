package de.adesso;

import de.adesso.charts.CsvExporter;
import de.adesso.charts.ExcelExporter;
import de.adesso.core.FileReader;
import de.adesso.core.JsonReader;
import de.adesso.statistic.StatisticsCalculator;
import de.adesso.test.TTestAnalysis;

public class ElicitationAnalysisApp {

    public static final String SEPARATOR = "--------------------------------------";

    public  static void main(String[] args) {
        FileReader.startFileReader();
        JsonReader.startJsonReader();
        StatisticsCalculator.startStatisticsCalculator();
        CsvExporter.startCsvExporter();
        ExcelExporter.startExcelExporter();
        TTestAnalysis.startTTestAnalysis();
    }
}
