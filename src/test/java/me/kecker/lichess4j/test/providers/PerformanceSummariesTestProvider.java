package me.kecker.lichess4j.test.providers;

import me.kecker.lichess4j.model.PerformanceSummaries;

public class PerformanceSummariesTestProvider {

    public static PerformanceSummaries getPerformanceSummaries() {
        return new PerformanceSummaries(PerformanceSummaryTestProvider.getDefaultSummary(),
                PerformanceSummaryTestProvider.getDefaultSummary(), PerformanceSummaryTestProvider
                        .getDefaultSummary(), PerformanceSummaryTestProvider.getDefaultSummary(),
                PerformanceSummaryTestProvider.getDefaultSummary(), PerformanceSummaryTestProvider
                        .getDefaultSummary(), PerformanceSummaryTestProvider.getDefaultSummary(),
                PerformanceSummaryTestProvider.getDefaultSummary(), PerformanceSummaryTestProvider
                        .getDefaultSummary(), PerformanceSummaryTestProvider.getDefaultSummary(),
                PerformanceSummaryTestProvider.getDefaultSummary(), PerformanceSummaryTestProvider
                        .getDefaultSummary(), PerformanceSummaryTestProvider.getStormSummary());
    }
}
