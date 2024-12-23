package de.adesso.core;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static de.adesso.ElicitationAnalysisApp.SEPARATOR;
import static de.adesso.core.FileReader.STUDY_NUMBER;

/**
 * This class is used to read JSON files.
 * It is used to read the JSON files that contain the data of the studies.
 * - resources/studies/demographics
 * - resources/studies/questionnaire
 * - resources/studies/taskDetail
 */
public class JsonReader {

    /**
     * Demographics data
     */
    public static List<String> genderList = new ArrayList<>();
    public static List<String> ageList = new ArrayList<>();
    public static List<String> leftHandedOrRightHandedList = new ArrayList<>();
    public static List<Boolean> ownATabletList = new ArrayList<>();
    public static List<String> useTabletList = new ArrayList<>();
    public static List<Boolean> usePencilList = new ArrayList<>();
    public static List<Boolean> useSpreadsheetsList = new ArrayList<>();
    public static List<Boolean> anyExperienceList = new ArrayList<>();
    public static List<Boolean> useSpreadsheetsMultiTouchList = new ArrayList<>();

    /**
     * Questionnaire data
     */
    public static Map<Integer, List<String>> answer1Map = new HashMap<>();
    public static Map<Integer, List<String>> answer2Map = new HashMap<>();
    public static Map<Integer, List<LocalDateTime>> startTimeMap = new HashMap<>();
    public static Map<Integer, List<LocalDateTime>> endTimeMap = new HashMap<>();

    /**
     * Task detail data
     */
    public static Map<Integer, List<Integer>> resetsMap = new HashMap<>();
    public static Map<Integer, List<LocalDateTime>> startTimeWatchingMap = new HashMap<>();
    public static Map<Integer, List<LocalDateTime>> endTimeWatchingMap = new HashMap<>();
    public static Map<Integer, List<Integer>> timeWatchingMap = new HashMap<>();
    public static Map<Integer, List<LocalDateTime>> startTimeDrawingMap = new HashMap<>();
    public static Map<Integer, List<LocalDateTime>> endTimeDrawingMap = new HashMap<>();
    public static Map<Integer, List<Integer>> timeDrawingMap = new HashMap<>();
    public static List<Integer> resetsList = new ArrayList<>();
    public static List<LocalDateTime> startTimeWatchingList = new ArrayList<>();
    public static List<LocalDateTime> endTimeWatchingList = new ArrayList<>();
    public static List<Integer> timeWatchingList = new ArrayList<>();
    public static List<LocalDateTime> startTimeDrawingList = new ArrayList<>();
    public static List<LocalDateTime> endTimeDrawingList = new ArrayList<>();
    public static List<Integer> timeDrawingList = new ArrayList<>();

