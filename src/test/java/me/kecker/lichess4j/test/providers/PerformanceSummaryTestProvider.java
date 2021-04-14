package me.kecker.lichess4j.test.providers;

import me.kecker.lichess4j.model.PerformanceSummary;
import me.kecker.lichess4j.model.StormPerformanceSummary;

public class PerformanceSummaryTestProvider {

    public static PerformanceSummary getDefaultSummary() {
        return new PerformanceSummary(2945, 1609, 60, -22, true);
    }
    
    public static StormPerformanceSummary getStormSummary() {
        return new StormPerformanceSummary(44, 61);
    }
    
}
