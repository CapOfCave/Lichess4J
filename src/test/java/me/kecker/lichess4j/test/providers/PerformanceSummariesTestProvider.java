package me.kecker.lichess4j.test.providers;

import me.kecker.lichess4j.model.account.PerformanceSummaries;

public final class PerformanceSummariesTestProvider {

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

    private PerformanceSummariesTestProvider() {
        // this class should not be instantiated
    }
}
