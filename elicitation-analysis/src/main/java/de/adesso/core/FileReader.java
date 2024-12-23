package de.adesso.core;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static de.adesso.ElicitationAnalysisApp.SEPARATOR;

/**
 * This class reads the files from the studies and saves them in the correct directory.
 * The files are saved in the following directories: drawingTaskImage, questionnaire, taskDetail, demographics
 */
public class FileReader {
    private static final String SOURCE_PATH = "/Users/mak.krvavac/Desktop/Studienergebnisse/";
    private static final String DESTINATION_PATH = "src/main/resources/studies/";
    static String STUDIES_PATH = SOURCE_PATH + "Ausgewertet/";

    public static int STUDY_NUMBER = 0;

    private static final List<Integer> femaleIds = Arrays.asList(19, 20, 23, 25, 29, 34, 1, 6, 9, 11, 12, 14);
    private static final List<Integer> maleIds = Arrays.asList(21, 22, 24, 26, 27, 28, 30, 31, 32, 33, 35, 36, 2, 3, 4, 5, 7, 8, 10, 13, 15, 16, 17);

    private static final List<Integer> youngAgeIds = Arrays.asList(6, 11, 12, 17, 22, 26, 35);
    private static final List<Integer> oldAgeIds = Arrays.asList(1, 2, 3, 5, 10, 13, 14, 16, 18, 30, 34);
    private static final List<Integer> middleAgeIds = Arrays.asList(4, 7, 8, 9, 15, 19, 20, 21, 23, 24, 25, 27, 28, 29, 31, 32, 33, 36);

    private static final List<Integer> usePencilIds = Arrays.asList(1, 5, 6, 9, 10, 11, 12, 13, 14, 15, 19, 21, 22, 23, 24, 28, 29, 31, 32, 35, 36);
    private static final List<Integer> notUsePencilIds = Arrays.asList(2, 3, 4, 7, 8, 16, 17, 18, 20, 25, 26, 27, 30, 33, 34);

