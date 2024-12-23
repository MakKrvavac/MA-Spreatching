package de.adesso.statistic;

import de.adesso.core.JsonReader;
import de.adesso.model.Results;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static de.adesso.ElicitationAnalysisApp.SEPARATOR;
import static de.adesso.core.JsonReader.*;

public class DemographicsCalculator {

    public static Map<Integer, List<String>> DEMOGRAPHICS_MAPS = new HashMap<>();
    public static Map<String, Integer> GENDER_MAP_FREQUENCY = new HashMap<>();
    public static Map<String, Integer> AGE_MAP_FREQUENCY = new HashMap<>();
    public static Map<String, Integer> HANDEDNESS_MAP_FREQUENCY = new HashMap<>();
    public static Map<Boolean, Integer> OWN_A_TABLET_MAP_FREQUENCY = new HashMap<>();
    public static Map<String, Integer> USE_TABLET_MAP_FREQUENCY = new HashMap<>();
    public static Map<Boolean, Integer> USE_PENCIL_MAP_FREQUENCY = new HashMap<>();
    public static Map<Boolean, Integer> USE_SPREADSHEETS_MAP_FREQUENCY = new HashMap<>();
    public static Map<Boolean, Integer> ANY_EXPERIENCE_MAP_FREQUENCY = new HashMap<>();
    public static Map<Boolean, Integer> USE_SPREADSHEETS_MULTI_TOUCH_MAP_FREQUENCY = new HashMap<>();

    public static void calculateDemographics() {
        System.out.println("Analysis of the demographic data...");
        System.out.println(SEPARATOR);
        GENDER_MAP_FREQUENCY = calculateGenderFrequency();
        System.out.println("Gender: " + GENDER_MAP_FREQUENCY);
        AGE_MAP_FREQUENCY = calculateAgeFrequency();
        System.out.println("Age: " + AGE_MAP_FREQUENCY);
        HANDEDNESS_MAP_FREQUENCY = calculateLeftHandedOrRightHandedFrequency();
        System.out.println("Handedness: " + HANDEDNESS_MAP_FREQUENCY);
        OWN_A_TABLET_MAP_FREQUENCY = calculateOwnATabletFrequency();
        System.out.println("OwnATablet: " + OWN_A_TABLET_MAP_FREQUENCY);
        USE_TABLET_MAP_FREQUENCY = calculateUseTabletFrequency();
        System.out.println("UseTablet: " + USE_TABLET_MAP_FREQUENCY);
        USE_PENCIL_MAP_FREQUENCY = calculateUsePencilFrequency();
        System.out.println("UsePencil: " + USE_PENCIL_MAP_FREQUENCY);
        USE_SPREADSHEETS_MAP_FREQUENCY = calculateUseSpreadsheetsFrequency();
        System.out.println("UseSpreadsheets: " + USE_SPREADSHEETS_MAP_FREQUENCY);
        ANY_EXPERIENCE_MAP_FREQUENCY = calculateAnyExperienceFrequency();
        System.out.println("AnyExperience: " + ANY_EXPERIENCE_MAP_FREQUENCY);
        USE_SPREADSHEETS_MULTI_TOUCH_MAP_FREQUENCY = calculateUseSpreadsheetsMultiTouchFrequency();
        System.out.println("UseSpreadsheetsMultiTouch: " + USE_SPREADSHEETS_MULTI_TOUCH_MAP_FREQUENCY);

        DEMOGRAPHICS_MAPS = calculateDemographicsMaps();
        Results.DEMOGRAPHICS_MAPS = DEMOGRAPHICS_MAPS;
        System.out.println("Demographics maps: " + DEMOGRAPHICS_MAPS);

        System.out.println(SEPARATOR);
        System.out.println("Analysis of the demographic data completed.");
    }

    public static Map<String, Integer> calculateGenderFrequency() {
        Map<String, Integer> genderMapping = new HashMap<>();
        genderMapping.put("female", 0);
        genderMapping.put("male", 1);
        genderMapping.put("non-binary", 2);
        genderMapping.put("prefer not to say", 3);
        genderMapping.put("weiblich", 0);
        genderMapping.put("männlich", 1);
        genderMapping.put("nicht-binär", 2);
        genderMapping.put("Keine Angabe", 3);

        Map<String, Integer> frequencies = new HashMap<>();
        for (String gender : JsonReader.genderList) {
            if (genderMapping.containsKey(gender)) {
                String mappedCategory = getKeyByValue(genderMapping, genderMapping.get(gender));
                frequencies.put(mappedCategory, frequencies.getOrDefault(mappedCategory, 0) + 1);
            }
        }

        //TODO remove JsonReader.genderList
        Results.GENDER_LIST = JsonReader.genderList;
        return frequencies;
    }

    private static Map<String, Integer> calculateAgeFrequency() {
        Map<String, Integer> ageMapping = new HashMap<>();
        ageMapping.put("18-24", 0);
        ageMapping.put("25-30", 1);
        ageMapping.put("30-40", 2);
        ageMapping.put("41-60", 3);
        ageMapping.put("61-80", 4);
        ageMapping.put("prefer not to say", 5);
        ageMapping.put("Keine Angabe", 5);

        Map<String, Integer> frequencies = new HashMap<>();
        for (String age : ageList) {
            String mappedCategory = getKeyByValue(ageMapping, ageMapping.get(age));
            frequencies.put(mappedCategory, frequencies.getOrDefault(mappedCategory, 0) + 1);
        }
        return frequencies;
    }

