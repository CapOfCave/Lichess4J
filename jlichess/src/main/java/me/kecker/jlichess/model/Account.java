package me.kecker.jlichess.model;

import java.time.LocalDateTime;
import java.util.Map;
import lombok.Getter;
import lombok.ToString;
import me.kecker.jlichess.model.enums.GameMode;

@Getter
@ToString
public class Account {

  private String id;
  private String username;
  private boolean online;
  private Map<GameMode, PerformanceSummary> perfs;
  private LocalDateTime createdAt;
  private LocalDateTime seenAt;

}