    private static final List<Integer> englishIds = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18);
    private static final List<Integer> germanIds = Arrays.asList(19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36);

    static String[] STUDY_NAMES = {
            "ENGLISH_16239_DATA",
            "ENGLISH_129245_DATA",
            "ENGLISH_141974_DATA",
            "ENGLISH_170430_DATA",
            "ENGLISH_278884_DATA",
            "ENGLISH_299125_DATA",
            "ENGLISH_382576_DATA",
            "ENGLISH_383318_DATA",
            "ENGLISH_395858_DATA",
            "ENGLISH_452807_DATA",
            "ENGLISH_581502_DATA",
            "ENGLISH_582125_DATA",
            "ENGLISH_689523_DATA",
            "ENGLISH_746142_DATA",
            "ENGLISH_893514_DATA",
            "ENGLISH_895439_DATA",
            "ENGLISH_948959_DATA",
            "ENGLISH_966789_DATA",
            "GERMAN_14038_DATA",
            "GERMAN_50056_DATA",
            "GERMAN_79510_DATA",
            "GERMAN_116368_DATA",
            "GERMAN_171450_DATA",
            "GERMAN_224737_DATA",
            "GERMAN_226804_DATA",
            "GERMAN_389938_DATA",
            "GERMAN_417751_DATA",
            "GERMAN_467712_DATA",
            "GERMAN_508355_DATA",
            "GERMAN_647999_DATA",
            "GERMAN_652910_DATA",
            "GERMAN_884209_DATA",
            "GERMAN_905436_DATA",
            "GERMAN_986610_DATA",
            "GERMAN_992706_DATA",
            "GERMAN_305553_DATA"
    };

    static int COUNTER = 1;

    static List<String> fileNamesDrawingTaskImage = new ArrayList<>();
    static List<String> fileNamesQuestionnaire = new ArrayList<>();
    static List<String> fileNamesTaskDetail = new ArrayList<>();

    public static void startFileReader() {
        System.out.println(SEPARATOR);
        System.out.println("Start reading files...");
        System.out.println(SEPARATOR);
        int studyNumber = 1;
        for (String studyName : STUDY_NAMES) {
            STUDIES_PATH = SOURCE_PATH + "Ausgewertet/" + studyName + "/";
            System.out.println("Study: " + studyName + " - " + studyNumber + " - " + STUDIES_PATH);
            createFiles(studyNumber);
            COUNTER = 1;
            fileNamesDrawingTaskImage.clear();
            fileNamesQuestionnaire.clear();
            fileNamesTaskDetail.clear();
            studyNumber++;
        }
        STUDY_NUMBER = STUDY_NAMES.length;
        System.out.println(SEPARATOR);
        System.out.println("Finished reading files. Found " + STUDY_NUMBER + " studies.");
        System.out.println("Validating files...");
        System.out.println(SEPARATOR);
        validateFiles();
        System.out.println(SEPARATOR);
        System.out.println("Finished validating files.");
    }

    private static void createFiles(int studyNumber) {
        // P Tasks
        for (int i = 1; i <= 2; i++) {
            createFileName(1, 2);
        }

        // A Tasks
        for (int i = 3; i <= 7; i++) {
            createFileName(3, 7);
        }

        // B Tasks
        for (int i = 8; i <= 15; i++) {
            createFileName(8, 15);
        }

        // C Tasks
        for (int i = 16; i <= 20; i++) {
            createFileName(16, 20);
        }
        saveFileToDirectory(studyNumber);
    }

    private static void createFileName(int start, int end) {
        String drawingTaskImage = "";
        String questionnaire = "";
        String taskDetail = "";

        for (int i = start; i <= end; i++) {
            int RESETS_COUNTER = 0;

            drawingTaskImage = i + "_drawing_task" + COUNTER + "_resets" + RESETS_COUNTER + ".png";

            try (FileInputStream fis = new FileInputStream(STUDIES_PATH + drawingTaskImage)) {
                try {
                    while (true) {
                        int RESETS_COUNTER_TEST = RESETS_COUNTER + 1;
                        FileInputStream fis1 = new FileInputStream(STUDIES_PATH + i + "_drawing_task" + COUNTER + "_resets" + RESETS_COUNTER_TEST + ".json");
                        RESETS_COUNTER++;
                    }
                }
                catch (IOException e) {
                    drawingTaskImage = i + "_drawing_task" + COUNTER + "_resets" + RESETS_COUNTER + ".png";
                    questionnaire = i + "_questionnaire_task" + COUNTER + ".json";
                    taskDetail = i + "_task_detail" + COUNTER + "_resets" + RESETS_COUNTER + ".json";
                    //System.out.println("File exists" + drawingTaskJson);

                    fileNamesDrawingTaskImage.add(drawingTaskImage);
                    fileNamesQuestionnaire.add(questionnaire);
                    fileNamesTaskDetail.add(taskDetail);
                    //TODO: store task directly in a separat directory
                }

                COUNTER++;
            } catch (IOException e) {

            }
        }
    }

    private static void saveFileToDirectory(int studyNumber) {

        for (int i = 0; i < 20; i++) {
            int id = i + 1;
            String drawingTaskImagePath = DESTINATION_PATH + "drawingTaskImage/" + id + "/" + studyNumber + "_drawing_task.png";
            String questionnairePath = DESTINATION_PATH + "questionnaire/" + id + "/" + studyNumber + "_questionnaire.json";
            String taskDetailPath = DESTINATION_PATH + "taskDetail/" + id + "/" + studyNumber + "_task_detail.json";

            saveFile(drawingTaskImagePath, STUDIES_PATH + fileNamesDrawingTaskImage.get(i));
            //Group by gender
            if (femaleIds.contains(studyNumber)) {
                String drawingTaskImageFemalePath = DESTINATION_PATH + "drawingTaskImageFemale/" + id + "/" + studyNumber + "_drawing_task.png";
                saveFile(drawingTaskImageFemalePath, STUDIES_PATH + fileNamesDrawingTaskImage.get(i));
            } else if (maleIds.contains(studyNumber)) {
                String drawingTaskImageMalePath = DESTINATION_PATH + "drawingTaskImageMale/" + id + "/" + studyNumber + "_drawing_task.png";
                saveFile(drawingTaskImageMalePath, STUDIES_PATH + fileNamesDrawingTaskImage.get(i));
            }
            //Group by age
            if (youngAgeIds.contains(studyNumber)) {
                String drawingTaskImageYoungPath = DESTINATION_PATH + "drawingTaskImageYoung/" + id + "/" + studyNumber + "_drawing_task.png";
                saveFile(drawingTaskImageYoungPath, STUDIES_PATH + fileNamesDrawingTaskImage.get(i));
            } else if (middleAgeIds.contains(studyNumber)) {
                String drawingTaskImageMiddlePath = DESTINATION_PATH + "drawingTaskImageMiddle/" + id + "/" + studyNumber + "_drawing_task.png";
                saveFile(drawingTaskImageMiddlePath, STUDIES_PATH + fileNamesDrawingTaskImage.get(i));
            } else if (oldAgeIds.contains(studyNumber)) {
                String drawingTaskImageOldPath = DESTINATION_PATH + "drawingTaskImageOld/" + id + "/" + studyNumber + "_drawing_task.png";
                saveFile(drawingTaskImageOldPath, STUDIES_PATH + fileNamesDrawingTaskImage.get(i));
            }
            //Group by pencil usage
            if (usePencilIds.contains(studyNumber)) {
                String drawingTaskImageUsePencilPath = DESTINATION_PATH + "drawingTaskImageUsePencil/" + id + "/" + studyNumber + "_drawing_task.png";
                saveFile(drawingTaskImageUsePencilPath, STUDIES_PATH + fileNamesDrawingTaskImage.get(i));
            } else if (notUsePencilIds.contains(studyNumber)) {
                String drawingTaskImageNotUsePencilPath = DESTINATION_PATH + "drawingTaskImageNotUsePencil/" + id + "/" + studyNumber + "_drawing_task.png";
                saveFile(drawingTaskImageNotUsePencilPath, STUDIES_PATH + fileNamesDrawingTaskImage.get(i));
            }
            //Group by language
            if (englishIds.contains(studyNumber)) {
                String drawingTaskImageEnglishPath = DESTINATION_PATH + "drawingTaskImageEnglish/" + id + "/" + studyNumber + "_drawing_task.png";
                saveFile(drawingTaskImageEnglishPath, STUDIES_PATH + fileNamesDrawingTaskImage.get(i));
            } else if (germanIds.contains(studyNumber)) {
                String drawingTaskImageGermanPath = DESTINATION_PATH + "drawingTaskImageGerman/" + id + "/" + studyNumber + "_drawing_task.png";
                saveFile(drawingTaskImageGermanPath, STUDIES_PATH + fileNamesDrawingTaskImage.get(i));
            }
            saveFile(questionnairePath, STUDIES_PATH + fileNamesQuestionnaire.get(i));
            saveFile(taskDetailPath, STUDIES_PATH + fileNamesTaskDetail.get(i));
        }
        String demographicsPath = DESTINATION_PATH + "demographics/" + studyNumber + "_demographics.json";
        saveFile(demographicsPath, STUDIES_PATH + "21_demographics.json");
    }

    /**
     * Saves the file to the directory
     * @param sourcePath
     * @param destinationPath
     */
    public static void saveFile(String sourcePath, String destinationPath) {
        try {
            Files.createDirectories(Paths.get(sourcePath).getParent());
        } catch (IOException e) {
            System.out.println("Error creating directory: " + e.getMessage());
        }

        try (FileInputStream fis = new FileInputStream(destinationPath);
             FileOutputStream fos = new FileOutputStream(sourcePath)) {

            // Copy file contents from source to destination
            byte[] buffer = new byte[1024]; // Adjust buffer size as needed
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    /**
     * Validates the files in the directories drawingTaskImage, questionnaire, taskDetail, and demographics
     * The files are validated by checking if the files exist in the directories
     * If the files do not exist, an error message is printed
     * All files exist in the directory if directory contains STUDY_NAMES.length + 1 files
     */
    private static void validateFiles() {
        int drawingTaskImageFiles = 0;
        int questionnaireFiles = 0;
        int taskDetailFiles = 0;
        int demographicsFiles = 0;

        for (int i = 1; i <= 20; i++) {
            String drawingTaskImageDirectory = DESTINATION_PATH + "drawingTaskImage/" + i + "/";
            String questionnaireDirectory = DESTINATION_PATH + "questionnaire/" + i + "/";
            String taskDetailDirectory = DESTINATION_PATH + "taskDetail/" + i + "/";

            drawingTaskImageFiles += countFilesInDirectory(drawingTaskImageDirectory);
            questionnaireFiles += countFilesInDirectory(questionnaireDirectory);
            taskDetailFiles += countFilesInDirectory(taskDetailDirectory);
        }

        String demographicsDirectory = DESTINATION_PATH + "demographics/";
        demographicsFiles += countFilesInDirectory(demographicsDirectory);

        if (demographicsFiles == STUDY_NUMBER) {
            System.out.println("All demographics files exist in the directory");
        } else {
            System.out.println("Error: Demographics files do not exist in the directory");
        }

        if (drawingTaskImageFiles == STUDY_NUMBER * 20) {
            System.out.println("All drawing task image files exist in the directory");
        } else {
            System.out.println("Error: Drawing task image files do not exist in the directory");
        }

        if (questionnaireFiles == STUDY_NUMBER * 20) {
            System.out.println("All questionnaire files exist in the directory");
        } else {
            System.out.println("Error: Questionnaire files do not exist in the directory");
        }

        if (taskDetailFiles == STUDY_NUMBER * 20) {
            System.out.println("All task detail files exist in the directory");
        } else {
            System.out.println("Error: Task detail files do not exist in the directory");
        }
    }

    private static int countFilesInDirectory(String directory) {
        int count = 0;
        try {
            count = (int) Files.list(Paths.get(directory)).count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (count != STUDY_NUMBER) {
            System.out.println("Error: " + directory + " does not contain " + STUDY_NUMBER + " files");
        }
        return count;
    }
}
