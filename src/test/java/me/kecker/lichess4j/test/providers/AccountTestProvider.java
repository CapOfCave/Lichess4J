package me.kecker.lichess4j.test.providers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import me.kecker.lichess4j.model.Account;
import me.kecker.lichess4j.model.enums.Title;

public final class AccountTestProvider {

    private static final String ID = "georges";
    private static final String USERNAME = "Georges";
    private static final boolean ONLINE = true;
    private static final LocalDateTime CREATED_AT = LocalDateTime.ofInstant(Instant.ofEpochMilli(
            1290415680000L), ZoneId.systemDefault());
    private static final boolean DISABLED = false;
    private static final boolean TOS_VIOLATION = false;
    private static final boolean BOOSTER = false;
    private static final LocalDateTime SEEN_AT = LocalDateTime.ofInstant(Instant.ofEpochMilli(
            1522636452014L), ZoneId.systemDefault());
    private static final boolean PATRON = true;
    private static final String LANGUAGE = "en-GB";
    private static final Title TITLE = Title.NM;
    private static final String URL = "https://lichess.org/@/georges";
    private static final String PLAYING = "https://lichess.org/yqfLYJ5E/black";
    private static final int NB_FOLLOWING = 299;
    private static final int NB_FOLLOWERS = 2735;
    private static final int COMPLETION_RATE = 97;
    private static final boolean STREAMING = false;
    private static final boolean FOLLOWABLE = true;
    private static final boolean FOLLOWING = false;
    private static final boolean BLOCKING = false;
    private static final boolean FOLLOWS_YOU = false;

    public static Account getAccount() {
        return new Account(ID, USERNAME, ONLINE, PerformanceSummariesTestProvider
                .getPerformanceSummaries(), CREATED_AT, DISABLED, TOS_VIOLATION, BOOSTER,
                ProfileTestProvider.getProfile(), SEEN_AT, PATRON, PlayTimeTestProvider
                        .getPlayTime(), LANGUAGE, TITLE, URL, PLAYING, NB_FOLLOWING, NB_FOLLOWERS,
                COMPLETION_RATE, GameCountTestProvider.getCameCount(), STREAMING, FOLLOWABLE,
                FOLLOWING, BLOCKING, FOLLOWS_YOU);
    }

    private AccountTestProvider() {
        // this class should not be instantiated
    }
}