    private static Map<String, Integer> calculateLeftHandedOrRightHandedFrequency() {
        Map<String, Integer> handednessMapping = new HashMap<>();
        handednessMapping.put("left", 0);
        handednessMapping.put("right", 1);
        handednessMapping.put("links", 0);
        handednessMapping.put("rechts", 1);

        Map<String, Integer> frequencies = new HashMap<>();
        for (String handedness : leftHandedOrRightHandedList) {
            String mappedCategory = getKeyByValue(handednessMapping, handednessMapping.get(handedness));
            frequencies.put(mappedCategory, frequencies.getOrDefault(mappedCategory, 0) + 1);
        }
        return frequencies;
    }

    private static Map<Boolean, Integer> calculateOwnATabletFrequency() {
        Map<Boolean, Integer> ownATabletMapping = new HashMap<>();
        ownATabletMapping.put(true, 0);
        ownATabletMapping.put(false, 1);

        Map<Boolean, Integer> frequencies = new HashMap<>();
        for (Boolean ownATablet : ownATabletList) {
            frequencies.put(ownATablet, frequencies.getOrDefault(ownATablet, 0) + 1);
        }
        return frequencies;
    }

    private static Map<String, Integer> calculateUseTabletFrequency() {
        Map<String, Integer> useTabletMapping = new HashMap<>();
        useTabletMapping.put("Internet", 0);
        useTabletMapping.put("Games", 1);
        useTabletMapping.put("Work", 2);
        useTabletMapping.put("Books", 3);
        useTabletMapping.put("Other", 4);
        useTabletMapping.put("Spiele", 1);
        useTabletMapping.put("Arbeit", 2);
        useTabletMapping.put("Bücher", 3);
        useTabletMapping.put("Andere Media", 4);

        Map<String, Integer> frequencies = new HashMap<>();
        for (String useTablet : useTabletList) {
            if (useTablet.isEmpty()) {
                continue;
            }
            String mappedCategory = getKeyByValue(useTabletMapping, useTabletMapping.get(useTablet));
            frequencies.put(mappedCategory, frequencies.getOrDefault(mappedCategory, 0) + 1);
        }
        return frequencies;
    }

    private static Map<Boolean, Integer> calculateUsePencilFrequency() {
        Map<Boolean, Integer> usePencilMapping = new HashMap<>();
        usePencilMapping.put(true, 0);
        usePencilMapping.put(false, 1);

        Map<Boolean, Integer> frequencies = new HashMap<>();
        for (Boolean usePencil : usePencilList) {
            frequencies.put(usePencil, frequencies.getOrDefault(usePencil, 0) + 1);
        }
        return frequencies;
    }

    private static Map<Boolean, Integer> calculateUseSpreadsheetsFrequency() {
        Map<Boolean, Integer> useSpreadsheetsMapping = new HashMap<>();
        useSpreadsheetsMapping.put(true, 0);
        useSpreadsheetsMapping.put(false, 1);

        Map<Boolean, Integer> frequencies = new HashMap<>();
        for (Boolean useSpreadsheets : useSpreadsheetsList) {
            frequencies.put(useSpreadsheets, frequencies.getOrDefault(useSpreadsheets, 0) + 1);
        }
        return frequencies;
    }

    private static Map<Boolean, Integer> calculateAnyExperienceFrequency() {
        Map<Boolean, Integer> anyExperienceMapping = new HashMap<>();
        anyExperienceMapping.put(true, 0);
        anyExperienceMapping.put(false, 1);

        Map<Boolean, Integer> frequencies = new HashMap<>();
        for (Boolean anyExperience : anyExperienceList) {
            frequencies.put(anyExperience, frequencies.getOrDefault(anyExperience, 0) + 1);
        }
        return frequencies;
    }

    private static Map<Boolean, Integer> calculateUseSpreadsheetsMultiTouchFrequency() {
        Map<Boolean, Integer> useSpreadsheetsMultiTouchMapping = new HashMap<>();
        useSpreadsheetsMultiTouchMapping.put(true, 0);
        useSpreadsheetsMultiTouchMapping.put(false, 1);

        Map<Boolean, Integer> frequencies = new HashMap<>();
        for (Boolean useSpreadsheetsMultiTouch : useSpreadsheetsMultiTouchList) {
            frequencies.put(useSpreadsheetsMultiTouch, frequencies.getOrDefault(useSpreadsheetsMultiTouch, 0) + 1);
        }
        return frequencies;
    }

    private static Map<Integer, List<String>> calculateDemographicsMaps() {
        Map<Integer, List<String>> demographicsMaps = new HashMap<>();

        int useCounters = 0;
        for (int i = 0; i < genderList.size(); i++) {
            List<String> userList = new ArrayList<>();
            userList.add(genderList.get(i));
            userList.add(ageList.get(i));
            userList.add(leftHandedOrRightHandedList.get(i));
            boolean ownATablet = ownATabletList.get(i);
            userList.add(ownATabletList.get(i).toString());
            if (ownATablet) {
                userList.add(useTabletList.get(useCounters));
                useCounters++;
            } else {
                userList.add("");
            }
            userList.add(usePencilList.get(i).toString());
            userList.add(useSpreadsheetsList.get(i).toString());
            userList.add(anyExperienceList.get(i).toString());
            userList.add(useSpreadsheetsMultiTouchList.get(i).toString());

            demographicsMaps.put(i+1, userList);
        }
        return demographicsMaps;
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
}
