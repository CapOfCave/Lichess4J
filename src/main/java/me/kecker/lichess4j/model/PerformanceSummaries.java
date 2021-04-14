package me.kecker.lichess4j.model;

import lombok.Value;

@Value
public class PerformanceSummaries {

    private PerformanceSummary chess960;
    private PerformanceSummary atomic;
    private PerformanceSummary racingKings;
    private PerformanceSummary ultraBullet;
    private PerformanceSummary blitz;
    private PerformanceSummary kingOfTheHill;
    private PerformanceSummary bullet;
    private PerformanceSummary correspondence;
    private PerformanceSummary horde;
    private PerformanceSummary puzzle;
    private PerformanceSummary classical;
    private PerformanceSummary rapid;
    private StormPerformanceSummary storm;

}
