package me.kecker.lichess4j.test.providers;

import me.kecker.lichess4j.model.account.PlayTime;

public final class PlayTimeTestProvider {

    public static PlayTime getPlayTime() {
        return new PlayTime(3296897, 12134);
    }
    
    private PlayTimeTestProvider() {
        // this class should not be instantiated
    }
}
