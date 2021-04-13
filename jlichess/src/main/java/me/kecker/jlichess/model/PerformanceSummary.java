package me.kecker.jlichess.model;

import lombok.Value;

@Value
public class PerformanceSummary {

    private int games;
    private int rating;
    private int rd;
    private int prog;
    private boolean prov;

}
