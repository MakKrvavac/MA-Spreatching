package de.adesso.statistic;

import static de.adesso.ElicitationAnalysisApp.SEPARATOR;
import static de.adesso.statistic.DemographicsCalculator.calculateDemographics;
import static de.adesso.statistic.QuestionnaireCalculator.calculateQuestionnaire;
import static de.adesso.statistic.TaskDetailCalculator.calculateTaskDetail;

public class StatisticsCalculator {

    public static void startStatisticsCalculator() {
        calculateDemographics();
        calculateQuestionnaire();
        calculateTaskDetail();
    }
}
