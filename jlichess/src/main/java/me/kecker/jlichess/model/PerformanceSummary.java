package me.kecker.jlichess.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PerformanceSummary {

  private int games;
  private int rating;
  private int rd;
  private int prog;
  private boolean prov;
  
}
