package me.kecker.lichess4j.test.providers;

import me.kecker.lichess4j.model.account.Profile;

public final class ProfileTestProvider {
    private static final String COUNTRY = "EC";
    private static final String LOCATION = "string";
    private static final String BIO = "Free bugs!";
    private static final String FIRST_NAME = "Thibault";
    private static final String LAST_NAME = "Duplessis";
    private static final int FIDE_RATING = 1500;
    private static final int USCF_RATING = 1500;
    private static final int ECF_RATING = 1500;
    private static final String LINKS = "github.com/ornicar\r\ntwitter.com/ornicar";

    public static Profile getProfile() {
        return new Profile(COUNTRY, LOCATION, BIO, FIRST_NAME, LAST_NAME, FIDE_RATING, USCF_RATING,
                ECF_RATING, LINKS);
    }
    
    private ProfileTestProvider() {
        // this class should not be instantiated
    }
}
