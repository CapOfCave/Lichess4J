package me.kecker.lichess4j.model.account;

import java.time.LocalDateTime;
import lombok.Value;
import me.kecker.lichess4j.model.enums.Title;

@Value
public class Account {

    private String id;
    private String username;
    private boolean online;
    private PerformanceSummaries perfs;
    private LocalDateTime createdAt;
    private boolean disabled;
    private boolean tosViolation;
    private boolean booster;
    private Profile profile;
    private LocalDateTime seenAt;
    private boolean patron;
    private PlayTime playTime;
    private String language;
    private Title title;
    private String url;
    private String playing;
    private int nbFollowing;
    private int nbFollowers;
    private int completionRate;
    private GameCount count;
    private boolean streaming;
    private boolean followable;
    private boolean following;
    private boolean blocking;
    private boolean followsYou;
}