    // Methode zum Einlesen einer JSON-Datei und Konvertieren in ein JSONArray
    public static void startJsonReader() {
        System.out.println("Reading JSON files...");
        System.out.println(SEPARATOR);
        for (int i = 1; i <= 20; i++) {
            answer1Map.put(i, new ArrayList<>());
            answer2Map.put(i, new ArrayList<>());
            startTimeMap.put(i, new ArrayList<>());
            endTimeMap.put(i, new ArrayList<>());

            resetsMap.put(i, new ArrayList<>());
            startTimeWatchingMap.put(i, new ArrayList<>());
            endTimeWatchingMap.put(i, new ArrayList<>());
            timeWatchingMap.put(i, new ArrayList<>());
            startTimeDrawingMap.put(i, new ArrayList<>());
            endTimeDrawingMap.put(i, new ArrayList<>());
            timeDrawingMap.put(i, new ArrayList<>());
        }

        for (int studyNumber = 1; studyNumber <= STUDY_NUMBER; studyNumber++) {
            String filePathDemographics = "src/main/resources/studies/demographics/" + studyNumber + "_demographics.json";
            try {
                saveDemographicsData(new JSONObject(new String(Files.readAllBytes(Paths.get(filePathDemographics)))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (int taskId = 1; taskId <= 20; taskId++) {
            for (int studyNumber = 1; studyNumber <= STUDY_NUMBER; studyNumber++) {
                String filePathQuestionnaire = "src/main/resources/studies/questionnaire/" + taskId + "/"  + studyNumber + "_questionnaire.json";
                String filePathTaskDetail = "src/main/resources/studies/taskDetail/" + taskId + "/" + studyNumber + "_task_detail.json";

                JSONObject jsonQuestionnaire = new JSONObject();
                JSONObject jsonTaskDetail = new JSONObject();
                try {
                    String contentQuestionnaire = new String(Files.readAllBytes(Paths.get(filePathQuestionnaire)));
                    String contentTaskDetail = new String(Files.readAllBytes(Paths.get(filePathTaskDetail)));
                    jsonQuestionnaire = new JSONObject(contentQuestionnaire);
                    jsonTaskDetail = new JSONObject(contentTaskDetail);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                saveQuestionnaireData(taskId, jsonQuestionnaire);
                saveTaskDetailData(taskId, jsonTaskDetail);
            }
        }

        boolean demographicsComplete = genderList.size() == STUDY_NUMBER
                && ageList.size() == STUDY_NUMBER
                && leftHandedOrRightHandedList.size() == STUDY_NUMBER
                && ownATabletList.size() == STUDY_NUMBER
                && useTabletList.size() == STUDY_NUMBER
                && usePencilList.size() == STUDY_NUMBER
                && useSpreadsheetsList.size() == STUDY_NUMBER
                && anyExperienceList.size() == STUDY_NUMBER
                && useSpreadsheetsMultiTouchList.size() == STUDY_NUMBER;
        if (demographicsComplete) {
            System.out.println("Demographics are complete -> (GenderList: " + genderList.size() +
                    ", AgeList: " + ageList.size() +
                    ", LeftHandedOrRightHandedList: " + leftHandedOrRightHandedList.size() +
                    ", OwnATabletList: " + ownATabletList.size() +
                    ", UseTabletList: " + useTabletList.size() +
                    ", UsePencilList: " + usePencilList.size() +
                    ", UseSpreadsheetsList: " + useSpreadsheetsList.size() +
                    ", AnyExperienceList: " + anyExperienceList.size() +
                    ", UseSpreadsheetsMultiTouchList: " + useSpreadsheetsMultiTouchList.size() + ")");
        }
        else  {
            System.out.println("ERROR: Demographics are incomplete -> (GenderList: " + genderList.size() +
                    ", AgeList: " + ageList.size() +
                    ", LeftHandedOrRightHandedList: " + leftHandedOrRightHandedList.size() +
                    ", OwnATabletList: " + ownATabletList.size() +
                    ", UseTabletList: " + useTabletList.size() +
                    ", UsePencilList: " + usePencilList.size() +
                    ", UseSpreadsheetsList: " + useSpreadsheetsList.size() +
                    ", AnyExperienceList: " + anyExperienceList.size() +
                    ", UseSpreadsheetsMultiTouchList: " + useSpreadsheetsMultiTouchList.size() + ")");
        }

        for (int i = 1; i <= 20; i++) {
            boolean questionnaireComplete = answer1Map.get(i).size() == STUDY_NUMBER
                    && answer2Map.get(i).size() == STUDY_NUMBER
                    && startTimeMap.get(i).size() == STUDY_NUMBER
                    && endTimeMap.get(i).size() == STUDY_NUMBER;
            if (questionnaireComplete) {
                System.out.println("Questionnaire " + i + " is complete. -> (Answer1Map: " + answer1Map.get(i).size() +
                        ", Answer2Map: " + answer2Map.get(i).size() +
                        ", StartTimeMap: " + startTimeMap.get(i).size() +
                        ", EndTimeMap: " + endTimeMap.get(i).size() + ")");
            }
            else {
                System.out.println("ERROR: Questionnaire " + i + " is incomplete -> (Answer1Map: " + answer1Map.get(i).size() +
                        ", Answer2Map: " + answer2Map.get(i).size() +
                        ", StartTimeMap: " + startTimeMap.get(i).size() +
                        ", EndTimeMap: " + endTimeMap.get(i).size() + ")");
            }

            boolean taskDetailComplete = resetsMap.get(i).size() == STUDY_NUMBER
                    && startTimeWatchingMap.get(i).size() == STUDY_NUMBER
                    && endTimeWatchingMap.get(i).size() == STUDY_NUMBER
                    && timeWatchingMap.get(i).size() == STUDY_NUMBER
                    && startTimeDrawingMap.get(i).size() == STUDY_NUMBER
                    && endTimeDrawingMap.get(i).size() == STUDY_NUMBER
                    && timeDrawingMap.get(i).size() == STUDY_NUMBER;
            if (taskDetailComplete) {
                System.out.println("TaskDetail " + i + " is complete. -> (ResetsMap: " + resetsMap.get(i).size() +
                        ", StartTimeWatchingMap: " + startTimeWatchingMap.get(i).size() +
                        ", EndTimeWatchingMap: " + endTimeWatchingMap.get(i).size() +
                        ", TimeWatchingMap: " + timeWatchingMap.get(i).size() +
                        ", StartTimeDrawingMap: " + startTimeDrawingMap.get(i).size() +
                        ", EndTimeDrawingMap: " + endTimeDrawingMap.get(i).size() +
                        ", TimeDrawingMap: " + timeDrawingMap.get(i).size() + ")");
            }
            else {
                System.out.println("ERROR: TaskDetail " + i + " is incomplete -> (ResetsMap: " + resetsMap.get(i).size() +
                        ", StartTimeWatchingMap: " + startTimeWatchingMap.get(i).size() +
                        ", EndTimeWatchingMap: " + endTimeWatchingMap.get(i).size() +
                        ", TimeWatchingMap: " + timeWatchingMap.get(i).size() +
                        ", StartTimeDrawingMap: " + startTimeDrawingMap.get(i).size() +
                        ", EndTimeDrawingMap: " + endTimeDrawingMap.get(i).size() +
                        ", TimeDrawingMap: " + timeDrawingMap.get(i).size() + ")");
            }
        }
        System.out.println(SEPARATOR);
        System.out.println("Finished reading JSON files.");
    }

    private static void saveDemographicsData(JSONObject json) {
        json.keySet().forEach(key -> {
            switch (key) {
                case "gender":
                    genderList.add(json.getString(key));
                    break;
                case "age":
                    ageList.add(json.getString(key));
                    break;
                case "leftHandedOrRightHanded":
                    leftHandedOrRightHandedList.add(json.getString(key));
                    break;
                case "ownATablet":
                    ownATabletList.add(json.getBoolean(key));
                    break;
                case "useTablet":
                    useTabletList.add(json.getString(key));
                    break;
                case "usePencil":
                    usePencilList.add(json.getBoolean(key));
                    break;
                case "useSpreadsheets":
                    useSpreadsheetsList.add(json.getBoolean(key));
                    break;
                case "anyExperience":
                    anyExperienceList.add(json.getBoolean(key));
                    break;
                case "useSpreadsheetsMultiTouch":
                    useSpreadsheetsMultiTouchList.add(json.getBoolean(key));
                    break;
                default:
                    throw new IllegalStateException("Unexpected value for Demographics: " + key);
            }
        });
    }

    private static void saveQuestionnaireData(int taskNumber, JSONObject json) {
        json.keySet().forEach(key -> {
            switch (key) {
                case "answer1":
                    answer1Map.get(taskNumber).add(json.getString(key));
                    break;
                case "answer2":
                    answer2Map.get(taskNumber).add(json.getString(key));
                    break;
                case "startTime":
                    startTimeMap.get(taskNumber).add(LocalDateTime.ofInstant(Instant.parse(json.getString(key)), ZoneId.systemDefault()));
                    break;
                case "endTime":
                    endTimeMap.get(taskNumber).add(LocalDateTime.ofInstant(Instant.parse(json.getString(key)), ZoneId.systemDefault()));
                    break;
                case "question1": case "question2": case "id":
                    break;
                default:
                    throw new IllegalStateException("Unexpected value for Questionnaire: " + key);
            }
        });
    }

    private static void saveTaskDetailData(int taskNumber, JSONObject json) {
        json.keySet().forEach(key -> {
            switch (key) {
                case "resets":
                    resetsMap.get(taskNumber).add(json.getInt(key));
                    resetsList.add(json.getInt(key));
                    break;
                case "startTimeWatching":
                    startTimeWatchingMap.get(taskNumber).add(LocalDateTime.ofInstant(Instant.parse(json.getString(key)), ZoneId.systemDefault()));
                    startTimeWatchingList.add(LocalDateTime.ofInstant(Instant.parse(json.getString(key)), ZoneId.systemDefault()));
                    break;
                case "endTimeWatching":
                    endTimeWatchingMap.get(taskNumber).add(LocalDateTime.ofInstant(Instant.parse(json.getString(key)), ZoneId.systemDefault()));
                    endTimeWatchingList.add(LocalDateTime.ofInstant(Instant.parse(json.getString(key)), ZoneId.systemDefault()));
                    break;
                case "timeWatching":
                    timeWatchingMap.get(taskNumber).add(json.getInt(key));
                    timeWatchingList.add(json.getInt(key));
                    break;
                case "startTimeDrawing":
                    startTimeDrawingMap.get(taskNumber).add(LocalDateTime.ofInstant(Instant.parse(json.getString(key)), ZoneId.systemDefault()));
                    startTimeDrawingList.add(LocalDateTime.ofInstant(Instant.parse(json.getString(key)), ZoneId.systemDefault()));
                    break;
                case "endTimeDrawing":
                    endTimeDrawingMap.get(taskNumber).add(LocalDateTime.ofInstant(Instant.parse(json.getString(key)), ZoneId.systemDefault()));
                    endTimeDrawingList.add(LocalDateTime.ofInstant(Instant.parse(json.getString(key)), ZoneId.systemDefault()));
                    break;
                case "timeDrawing":
                    timeDrawingMap.get(taskNumber).add(json.getInt(key));
                    timeDrawingList.add(json.getInt(key));
                    break;
                case "id": case "description": case "title": case "group": case "language": case "taskNumber":
                    break;
                default:
                    throw new IllegalStateException("Unexpected value for TaskDetail: " + key);
            }
        });
    }

}
