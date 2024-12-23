package de.adesso.test;

import de.adesso.model.Results;
import de.adesso.statistic.StatisticsCalculator;
import org.apache.commons.math3.stat.inference.TTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static de.adesso.core.FileReader.STUDY_NUMBER;

public class TTestAnalysis {

    public static void startTTestAnalysis() {
        performTTestForTimeDrawingAndGender(1);
    }

    private static double performTTestForTimeDrawingAndGender(int taskNumber) {
        List<Integer> femaleList = new ArrayList<>();
        List<Integer> maleList = new ArrayList<>();
        List<Integer> nonBinaryList = new ArrayList<>();
        List<Integer> preferNotToSayList = new ArrayList<>();
        for (int i = 0; i < Results.GENDER_LIST.size(); i++) {
            switch (Results.GENDER_LIST.get(i)) {
                case "female": case "weiblich":
                    femaleList.add(Results.TIME_DRAWING_MAP.get(taskNumber).get(i));
                    break;
                case "male": case "männlich":
                    maleList.add(Results.TIME_DRAWING_MAP.get(taskNumber).get(i));
                    break;
                case "non-binary": case "nicht-binär":
                    nonBinaryList.add(Results.TIME_DRAWING_MAP.get(taskNumber).get(i));
                    break;
                case "prefer not to say": case "Keine Angabe":
                    preferNotToSayList.add(Results.TIME_DRAWING_MAP.get(taskNumber).get(i));
                    break;
                default:
                    break;
            }
        }
        System.out.println("Female Task " + taskNumber + ": " + femaleList);
        System.out.println("Male Task " + taskNumber + ": " + maleList);
        System.out.println("NonBinary Task " + taskNumber + ": " + nonBinaryList);
        System.out.println("PreferNotToSay Task " + taskNumber + ": " + preferNotToSayList);

        double[] femaleArray = new double[femaleList.size()];
        double[] maleArray = new double[maleList.size()];
        for (int i = 0; i < femaleList.size(); i++) {
            femaleArray[i] = femaleList.get(i);
        }
        for (int i = 0; i < maleList.size(); i++) {
            maleArray[i] = maleList.get(i);
        }

        System.out.println("Male Array: " + Arrays.toString(maleArray));
        System.out.println("Female Array: " + Arrays.toString(femaleArray));

        return performTTest(femaleArray, maleArray);
    }

    /**
     * Führt einen t-Test für unabhängige Stichproben durch und gibt das Ergebnis zurück.
     * @param sample1 Die erste Stichprobe.
     * @param sample2 Die zweite Stichprobe.
     * @return Gibt die p-Wert des t-Tests zurück.
     */
    private static double performTTest(double[] sample1, double[] sample2) {
        TTest tTest = new TTest();
        double pValue = tTest.tTest(sample1, sample2);

        System.out.println("Der p-Wert des t-Tests ist: " + pValue);

        // Interpretation des p-Wertes
        if (pValue < 0.05) {
            System.out.println("Es gibt einen statistisch signifikanten Unterschied zwischen den beiden Gruppen.");
        } else {
            System.out.println("Es gibt keinen statistisch signifikanten Unterschied zwischen den beiden Gruppen.");
        }

        return pValue;
    }
}
