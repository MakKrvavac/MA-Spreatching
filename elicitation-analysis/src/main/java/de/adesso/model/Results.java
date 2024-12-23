package de.adesso.model;

import java.util.*;

public class Results {

    // Demographics
    public static Map<Integer, List<String>> DEMOGRAPHICS_MAPS = new HashMap<>();
    public static List<String> GENDER_LIST = new ArrayList<>();
    public static List<Integer> AGE_LIST = new ArrayList<>();
    public static List<String> HANDEDNESS_LIST = new ArrayList<>();
    public static List<Boolean> OWN_A_TABLET_LIST = new ArrayList<>();
    public static List<String> USE_TABLET_LIST = new ArrayList<>();
    public static List<Boolean> USE_PENCIL_LIST = new ArrayList<>();
    public static List<Boolean> USE_SPREADSHEETS_LIST = new ArrayList<>();
    public static List<Boolean> ANY_EXPERIENCE_LIST = new ArrayList<>();
    public static List<Boolean> USE_SPREADSHEETS_MULTI_TOUCH_LIST = new ArrayList<>();


    // Questionnaire
    public static Map<Integer, List<String>> ANSWER1_MAP = new HashMap<>();
    public static Map<Integer, List<String>> ANSWER2_MAP = new HashMap<>();


    // Task Details
    public static Map<Integer, List<Integer>> RESETS_MAP = new HashMap<>();
    public static Map<Integer, List<Integer>> TIME_WATCHING_MAP = new HashMap<>();
    public static Map<Integer, List<Integer>> TIME_DRAWING_MAP = new HashMap<>();


    // T-Test
}
